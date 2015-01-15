#pragma once

#include "lss_14_07.h"
#include <math.h>
#include <stdio.h> 

// 0 - работа завершена успешно, решение построено
//-1 - метод решения не применим к данной системе 
//tmp = (double*)malloc(sizeof(double)*n*n)

int lss_14_07(int n, double* A, double* B, double* X, double* tmp) {
	extern int 
		debug,
		error,
		printmtx,
		printextm;

	double eps = 1e-14;

	int i, j;
	
	//kopirovanie : A -> tmp
	for (i = 0; i < n; ++i) {
		for (j = 0; j < n; ++j) {
			tmp[i*n+j] = A[i*n+j];	
		}
	}		

	if (debug == ON) {
		fprintf(stderr, "matrix A:\n");
		for (i = 0; i < n; ++i) {
			for (j = 0; j < n; ++j)
				fprintf(stderr, "%1.9lf ", A[i*n+j]);
			fprintf(stderr, "\n");
		}
	}

	int p, k;
	
	//proverka na primenimost metoda
	//cherez naxojdenie opredelitelya 
	//po metodu gaussa 
	//s viborom glavnogo elementa
	//po stolbcu
	for (p = 0; p < n; ++p) {
		int m = p;
		
//		for (k = p + 1; k < n; ++k)
//			if (fabs(tmp[k*n+p]) > fabs(tmp[m*n+p]))
//				m = k;

		if (fabs(tmp[m*n+p]) < eps) {
			return -1;
			//metod ne primenim
		}

//		if (m != p) {
//			double buf;
//			for (k = 0; k < n; ++k) {
//				buf = tmp[p*n+k];
//				tmp[p*n+k] = tmp[m*n+k];
//				tmp[m*n+k] = buf;
//
//				buf = A[p*n+k];//!
//				A[p*n+k] = A[m*n+k];//!
//				A[m*n+k] = buf;//!
//			}
//
//			buf = B[p];//!
//			B[p] = B[m];//!
//			B[m] = buf;//!
//		}

		for (k = n - 1; k >= p; --k)
			tmp[p*n+k] /= tmp[p*n+p];

		for (i = p + 1; i < n; ++i)
			for (j = n - 1; j >= p; --j)
				tmp[i*n+j] -= tmp[p*n+j]*tmp[i*n+p]; 
	}

//	if (debug == ON) {
//		fprintf(stderr, "matrix A, determinant calculated:\n");
//		for (i = 0; i < n; ++i) {
//			for (j = 0; j < n; ++j)
//				fprintf(stderr, "%1.9lf ", A[i*n+j]);
//			fprintf(stderr, "\n");
//		}
//	}

	//A->LU
	for (j = 0; j < n; ++j) {
		for (i = j; i < n; ++i) {
			for (p = 0; p < j; ++p)
				A[i*n+j] -= A[i*n+p] * A[p*n+j];
		}

		for (k = j + 1; k < n; ++k) {
			for (p = 0; p < j; ++p)
				A[j*n+k] -= A[j*n+p]*A[p*n+k];
			A[j*n+k] /= A[j*n+j];
		}
	}

	if (debug == ON) {
		fprintf(stderr, "LU matrix :\n");
		for (i = 0; i < n; ++i) {
			for (j = 0; j < n; ++j)
				fprintf(stderr, "%1.9lf ", A[i*n+j]);
			fprintf(stderr, "\n");
		}
	}

	//LX==B
	for (j = 0; j < n; ++j) {
		X[j] = B[j] / A[j*n+j];
		for (i = j + 1; i < n; ++i)
			B[i] -= X[j] * A[i*n+j];
	}
	
	if (debug == ON) {
		fprintf(stderr, "LY == B equation solved, Y vector : \n");
		for (i = 0; i < n; ++i) {
			fprintf(stderr, "%1.9lf\n", X[i]);
		}
	}

	//UX==X
	for (j = n - 1; j >= 0; --j) {
		for (i = j - 1; i >= 0; --i)
			X[i] -= X[j]*A[i*n+j];
	}
	
	if (debug == ON) {
		fprintf(stderr, "UX == Y equation solved, X vector : \n");
		for (i = 0; i < n; ++i) {
			fprintf(stderr, "%1.9lf\n", X[i]);
		}
	}

	return 0;
}

/*
1.1 tmp = A;
1.2 esli rang A == 0 to metod ne primenim : return -1;
2.1 razlagaem A na LU;	!pryam tam je(v A)
2.2 reshaem LX == B; 
2.3 reshaem UX == X; !pryam tam je(v X)
*/