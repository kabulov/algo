#pragma once

#include "main.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#include <windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
ATOM RegMyWindowClass(HINSTANCE, LPCTSTR);

BOOL Line(HDC hdc, int x1, int y1, int x2, int y2);

int x, y;

int n, k;
double a, b;
double *xv, *fxv;
double *Pfxv;
double YMAX, XMAX, YMIN;

char* ifn1 = "lss_14_21_in.txt";
char* ifn2 = "lss_14_21_out.txt";

int APIENTRY WinMain(HINSTANCE hInstance,
					 HINSTANCE hPrevInstance,
					 LPSTR lpCmdLine,
					 int nCmdShow)
{
	LPCTSTR lpzClass = TEXT("windowClass");

	if (!RegMyWindowClass(hInstance, lpzClass))
		return 1;

	RECT scr_rect;
	GetWindowRect(GetDesktopWindow(), &scr_rect);	
	x = scr_rect.right;
	y = scr_rect.bottom;

	HWND hWnd = CreateWindow(lpzClass, 
							 TEXT("KAZIM KABULOV - Interpolyaciya Parabolicheskimi Splaynami"), 
							 WS_OVERLAPPEDWINDOW | WS_VISIBLE,
							 0, 0,
							 scr_rect.right, scr_rect.bottom - 40,
							 NULL, NULL,
							 hInstance, 
							 NULL);

	if (!hWnd)
		return 2;

//-------------------------------------------------------------------------------------------------------------------------------------------------------
	//matematicheskaya chast zadachi
	if (solve(ifn1, ifn2) != 0)
		return -1000;	//not solved
	//

	//schitivaem dannie dlya risovaniya grafika
	FILE *fin;
	fin = fopen(ifn1, "r");
	
	int i;

	fscanf(fin, "%d %d", &n, &n); //p and n
	xv = (double*)malloc(sizeof(double)*n);
	fxv = (double*)malloc(sizeof(double)*n);
	
	for (i = 0; i < n; ++i) {
		fscanf(fin, "%lf %lf", &xv[i], &fxv[i]);
		if (i == 0) {
			YMIN = fxv[0];

			YMAX = fxv[0];
			XMAX = xv[0];
		} else {
			if (fxv[i] > YMAX) {
				XMAX = xv[i];		
				YMAX = fxv[i];
			}
			if (fxv[i] < YMIN) {
				YMIN = fxv[i];
			}
		}
	}
	fscanf(fin, "%lf %lf %d", &a, &b, &k);

	fclose(fin);

	fin = fopen(ifn2, "r");

	Pfxv = (double*)malloc(sizeof(double) * (k+1));
	for (i = 0; i <= k; ++i) {	
		fscanf(fin, "%lf", &Pfxv[i]);
		if (Pfxv[i] > YMAX) {
			YMAX = Pfxv[i];
			XMAX = a + i*(b-a)/k;
		}
		if (Pfxv[i] < YMIN) {
			YMIN = Pfxv[i];
		}
	}
	
	fclose(fin);
	//
//-------------------------------------------------------------------------------------------------------------------------------------------------------

	MSG msg = {0};
	int iGetOk = 0;

	while ((iGetOk = GetMessage(&msg, NULL, 0, 0)) != 0) {
		if (iGetOk == -1) 
			return 3;

		TranslateMessage(&msg);
		DispatchMessage(&msg); //WndProc 
	}

	free(xv);
	free(fxv);
	free(Pfxv);

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

BOOL Line(HDC hdc, int x1, int y1, int x2, int y2) {
	MoveToEx(hdc, x1, y1, NULL); //сделать текущими координаты x1, y1
	return LineTo(hdc, x2, y2);
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam) {
	HDC hdc; //создаём контекст устройства
	PAINTSTRUCT ps; //создаём экземпляр структуры графического вывода
	HPEN hPen = NULL; //создаём перо

	double ymax = YMAX, ymin = YMIN;
	char fstr[20];

	switch(message) {
		case WM_SIZE:
			x=LOWORD(lParam);
			y=HIWORD(lParam);
			break;
		case WM_PAINT:
			hdc = BeginPaint(hWnd, &ps);
			SetMapMode(hdc, MM_ISOTROPIC); //логические единицы отображаем, как физические
			SetViewportExtEx(hdc, x, -y, NULL); //Определяем облась вывода
			SetWindowExtEx(hdc, x, y, NULL); //Длина осей ???
//--------------------------------------------------------------------------------------------------------------------------------------------------------			
			int i;
			double koef;
			double cntx, cnty;
			
			//masshtabiruem po X i ustanavlivaem os Y otnositelno ekrana
//			if (b-a > x) {
				koef = x/(b-a);

				b *= koef;
				a *= koef;

				for (i = 0; i < n; ++i)
					xv[i] *= koef;

				//ustanavlivaem centr po X
				if (a <= 0 && b <= 0) {
					cntx = x + (-b);
				} else
				if (a <= 0 && b >= 0) {
					cntx = -a; //x-b
				} else
				if (a >= 0 && b >= 0) {
					cntx = -a;
				}
//			} else {
//				koef = (x - (b-a)) / 2;
//
//				if (a <= 0 && b <= 0) {
//					cntx = x + (-b) - koef;
//				} else
//				if (a <= 0 && b >= 0) {
//					cntx = koef + (-a);
//				} else
//				if (a >= 0 && b >= 0) {
//					cntx = -a + koef;
//				}
//			}
			//

			//masshtabiruem po Y i ustanavlivaem os X otnositelno ekrana
//			if (ymax - ymin > y) {
				koef = y/(ymax - ymin);
				
				for (i = 0; i < n; ++i)
					fxv[i] *= koef;

				for (i = 0; i <= k; ++i)
					Pfxv[i] *= koef;

				ymax *= koef;
				ymin *= koef;

				if (ymax <= 0 && ymin <= 0) {
					cnty = ymax;
				} else
				if (ymax >= 0 && ymin <= 0) {
					cnty = ymax;
				} else 
				if (ymax >= 0 && ymin >= 0) {
					cnty = y + ymin;
				}
//			} else {
//				koef = (y - (ymax - ymin))/2;
//
//				if (ymax <= 0 && ymin <= 0) {
//					cnty = ymax + koef;
//				} else
//				if (ymax >= 0 && ymin <= 0) {
//					cnty = ymax + koef;
//				} else 
//				if (ymax >= 0 && ymin >= 0) {
//					cnty = y + ymin - koef;	
//				}
//			}	
			//

			SetViewportOrgEx(hdc, cntx, cnty, NULL); //Начало координат			

			//risuem osi koordinat esli oni popadayut v pole videniya i otmetki na nix
			hPen=CreatePen(0,1,RGB(0,0,0));
			SelectObject(hdc, hPen);

			if (0 <= cnty && cnty <= y) {
				Line(hdc, a, 0, b, 0);
				for (i = (int)a; i <= (int)b; i+=25)
					Line(hdc, i, -2, i, 3);
			}

			if (0 <= cntx && cntx <= x) {
				Line(hdc, 0, (int)ymin, 0, (int)ymax);
				for (i = (int)ymin; i < (int)ymax; i+=25)
					Line(hdc, -2, i, 3, i);
			}
			//

			//risuem grafik zdes
			hPen=CreatePen(0,1,RGB(255,0,0));
			SelectObject(hdc, hPen);
			
			MoveToEx(hdc, (int)xv[0], (int)fxv[0], NULL);
			for (i = 1; i < n; ++i) {
				LineTo(hdc, (int)xv[i], (int)fxv[i]);
				Line(hdc, (int)xv[i], (int)(fxv[i]-5), (int)xv[i], (int)(fxv[i]+5));
				MoveToEx(hdc, (int)xv[i], (int)fxv[i], NULL);
			}

			hPen=CreatePen(0,1,RGB(0,0,255));
			SelectObject(hdc, hPen);

			MoveToEx(hdc, (int)a, (int)Pfxv[0], NULL);
			for(i = 1; i <= k; ++i){
				LineTo(hdc, (int)(a+i*(b-a)/k), (int)Pfxv[i]);
				Line(hdc, (int)(a+i*(b-a)/k), (int)(Pfxv[i]-5), (int)(a+i*(b-a)/k), (int)(Pfxv[i]+5));
				MoveToEx(hdc, (int)(a+i*(b-a)/k), (int)Pfxv[i], NULL);
			}
			//

			//pishem slova esli nado
			TextOut(hdc, (int)a, (int)ymax, TEXT("red - F; blue - PF"), 18);
			TextOut(hdc, (int)a, (int)(ymax-15), TEXT("interval na osyax = 25"), 22);
			sprintf(fstr, "%.5lf", YMAX);
			TextOut(hdc, (int)a, (int)(ymax-30), "Maximum funkcii : ", 18);
			TextOut(hdc, (int)a + 18*7, (int)(ymax-30), fstr, strlen(fstr));
			//nujno maximum vivesti
			//pishem slova esli nado
//--------------------------------------------------------------------------------------------------------------------------------------------------------			
			ValidateRect(hWnd, NULL); //Обновляем экран
			EndPaint(hWnd, &ps);
			break;
		case WM_DESTROY:
			if (hPen != NULL)
				DeleteObject(hPen); //не забываем уничтожать перья
			PostQuitMessage(0); //Посылаем сообщение выхода с кодом 0 - нормальное завершение
			break;
		default:
			return DefWindowProc(hWnd, message, wParam, lParam);//освобождаем очередь приложения от нераспознаных
	}

	return 0;
}