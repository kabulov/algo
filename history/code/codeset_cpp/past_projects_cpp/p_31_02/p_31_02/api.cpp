#pragma once

#include "main_lss_31_02.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
ATOM RegMyWindowClass(HINSTANCE, LPCTSTR);

//w - shirina okna rab stola
//h - visota okna rab stola
int w, h;

//standartnaya chast vo vsex api programmax - nachalo
int APIENTRY WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow)
{
	//razmeri okna
	RECT scr;
	GetWindowRect(GetDesktopWindow(), &scr);	

	//registriruem klass okna
	LPCTSTR lpzClass = TEXT("newClass");
	if (!RegMyWindowClass(hInstance, lpzClass))
		return 1;

	//sozdaem okno
	HWND hWnd = CreateWindow(lpzClass, TEXT("interpolyaciya kusochno-kubicheskimi funkciyami"), 
							WS_OVERLAPPEDWINDOW | WS_VISIBLE, 0, 0, scr.right, scr.bottom-40, NULL, NULL, hInstance, NULL);

	if (!hWnd)
		return 2;

	w = scr.right;
	h = scr.bottom;

	MSG msg = {0};
	int getM = 0;

	while ((getM = GetMessage(&msg, NULL, 0, 0)) != 0) {
		if (getM == -1) 
			return 3;

		TranslateMessage(&msg);
		DispatchMessage(&msg); //WndProc 
	}

	return msg.wParam; 
}

ATOM RegMyWindowClass(HINSTANCE hInst, LPCTSTR lpzClassName) {
	WNDCLASS wcWindowClass = {0};
	wcWindowClass.lpfnWndProc = (WNDPROC)WndProc;
	wcWindowClass.hInstance = hInst;
	wcWindowClass.lpszClassName = lpzClassName;
	wcWindowClass.style = CS_HREDRAW | CS_VREDRAW;
	wcWindowClass.hCursor = LoadCursor(NULL, IDC_ARROW);
	wcWindowClass.hbrBackground = (HBRUSH)COLOR_APPWORKSPACE;
	return RegisterClass(&wcWindowClass);
}
//standartnaya chast vo vsex api programmax - konec

//liniya ot tochki x1y1 do tochki x2y2
BOOL Line(HDC hdc, int x1, int y1, int x2, int y2) {
	MoveToEx(hdc, x1, y1, NULL); //сделать текущими координаты x1, y1
	return LineTo(hdc, x2, y2);
}

//osnovnoe deystvo zdes
LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam) {
	HDC hdc; //создаём контекст устройства
	PAINTSTRUCT ps; //создаём экземпляр структуры графического вывода
	HPEN hPen = NULL; //создаём перо

	//standartnie vxodnoy i vixodnie fayli
	char* defIn = "lss_31_02_in.txt";
	char* defOut = "lss_31_02_out.txt";

	//reshaem sobstvenno zadachu
	if (main(2, defIn, defOut) != 0)
		return -1;	 //oshibka

	//peremennie
	int n, k;
	double a, b;
	double *x, *f, *pf;
	double maxy, miny;

	int i;

	//chitaem  dannie, poxodu vichislyaem minimum i maksimum funkcii na [a,b], standartniy kod
	FILE *In = fopen(defIn, "r");
	fscanf(In, "%d %d", &n, &n); //p and n
	x = (double*)malloc(sizeof(double)*n);
	f = (double*)malloc(sizeof(double)*n);
	for (i=0; i<n; i++) {
		fscanf(In, "%lf %lf", &x[i], &f[i]);
		
		if (i == 0) {
			miny = f[0];
			maxy = f[0];
		} else {
			maxy = f[i] > maxy ? f[i] : maxy;
			miny = f[i] < miny ? f[i] : miny;
		}
	}
	fscanf(In, "%lf %lf %d", &a, &b, &k);
	fclose(In);

	In = fopen(defOut, "r");
	pf = (double*)malloc(sizeof(double) * (k+1));
	for (i = 0; i < k+1; i++) {	
		fscanf(In, "%lf", &pf[i]);
		maxy = pf[i] > maxy ? pf[i] : maxy;
		miny = pf[i] < miny ? pf[i] : miny;
	}
	fclose(In);
	//zakonchili chitat dannie 

	switch(message) {
		case WM_SIZE:
			w=LOWORD(lParam);
			h=HIWORD(lParam);
			break;
		case WM_PAINT:
			//deystvie nachinaetsa tut
			hdc = BeginPaint(hWnd, &ps);
			SetMapMode(hdc, MM_ISOTROPIC); //логические единицы отображаем, как физические
			SetWindowExtEx(hdc, w, h, NULL); //Длина осей ???
			SetViewportExtEx(hdc, w, -h, NULL); //Определяем облась вывода
			SetViewportOrgEx(hdc, 0, h, NULL); //Начало координат			

			int i;
			double koef;
			//masshtabiruem po X						///!!!!!!
			koef = w/(b-a) * 0.8;
			b *= koef;
			a *= koef;
			for (i = 0; i < n; ++i)
				x[i] *= koef;

			//masshtabiruem po Y
			koef = h/(maxy - miny) * 0.8;					///!!!
			for (i = 0; i < n; ++i)
				f[i] *= koef;
			for (i = 0; i < k + 1; ++i)
				pf[i] *= koef;
			maxy *= koef;
			miny *= koef;

			//peremeshaem grafik po X					////!!!!!!!!
			for (i=0; i<n; i++)
				x[i] += -a + 75;
			b = b-a + 75;
			a = 0 + 75;

			//peremeshaem grafik po Y						////!!!!
			for (i=0; i<n; i++)
				f[i] += -miny + 100;
			for (i=0; i<k+1; i++)
				pf[i] += -miny + 100;
			maxy = maxy - miny + 100;
			miny = 0 + 75;

			//risuem grafik f
			hPen=CreatePen(0,1,RGB(255,0,0)); //sozdaem pero
			SelectObject(hdc, hPen);//delaem pero tekushim
			MoveToEx(hdc, x[0], f[0], NULL);//peremeshaemsa v tochku
			for (i = 1; i < n; ++i) {
				LineTo(hdc, x[i], f[i]); //posledovatelno soedinyaem tochki
			}
			
			//risuem grafik pf 
			hPen=CreatePen(0,1,RGB(0,255,0));
			SelectObject(hdc, hPen);
			MoveToEx(hdc, a, pf[0], NULL);
			for(i = 1; i < k+1; ++i){
				LineTo(hdc, a+i*(b-a)/k, pf[i]);
			}

			//pishem slova esli nado
			TextOut(hdc, 0, h-20, TEXT("green - PF, red - F"), 19);

			ValidateRect(hWnd, NULL); //Обновляем экран
			EndPaint(hWnd, &ps);
			break;
		case WM_DESTROY:
			if (hPen != NULL)
				DeleteObject(hPen); //не забываем уничтожать перья
			PostQuitMessage(0); //Посылаем сообщение выхода с кодом 0 - нормальное завершение
			break;
		default:
			free(x);
			free(f);
			free(pf);
			return DefWindowProc(hWnd, message, wParam, lParam);//освобождаем очередь приложения от нераспознаных
	}

	free(x);
	free(f);
	free(pf);

	return 0;
}