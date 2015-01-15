
#include <stdlib.h>
#include "lss_14_21.h"

//n>=1
//mojet li sistema ne imet resheniya?
int pf_koef(int n, double* x, double* fx, double* koef, double* e) {
	int size = n+1;
	int type = sizeof(double);

	//ksi[i] - opredelyaem
	
	e[0] = x[0] - (x[1] - x[0]);
	e[n] = x[n-1] + (x[n-1] - x[n-2]);

	int i;
	for (i = 1; i < n; ++i) {
		e[i] = (x[i-1] + x[i]) / 2;
	}

	double* mid = (double*)malloc(type * (n+1));

	mid[0] = fx[0] - (fx[1] - fx[0]);
	mid[n] = fx[n-1] + (fx[n-1] - fx[n-2]);

	if (n == 1) return 0; //

	double* A = (double*)malloc(type * size * size);
	//koef = malloc(type * size); pod koef razmer videlen snaruji!

	//inicializiruem A i B(B==koef)
	int j;
	for (i = 0; i < n+1; ++i)
		for (j = 0; j < n+1; ++j)
			A[i*(n+1)+j] = 0;

	A[0] = 1;
	A[n*(n+1)+n] = 1;

	for (i = 1; i < n; ++i) {
		A[i*(n+1)+i-1] = 1/(x[i-1] - e[i-1]) - 1/(e[i] - e[i-1]);
		A[i*(n+1)+i] = 1/(e[i] - x[i-1]) + 1/(e[i] - e[i-1]) + 1/(x[i] - e[i]) + 1/(e[i+1] - e[i]);
		A[i*(n+1)+i+1] = 1/(e[i+1] - x[i]) - 1/(e[i+1] - e[i]);

		mid[i] = (1/(x[i-1] - e[i-1]) + 1/(e[i] - x[i-1]))*fx[i-1]+(1/(x[i] - e[i]) + 1/(e[i+1] - x[i]))*fx[i];
	}

	//pryamoy xod gaussa(bez vibora vedushego elementa, tak kak matrica so strogim diagonalnim preobladaniem)
	mid[1] -= mid[0] * A[1*(n+1)+0];
	A[1*(n+1)+0] = 0;
	
	for (i = 1; i < n - 1; ++i) {
		mid[i] /= A[i*(n+1)+i];
		A[i*(n+1)+i+1] /= A[i*(n+1)+i];
		A[i*(n+1)+i] = 1;

		mid[i+1] -= mid[i] * A[(i+1)*(n+1)+i];
		A[(i+1)*(n+1)+i+1] -= A[i*(n+1)+i+1] * A[(i+1)*(n+1)+i];
		A[(i+1)*(n+1)+i] = 0;
	}

	mid[n-1] /= A[(n-1)*(n+1)+n-1];
	A[(n-1)*(n+1)+n] /= A[(n-1)*(n+1)+n-1];
	A[(n-1)*(n+1)+n-1] = 1;

	//obratniy xod metoda gaussa
	for (i = n - 1; i > 0; --i)
		mid[i] -= A[i*(n+1)+i+1] * mid[i+1];
	//v massive mid lejat koefficienti vi iz teorii, razmer koef - n+1

	for (i = 0; i < n; ++i) {
		koef[i*3 + 0] = mid[i];
		koef[i*3 + 1] = (fx[i] - mid[i]) / (x[i] - e[i]) - 
			            (x[i] - e[i]) / (e[i+1] - e[i]) * ((mid[i+1] - fx[i]) / (e[i+1] - x[i]) - (fx[i] - mid[i]) / (x[i] - e[i]));
		koef[i*3 + 2] = ((mid[i+1] - fx[i]) / (e[i+1] - x[i]) - (fx[i] - mid[i]) / (x[i] - e[i])) / (e[i+1] - e[i]);
	}

	return 0;
}

double* pf_val(double* koef, double* e, double a, double b, int k) {
	double* answer = (double*)malloc(sizeof(double) * (k+1));

	double eps = 1e-14;
	double step = (b-a)/k;
	double cur = a;

	int pos = 0;
	int i;

	for (i=0 ; i <= k ; i++) {
		if (cur > e[pos+1] + eps)
			++pos;

		answer[i] = koef[3*pos+0] + koef[3*pos+1]*(cur - e[pos]) + koef[3*pos+2]*(cur - e[pos])*(cur - e[pos]);
		cur += step;
	}

	return answer;
	//answer must be freed outside
}