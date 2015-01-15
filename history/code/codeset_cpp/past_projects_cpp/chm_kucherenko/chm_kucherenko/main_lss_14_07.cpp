#pragma once

//#include "generator.h"
#include "lss_14_07.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define DEFAULT_INPUT_FILE_NAME "lss_14_07_in.txt"
#define DEFAULT_OUTPUT_FILE_NAME "lss_14_07_out.txt"

int debug = OFF,
	error = OFF,
	printmtx = OFF,
	printextm = OFF;

int usage(FILE* f) {
	fprintf(f, "Usage: main_lss_14_07 [input_file_name] [output_file_name] [options]\n");
	fprintf(f, "Where options include:\n");
	fprintf(f, "  -d    print debug messages [default OFF]\n");
	fprintf(f, "  -e    print errors [default OFF]\n");
	fprintf(f, "  -p    print matrix [default OFF]\n");
	fprintf(f, "  -t    print execution time [default OFF]\n");
	fprintf(f, "  -h, -?     print usage\n");
	fprintf(f, "Default input_file_name value is lss_14_07_in.txt\nDefault output_file_name value is lss_14_07_out.txt\n");
	return 0;
}

int inputfileformat(FILE* f) {
	fprintf(f, "n\n");
	fprintf(f, "a_1_1 a_1_2 ... a_1_n\n");
	fprintf(f, "a_2_1 a_2_2 ... a_2_n\n");
	fprintf(f, ".........\n");
	fprintf(f, "a_n_1 a_n_2 ... a_n_n\n");
	fprintf(f, "b_1\n");
	fprintf(f, "b_2\n");
	fprintf(f, "...\n");
	fprintf(f, "b_n\n");
	return 0;
}

size_t lss_memsize_14_07(int n) {
	return n * n;
}

int main(int argc, char** argv) {	

	char* input_file_name = DEFAULT_INPUT_FILE_NAME;
	char* output_file_name = DEFAULT_OUTPUT_FILE_NAME;

	if (argc > 1) {
		int i = 1;

		if (argv[1][0] != '-') {//input output file names exist 
			if (argc == 2 || argv[2][0] == '-') {
				fprintf(stderr, "clarify both input and output file names\n");
				usage(stderr);
				return -2;
			}
			
			input_file_name = argv[1];
			output_file_name = argv[2];
		
			i = 3;
		}
		
		for (; i < argc; ++i) {
			if (argv[i][0] != '-' || argv[i][1] == 0 || argv[i][2] != 0) {
				fprintf(stderr, "bad option %s\n", argv[i]);
				usage(stderr);
				return -2;
			}
			
			switch(argv[i][1]) {
			case 'd':
				debug = ON;
				break;
			case 'e':
				error = ON;	
				break;
			case 'p':
				printmtx = ON;
				break;
			case 't':
				printextm = ON;
				break;
			case 'h': 
			case '?':
				usage(stderr);
				return 0;//
			default:
				fprintf(stderr, "no such option %s\n", argv[i]);
				usage(stderr);
				return -2;//
			}
		}
	}

	if (debug == ON) {
		fprintf(stderr, "input file name %s\n", input_file_name);
		fprintf(stderr, "output file name %s\n", output_file_name);
	}

	FILE* input = NULL;
	FILE* output = NULL;

	input = fopen(input_file_name, "r");	//here is the problem

	if (input == NULL) {
		fprintf(stderr, "Error: could not open input file %s\n", input_file_name);//if error nujno? 
		fclose(input);
		return -1;
	}

	output = fopen(output_file_name, "w");	//here is the problem

	if (output == NULL) {
		fprintf(stderr, "Error: could not open output file %s\n", output_file_name);//if error nujno? 
		fclose(output);
		return -1;
	}
	
	int n;
	
	double* A;
	double* B;
	double* X;
	double* tmp;

	if (fscanf(input, "%d", &n) != 1) {
		if (error == ON) {
			fprintf(stderr, "bad input file format\n");
			inputfileformat(stderr);
		}

		fclose(input);
		fclose(output);
		return -3;
	}	

	if (n<1) {
		if (error == ON){
			fprintf(stderr, "n must be > 0\n");
		}
		fclose(input);
		fclose(output);
		return -3;
	}

	A = (double*)malloc(sizeof(double)*n*n);
	B = (double*)malloc(sizeof(double)*n);
	X = (double*)malloc(sizeof(double)*n);

	size_t size = lss_memsize_14_07(n);
	tmp = (double*)malloc(sizeof(double)*size);
	
	if (A == NULL || B == NULL || X == NULL || tmp == NULL) {
		if (error == ON)
			fprintf(stderr, "Error : memory allocation error\n");//
		fclose(input);
		fclose(output);
		return -4;
	}

	int i, j;
	for (i = 0; i < n; ++i) {
		for (j = 0; j < n; ++j) 
			if (fscanf(input, "%lf", &A[i*n+j]) != 1) {
				if (error == ON) {
					fprintf(stderr, "A : bad input file format\n");
					inputfileformat(stderr);
				}

				fclose(input);
				fclose(output);
				return -3;
			}
	}

	if (printmtx == ON) {
		//vivodit v stdout ili v output?
		FILE* prn = stdout; // prn = output ? 
		fprintf(prn, "input matrix\n");
		fprintf(prn, "%d\n", n);
		for (i = 0; i < n; ++i) {
			for (j = 0; j < n; ++j)
				fprintf(prn, "%1.9lf ", A[i*n+j]);
			fprintf(prn, "\n");
		}
		fprintf(prn, "\n");
	}

	for (i = 0; i < n; ++i) {
		if (fscanf(input, "%lf", &B[i]) != 1) {
			if (error == ON) {
				fprintf(stderr, "B : bad input file format\n");
				inputfileformat(stderr);
			}

			fclose(input);
			fclose(output);
			return -3;
		}
	}

	time_t tm;
	
	if (printextm == ON) 
		tm = clock();

	int ans = lss_14_07(n, A, B, X, tmp);
	
	if (printextm == ON) {
		FILE* prn = stdout; //?output?
		fprintf(prn, "execution time : %d\n", clock() - tm);
		//ili po drugomu?
	}

	if (ans == -1) {
		fprintf(output, "0\n");
	} else {//ans == 0
		fprintf(output, "%d\n", n);
		for (i = 0; i < n; ++i) 
			fprintf(output, "%1.9lf\n", X[i]);
	}

	free(A);
	free(B);
	free(X);
	free(tmp);

	fclose(input);
	fclose(output);

	return 0;
}
