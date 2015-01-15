
#include <stdlib.h>
#include <stdio.h>
#include "lss_14_21.h"
#include "main.h"
#include "generator.h"

//0 well done
//-1 bad option error
//-2 io file error
//-3 io format error
//ans is ordered
int solve(char* ifn, char* ofn) {
	generate();

	//global
	int upper_bound = 1000;
	int type = sizeof(double);
	
	FILE *fin, *fout;

	if ((fin = fopen(ifn, "r")) == NULL) {
		fprintf(stderr, "cannot open input file\n");
		return -2;
	}

	if ((fout = fopen(ofn, "w")) == NULL) {
		fprintf(stderr, "cannot open output file\n");
		return -2;
	}

	int p;
	if (fscanf(fin, "%d", &p) != 1) {
		fprintf(stderr, "no parameter p\n");
		return -3;
	};

	if (p != 0) {
		fprintf(stderr, "no additional values needed(must be 0)\n");
		return -3;
	}

	int n;
	if (fscanf(fin, "%d", &n) != 1) {
		fprintf(stderr, "no parameter n\n");
		return -3;
	}

	//n>=1 !!
	if (n < 1 || n > upper_bound) {
		fprintf(stderr, "must be 1 < n < %d", upper_bound);
		return -3;
	}

	double* x = (double*)malloc(type * n);
	double* fx = (double*)malloc(type * n);

	int i;
	for (i = 0; i < n; ++i) {
		if (fscanf(fin, "%lf %lf", &x[i], &fx[i]) != 2) {
			fprintf(stderr, "io error\n");
			return -3;
		}
	}

	double a, b;
	int k;
	
	if (fscanf(fin, "%lf", &a) != 1) {
		fprintf(stderr, "no parameter a\n");
		return -3;
	}

	if (fscanf(fin, "%lf", &b) != 1) {
		fprintf(stderr, "no parameter b\n");
		return -3;
	}

	if (a > b) {
		fprintf(stderr, "must be a < b\n");
		return -3;
	}

	if (fscanf(fin, "%d", &k) != 1) {
		fprintf(stderr, "no parameter k\n");
		return -3;
	}

	if (k < 1) {
		fprintf(stderr, "must be k > 0\n");
		return -3;
	}

	double* e = (double*)malloc(type * (n+1));
	double* koef = (double*)malloc(type * 3 * n);
	
	pf_koef(n, x, fx, koef, e); //if check return value?
	//always have solution?

	double* ans = pf_val(koef, e, a, b, k);

	for (i = 0; i <= k; ++i)
		fprintf(fout, "%1.10lf\n", ans[i]);

	free(ans);
	free(x);
	free(fx);
	free(e);
	free(koef);

	fclose(fin);
	fclose(fout);

	return 0;
}

