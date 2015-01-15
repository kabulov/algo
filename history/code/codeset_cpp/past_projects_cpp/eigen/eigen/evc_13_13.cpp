
#include "task_13_13.h"

#include <math.h>
#include <stdio.h>

//videlyaem pamyat
int evc_memsize_13_13(int n){
	int result = 0 * sizeof(double);

	extern bool Debug;

	if (Debug) {
		fprintf(stdout, "\nDebug : evc_memsize_13_13\n");
		fprintf(stdout, "videlyaemiy razmer raven %d\n", result);
	}

	return result; //nichego ne videlyaem
}

//-1 esli metod ne primenim , no etot sluchay uje proveren v sim_13_13
//1 esli metod ne sxoditsya za max_iterations(>0) shagov
//0 uspex
int evc_13_13(int n, int max_iterations, double epsilon, double* A, double* B, double* E, double* tmp, double precision){
		
	extern bool Debug;

	if (n < 2) {
		if (Debug) {
			fprintf(stdout, "\nDebug : evc_13_13\n razmer matrici doljen bit > 1\n");
		}
		
		return -1; //nikogda ne vipolnit etot if izza togo chto v sim_13_13 uje provereno
	}

	//tekushiy razmer i tekushaya iteraciya
	int cur_size = n, cur_iter = 0, i;	
	//sdvig, tekushie i predidushie sinusi i kosinusi povorotov
	double sdvig, buf, sn_cur, cs_cur, sn_prev = 0, cs_prev = 0, bi_prev; //inic ne obyazat, prosto inache budet rugatsa

	if (Debug) {
		fprintf(stdout, "\nDebug : evc_13_13\n nachinaem QR algoritm so sdvigami naxojdeniya sobstvennix znacheniy matrici\n");
	}

	//otvet v cikle pixaem v E, pri umenshenii razmera matrici
	while ((max_iterations <= 0 || cur_iter < max_iterations) && cur_size > 2) {

		if (Debug) {
			fprintf(stdout, "\n shag nomer %d\n", cur_iter);
		}

		//opredelyaem sdvig(stranica 130, bogachev)
		sdvig = A[cur_size - 1]; //po bogachevu : {a^k}_nn
		
		if (Debug) {
			fprintf(stdout, "\n sdvig raven : %1.9lf\n", sdvig);
		}

		//QR razlojenie matrici Ak - sdvig * I : Ak - sdvig * I = QkRk, 
		//t.k. trexdiagonalnaya samosopryajennaya matrica, to srazu tut je poluchaem Ak+1
		
		if (Debug) {
			fprintf(stdout, "otnimaem ot pervix %d diagonalnix elementov tekushiy sdvig\n", cur_size);
		} 

		for (i = 0; i < cur_size; ++i) {
			A[i] -= sdvig;
			//vot tut to mogut vozniknut problemi s poslednim elementom(otricatelniy ili polojitelniy? obyazatelno li delat polojitelnim)?
			//t.k. v teoreme 12.1 o razlojenii matrica R imeet polojitelnie elementi na diagonali
		}

		//tut

		if (Debug) {
			fprintf(stdout, "proizvodim QR razlojenie, i poluchaem RQ\n");
		}

		//otstuplenie-!!
		for (i = 0; i + 1 < cur_size; ++i) {//n - 1 shagov
			buf = sqrt(A[i] * A[i] + (i == 0 ? B[0] * B[0] : bi_prev * bi_prev));
			if (abs(buf) < precision) {	//?? ili che nibud eshe
				buf = 1;
			}

			cs_cur = A[i] / buf; //poryadok A[i], B[i] vajen!!
			sn_cur = (i == 0 ? -B[0] : -bi_prev) / buf; //takje
			
			A[i] = cs_cur * A[i] - sn_cur * (i == 0 ? B[0] : bi_prev);	

			buf = A[i + 1];
			A[i + 1] = sn_cur * B[i] + cs_cur * buf;
			
			if (i + 2 < n) {
				bi_prev = B[i + 1]; //izza togo chto xranyu ne 2 lenti pobochnie a vsego 1, shamanskie tryuki...
				B[i + 1] *= cs_cur;
			}
			
			B[i] = cs_cur * B[i] - sn_cur * buf; 

			if (i > 0) {//i tut ochen somnitelnoe predpolojenie 
				//umnojaem teper na transponirovannuyu matricu
				A[i - 1] = cs_prev * A[i - 1] - sn_prev * B[i - 1];
				B[i - 1] = -sn_prev * A[i];
				A[i] *= cs_prev;
			}

			cs_prev = cs_cur;
			sn_prev = sn_cur;
		}
		
		//bool flag = false; ??smutnoidiotskoe oshushenie
		//if (A[cur_size - 1] < 0) {flag = true;} ??
		//sn_prev i cs_prev inicializirovani t.k. cur_size > 2 bilo kogda voshli v cikl
		//domnojaem na tn-1n, 'i' posle cikla ravno cur_size - 1
		//i tut ochen somnitelnoe pedpolojenie
		//umnojaem teper na transponirovannuyu matricu
		A[i - 1] = cs_prev * A[i - 1] - sn_prev * B[i - 1];
		B[i - 1] = -sn_prev * A[i];
		A[i] *= cs_prev;

		//otstuplenie-!!

		//i tut		

		//Ak+1 = RkQk + sdvigk
		for (i = 0; i < cur_size; ++i) {
			A[i] += sdvig;
		}

		if (Debug) {
			fprintf(stdout, "teper pribavlyaem k %d pervim diagonalnim elementam poluchivsheysa matrici sdvig\n", cur_size);
		}

		if (Debug) {
			fprintf(stdout, "novaya matrica :\n");
			fprintf(stdout, "diagonalnie elementi:\n");
			int ind;
			for (ind = 0; ind < n; ++ind) {
				fprintf(stdout, "%1.9lf ", A[ind]);
			}
			fprintf(stdout, "\n");
			fprintf(stdout, "poddiagonalnie elementi:\n");
			for (ind = 0; ind + 1 < n; ++ind) {
				fprintf(stdout, "%1.9lf ", B[ind]);
			}
			fprintf(stdout, "\n");
		}

		//vichislyaem normu ||A||8(beskonechnost) , xranim v peremennoy sdvig, t.k. ona uje ne nujna
		sdvig = abs(A[0]) + abs(B[0]);
		if (abs(A[cur_size - 1]) + abs(B[cur_size - 2]) > sdvig) {
			sdvig = abs(A[cur_size - 1]) + abs(B[cur_size - 2]);
		}
		
		for (i = 1; i + 1 < cur_size; ++i) {
			buf = abs(A[i]) + abs(B[i]) + abs(B[i - 1]);
			if (buf > sdvig) sdvig = buf; //vibor maximuma
		}

		if (Debug) {
			fprintf(stdout, "maksimalnaya strochnaya norma(||A||8) ravna : %1.9lf\n", sdvig);	
		}

		//umenshaem razmer esli nado , bogachev, stranica 130
		if (abs(B[cur_size - 2]) < epsilon * sdvig) {
			E[cur_size - 1] = A[cur_size - 1];
			--cur_size;

			if (Debug) {
				fprintf(stdout, "nashlos %d-e sobstvennoe znachenie : %1.9lf\n", n - cur_size, E[cur_size]);
			}
		}

		++cur_iter; 
	}

	if (cur_size > 2) {//znachit cur_iter == max_iterations
		if (Debug) {
			fprintf(stdout, "\nDebug : evc_13_13\n");
			fprintf(stdout, "tekushiy shag = %d;\n kolichestvo nevichislennix sobstvennix znacheniy = %d\n");
			fprintf(stdout, "metod ne soshelsya za ukazannoe chislo shagov\n");
		}

		return 1;// metod ne soshelsya
		//E[cur_size - 1] = A[cur_size - 1];//bug
	}

	if (evc_13_13_2(A[0], B[0], B[0], A[1], E[0], E[1]) == -1) {
		//poka suda ne zaxodim
		return -1;//cheto ni tak
	}

	//tut odna tonkost : daje esli cur_iter == max_iterations, schitayu chto metod soshelsya t.k. reshit matricu 2X2 polagayu 0 shagov
	evc_13_13_sort(n, E);

	return 0;
}

