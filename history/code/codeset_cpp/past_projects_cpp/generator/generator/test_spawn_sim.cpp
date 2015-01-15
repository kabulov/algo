/*
Random non-degenerate symmetric matrix spawner
Author: Igor Kucherenko <kucherenko@intsys.msu.ru>
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_DIAG_VALUE 7
#define MIN_ADIAG_VALUE -3
#define MAX_ADIAG_VALUE 3
#define MIN_RIGHT_VALUE -31
#define MAX_RIGHT_VALUE 31

int main(int argc, char** argv)
{
	int i, j, k;
	double r;

	int N; // system dimension
	double* A; // matrix array
	double* A_s; // matrix array

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	scanf("%d", &N);

	//if (argc != 2) {
	//	fprintf(stderr, "Wrong usage!\n");
	//	fprintf(stderr, "Usage: %s dimension\n", argv[0]);
	//	return __LINE__;
	//}

	//if (sscanf(argv[1],"%d",&N) != 1) {
	//	fprintf(stderr, "Bad first parameter!\n");
	//	fprintf(stderr, "Usage: %s dimension\n", argv[0]);
	//	return __LINE__;
	//}

	if (N < 2) {
		fprintf(stderr, "First parameter is too small (%d)!\n",N);
		return __LINE__;
	}

	if (N > 1024) {
		fprintf(stderr, "First parameter is too big (%d)!\n",N);
		return __LINE__;
	}

	// allocating memory

	i = N*N*sizeof(double);
	if ((A = (double*)malloc(i)) == NULL) {
		fprintf(stderr, "Error: could not allocate %d bytes of memory!\n",i);
		fcloseall();
		return __LINE__;
	}

	i = N*N*sizeof(double);
	if ((A_s = (double*)malloc(i)) == NULL) {
		fprintf(stderr, "Error: could not allocate %d bytes of memory!\n",i);
		fcloseall();
		return __LINE__;
	}

	// seeding PRNG

	srand((unsigned)time(0));

	// spawning upper-triangle matrix

	// diagonal values
	for (i = 0; i < N; ++ i) {
		r = (rand() % MAX_DIAG_VALUE) + 1;
		if (rand() % 2) {
			r *= -1;
		}
		A[i*(N+1)] = r;
	}

	// under diagonal
	for (i = 1; i < N; ++ i) {
		for (j = 0; j < i; ++ j) {
			A[N*i + j] = 0;
		}
	}

	// above diagonal
	for (i = 0; i < N; ++ i) {
		for (j = i + 1; j < N; ++ j) {
		   r = (rand() % (MAX_ADIAG_VALUE - MIN_ADIAG_VALUE + 1)) + MIN_ADIAG_VALUE;
			A[N*i + j] = r;
		}
	}

	// multiplying matrix by it transposed matrix

	for (i = 0; i < N; ++ i) {
		for (j = 0; j < N; ++ j) {
			A_s[N*i + j] = 0;
			for (k = 0; k < N; ++ k) {
				A_s[N*i + j] += A[N*i + k]*A[N*j + k];
			}
		}
	}


	// printing result
	printf("%d\n",N);
	for (i = 0; i < N; ++ i) {
		printf("%1.0lf",A_s[i*N]);
		for (j = 1; j < N; ++ j) {
			printf(" %1.0lf",A_s[i*N + j]);
		}
		printf("\n");
	}

	// freeing memory
	free(A);
	free(A_s);

	return 0;
}

