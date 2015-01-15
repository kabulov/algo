
#include "generator.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define PI 3.14159265358979323846

//function here 
double f(double x) {
	return sin(x);
}

int generate() {
	int p, k, n;
	double a, b;

	p = 0;
	n = 25;
	a = -2*PI;
	b = 2*PI;
	k = 200;

	double* xv = (double*)malloc(sizeof(double)*n);
	double* fxv = (double*)malloc(sizeof(double)*n);

	double eps = 1e-14;
	double cur = a, step = (b-a)/(n-1);		///!!!!!

	for (int i = 0; i < n; ++i) {
		xv[i] = cur;
		fxv[i] = f(cur);
		cur += step;
	}

	char* fname = "lss_14_21_in.txt";
	FILE* fout = fopen(fname, "w");

	fprintf(fout, "%d\n", p);
	fprintf(fout, "%d\n", n);

	for (int i = 0; i < n; ++i)
		fprintf(fout, "%.10lf %.10lf\n", xv[i], fxv[i]);

	fprintf(fout, "%.10lf\n", a);
	fprintf(fout, "%.10lf\n", b);
	fprintf(fout, "%d\n", k);

	fclose(fout);

	free(xv);
	free(fxv);

	return 0;
}