//0 uspex
//-1 ploxoy vxod, rugaemsa
int evc_13_13_2(double a11, double a12, double a21, double a22, double& e1, double& e2) {//mojno li ssilki ispolzovat
		
	//rassmatrivayutsya tolko veshestvennie simmetrichnie nevirojdennie matrici -> d >= 0
	double d = sqrt((a11 - a22) * (a11 - a22) + 4 * a12 * a21); //vozmojno nujno +eps ochen malenkiy sdelay
	e1 = ((a11 + a22) - d) / 2;
	e2 = ((a11 + a22) + d) / 2;

	extern bool Debug;

	if (Debug) {
		fprintf(stdout, "\nDebug : evc_13_13_2\n");
		fprintf(stdout, "xarakteristicheskiy mnogochlen : x^2 - (a11 + a22)*x + (a11*a22 - a12*a21)\n");
		fprintf(stdout, "a11 + a22 = %1.9lf; (a11*a22 - a12*a21) = %1.9lf;\n", a11 + a22, a11 + a22 - a12*a21);
	}

	return 0;
}
 
//sortirovka elementov
int evc_13_13_sort(int n, double* E) {
	//tut mojno bilo bi zapixnut bistruyu sortirovku, no n ne tak uj i bolshoe
	int i, j, pos;
	double buf;

	for (i = 0; i < n; ++i) {
		pos = i;
		for (j = i + 1; j < n; ++j)
			if (E[j] < E[pos])
				pos = j;

		if (pos > i) {
			buf = E[pos];
			E[pos] = E[i];
			E[i] = buf;
		}
	}

	return 0;
}
