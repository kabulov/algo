/*
Random non-degenerate linear system spawner
Author: Igor Kucherenko <ivkmailbox@gmail.com>
*/

#include "generator.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_DIAG_VALUE 7
#define MIN_ADIAG_VALUE -3
#define MAX_ADIAG_VALUE 3
#define MIN_RIGHT_VALUE -31
#define MAX_RIGHT_VALUE 31

int generate(/*int argc, char** argv*/int inp)
{
	int i, j, k;
	double r;

	int N = inp; // system dimension
	double* A_1; // upper-triangle matrix array
	double* A_2; // lower-triangle matrix array
	// optimization possible - we can pack A_1 and A_2 into one array
	double* A; // matrix array
	double* B; // right part array

	// parsing command arguments
/*
	if (argc != 2) {
		fprintf(stderr, "Wrong usage!\n");
		fprintf(stderr, "Usage: %s dimension\n", argv[0]);
		return __LINE__;
	}

	if (sscanf(argv[1],"%d",&N) != 1) {
		fprintf(stderr, "Bad first parameter!\n");
		fprintf(stderr, "Usage: %s dimension\n", argv[0]);
		return __LINE__;
	}

	if (N < 2) {
		fprintf(stderr, "First parameter is too small (%d)!\n",N);
		return __LINE__;
	}

	if (N > 1024) {
		fprintf(stderr, "First parameter is too big (%d)!\n",N);
		return __LINE__;
	}

	// allocating memory
*/
	i = N*N*sizeof(double);
	if ((A = (double*)malloc(i)) == NULL) {
		fprintf(stderr, "Error: could not allocate %d bytes of memory!\n",i);
		fcloseall();
		return __LINE__;
	}

	if ((A_1 = (double*)malloc(i)) == NULL) {
		fprintf(stderr, "Error: could not allocate %d bytes of memory!\n",i);
		fcloseall();
		return __LINE__;
	}

	if ((A_2 = (double*)malloc(i)) == NULL) {
		fprintf(stderr, "Error: could not allocate %d bytes of memory!\n",i);
		fcloseall();
		return __LINE__;
	}

	i = N*sizeof(double);
	if ((B = (double*)malloc(i)) == NULL) {
		fprintf(stderr, "Error: could not allocate %d bytes of memory!\n",i);
		fcloseall();
		return __LINE__;
	}

	// seeding PGNG

	srand((unsigned)time(0));

	// spawning right part

	for (i = 0; i < N; ++ i) {
		r = (rand() % (MAX_RIGHT_VALUE - MIN_RIGHT_VALUE + 1)) + MIN_RIGHT_VALUE;
		B[i] = r;
	}

	// spawning upper-triangle matrix

	// diagonal values
	for (i = 0; i < N; ++ i) {
		r = (rand() % MAX_DIAG_VALUE) + 1;
		if (rand() % 2) {
			r *= -1;
		}
		A_1[i*(N+1)] = r;
	}

	// under diagonal
	for (i = 1; i < N; ++ i) {
		for (j = 0; j < i; ++ j) {
			A_1[N*i + j] = 0;
		}
	}

	// above diagonal
	for (i = 0; i < N; ++ i) {
		for (j = i + 1; j < N; ++ j) {
		   r = (rand() % (MAX_ADIAG_VALUE - MIN_ADIAG_VALUE + 1)) + MIN_ADIAG_VALUE;
			A_1[N*i + j] = r;
		}
	}

	// spawning lower-triangle matrix

	// diagonal values
	for (i = 0; i < N; ++ i) {
		A_2[i*(N+1)] = 1;
	}

	// under diagonal
	for (i = 1; i < N; ++ i) {
		for (j = 0; j < i; ++ j) {
		   r = (rand() % (MAX_ADIAG_VALUE - MIN_ADIAG_VALUE + 1)) + MIN_ADIAG_VALUE;
			A_2[N*i + j] = r;
		}
	}

	// above diagonal
	for (i = 0; i < N; ++ i) {
		for (j = i + 1; j < N; ++ j) {
			A_2[N*i + j] = 0;
		}
	}

	// multiplying lower and upper triangle matrices

	for (i = 0; i < N; ++ i) {
		for (j = 0; j < N; ++ j) {
			A[N*i + j] = 0;
			for (k = 0; k < N; ++ k) {
				A[N*i + j] += A_2[N*i + k]*A_1[N*k + j];
			}
		}
	}

	// printing result

	FILE* fout = fopen("lss_14_07_in.txt", "w");

	fprintf(fout, "%d\n",N);
	for (i = 0; i < N; ++ i) {
		fprintf(fout, "%1.0lf",A[i*N]);
		for (j = 1; j < N; ++ j) {
			fprintf(fout, " %1.0lf",A[i*N + j]);
		}
		fprintf(fout, "\n");
	}
	for (i = 0; i < N; ++ i)
		fprintf(fout, "%1.0lf\n",B[i]);

	fclose(fout);
	// freeing memory

	free(A);
	free(A_1);
	free(A_2);
	free(B);

	return 0;
}

