
#include "task_13_13.h"

#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

const int MAX_MATR_SIZE = 1000; //maksimalniy razmer obrabativaemoy matrici

bool Debug;
bool Error;

bool Time;
bool Print;

int max_iter;

double prec;
double eps;

time_t time_begin, time_end;

//vliyanie etix parametrov na progu eshe ne ralizovano!! -- ??
void init() {
	prec = 1e-14;
	eps = 1e-10;
	max_iter = 0;
	Time = false;
	Print = false;
	Debug = false;
	Error = false;
}

void usage() {
	fprintf(stdout, "\n");
	fprintf(stdout, "Usage: evc input_file_name [default - input.txt] output_file_name [default - output.txt] [options] \n");
	fprintf(stdout, "Where options include: \n");
	fprintf(stdout, " -d    print debug messages [default OFF] \n");
    fprintf(stdout, " -e    print errors [default OFF]");
    fprintf(stdout, " -p    print matrix [default OFF]");
    fprintf(stdout, " -t    print execution time [default OFF]");
    fprintf(stdout, " -prec=<num>       precision [default - 1e-14] "); //prinimaet tolko stepeni e v vide celogo chisla
    fprintf(stdout, " -eps=<num>        'epsilon' [default - 1e-10]"); //prinimaet tolko stepeni e v vide celogo chisla
    fprintf(stdout, " -max_iter=<num>   limit number of iterations [default - 0, i.e. not limit]"); //poka otricatelnie ne obrabativaet
    fprintf(stdout, " -h, -?     print this and exit");
    fprintf(stdout, "\n");
}

//true - esli parametr ploxoy
//false - esli parametr xoroshiy
bool is_unknown_param(char* param) {
	//if smth return true
	if (param[0] != '-') return true; //ne nachinetsa s defisa
	if (param[1] == '\0') return true;//dlina == 1
	if (param[1] != 'd' && param[1] != 'e' && param[1] != 'p' && param[1] != 't' && param[1] != 'm' && param[1] != 'h' && param[1] != '?')
		return true;

	if (param[2] == '\0') return false;
	
	int len = 0, j;	
	for (j = 1; param[j] != '\0'; ++j) {
		++len;
	}

	if (len >= 10 && param[1] == 'm' && param[2] == 'a' && param[3] == 'x' && param[4] == '_' && param[5] == 'i' && param[6] == 't' && param[7] == 'e' && param[8] == 'r' && param[9] == '=') {
		if (param[10] == '-') {
			if (len == 10) return true;
			if (param[11] == '\0') return true;
			for (j = 11; param[j] != '\0'; ++j) 
				if (!('0' <= param[j] && param[j] <= '9')) 
					return true;
		} else {
			for (j = 10; param[j] != '\0'; ++j) 
				if (!('0' <= param[j] && param[j] <= '9')) 
					return true;
		}
	} else
	if (len >= 8 && param[1] == 'p' && param[2] == 'r' && param[3] == 'e' && param[4] == 'c' && param[5] == '=' && param[6] == 'e' && param[7] == '-') {		
		for (j = 8; param[j] != '\0'; ++j) 
			if (!('0' <= param[j] && param[j] <= '9')) 
				return true;
	} else 
	if (len >= 7 && param[1] == 'e' && param[2] == 'p' && param[3] == 's' && param[4] == '=' && param[5] == 'e' && param[6] == '-') {	
		for (j = 7; param[j] != '\0'; ++j) 
			if (!('0' <= param[j] && param[j] <= '9')) 
				return true;
	} else
		return true;

	return false;
}

//1 print usage and exit
//-1 takogo parametra ne sushestvuet
// 0 vse xorosho
int parse_cmd(int argc, char** argv) {
	//vozmojni povtori parametrov : esli kakoyto parametr povtoryaetsa(naprimer : '-d -d') to prinimaetsa posledniy
	int i, j;
	bool neg = false;
	
	for (i = 3; i < argc; ++i) {
		//sintaksicheskiy analiz
		if (is_unknown_param(argv[i])) {
			return -i;
		}
		
		//schitayu chto esli parametr sintaksicheski xorosh, to znacheniya ego v dopustimix intervalax, 
		//inache proverka etogo potrebuet zverski bolshogo parsera
		
		//semanticheskiy analiz
		switch(argv[i][1]) {
			case 'd':
				Debug = true;
				break;
			case 't':
				Time = true;
				break;
			case 'm':
				max_iter = 0;
				j = 10;
				if (argv[i][j] == '-') {
					neg = true;
					++j;
				}
				for (; argv[i][j] != '\0'; ++j) {
					max_iter = max_iter * 10 + argv[i][j] - '0';
				}
				if (neg) max_iter = -max_iter;
				break;
			case 'h': case '?':
				return 1;
			case 'p':
				if (argv[i][2] == '\0') {
					Print = true;
				} else {
					//schitayu chto zadaetsya v vide e-chislo
					int number = 0;
					for (j = 8; argv[i][j] != '\0'; ++j) {
						number = number * 10 + argv[i][j] - '0';
					}

					prec = 1;
					for (j = 0; j < number; ++j) {
						prec /= 10.0;
					}
				}
				break;
			case 'e':
				if (argv[i][2] == '\0') {
					Error = true;
				} else {
					//schitayu chto zadaetsya v vide e-chislo
					int number = 0;
					for (j = 7; argv[i][j] != '\0'; ++j) {
						number = number * 10 + argv[i][j] - '0';
					}

					eps = 1;
					for (j = 0; j < number; ++j) {
						eps /= 10.0;
					}
				}
				break;
		}		
	}
	
	return 0;
}

int main(int argc, char** argv) {

	time_begin = clock();

	init();

	if (Debug) {
		fprintf(stdout, "\nDebug : main\n");
		fprintf(stdout, "\n znacheniya parametrov po umolchaniyu :\n");
		fprintf(stdout, " -d = OFF\n -e = OFF\n -p = OFF\n -t = OFF\n -prec = 1e-14\n -eps = 1e-10\n -max_iter = 0\n");
	}

	if (argc < 3) {
		if (Error) fprintf(stderr, "\n Ne ukazani imena vxodnogo i vixodnogo faylov\n");
		usage();
		return 0; //nujno li uzmenit vozvrashaemoe znachenie zdes ? 
	}

	FILE *fin, *fout;

	if ((fin = fopen(argv[1], "r")) == NULL) {
		if (Error) fprintf(stderr, "\n Vxodnogo fayla s takim nazvaniem ne sushestvuet, libo proizoshla oshibka otkritiya fayla \n");
		usage();
		return 0;	//0?
	}

	//mojet ne stoit v samom nachale fout otkrivat?
	if ((fout = fopen(argv[2], "w")) == NULL) {
		if (Error) fprintf(stderr, "\n Vixodnogo fayla s takim nazvaniem ne sushestvuet, libo proizoshla oshibka otkritiya fayla \n");
		usage();
		return 0;	//0?
	}

	if (Debug) {
		fprintf(stdout, "\nDebug : main\n");
		fprintf(stdout, "\n nachinaem razbor komandnoy stroki \n");
	}

	int parse_cmd_respond = parse_cmd(argc, argv);
	
	if (parse_cmd_respond < 0) {
		if (Error) fprintf(stderr, "neizvestniy parametr : %s\n", argv[-parse_cmd_respond]);
		usage();
		return 0;//0?
	} else 
	if (parse_cmd_respond == 1) {
		usage();
		return 0;
	} else 
	if (parse_cmd_respond != 0) {
		if (Error) printf("\n proizoshla nepredvidennaya oshibka razbora komandnoy stroki \n");
		usage();
		return 0;
	}

	if (Debug) {
		fprintf(stdout, "\nDebug : main\n");
		fprintf(stdout, "\nRazbor komandnoy stroki uspeshno zavershen\n");
		fprintf(stdout, "znacheniya parametrov po umolchaniyu :\n");
		fprintf(stdout, " -d=OFF\n -e = OFF\n -p = OFF\n -t = OFF\n -prec = 1e-14\n -eps = 1e-10\n -max_iter = 0\n");
		fprintf(stdout, "-d=%s\n", (Debug ? "YES" : "NO"));
		fprintf(stdout, "-e=%s\n", (Error ? "YES" : "NO"));
		fprintf(stdout, "-p=%s\n", (Print ? "YES" : "NO"));
		fprintf(stdout, "-t=%s\n", (Time ? "YES" : "NO"));
		fprintf(stdout, "-prec=%lf\n", prec);
		fprintf(stdout, "-eps=%lf\n", eps);
		fprintf(stdout, "-max_iter=%d\n", max_iter);
	}

	int n;
	//vse li normalno prochel?
	if (fscanf(fin, "%d", &n) != 1) {
		if (Error) fprintf(stderr, "\n oshibka chteniya razmera matrici\n");
		return 0;//0?
	} 

	if (n < 2) {
		if (Error) fprintf(stderr, "\n razmer matrici doljen bit > 1 \n");
		return 0; //0?
	}

	if (n > MAX_MATR_SIZE) {	//100?
		if (Error) fprintf(stderr, "\n razmer matrici doljen bit <= %d \n", MAX_MATR_SIZE);
		return 0;//0?
	}

	int mem_occupied = n * n;
	//vse li normalno videlil?
	double *A = (double*)malloc(mem_occupied * sizeof(double));
	if (A == NULL) {
		if (Error) fprintf(stderr, "\n ne smog videlit %d double-ov\n", mem_occupied);
		return 0;//0?
	}

	int i, j;
	for (i = 0; i < n; ++i)
		for (j = 0; j < n; ++j) {
			if (fscanf(fin, "%lf", &A[i * n + j]) != 1) { //vse li normalno prochel?
				if (Error) fprintf(stderr, "\n ne smog prochest element v pozicii [%d][%d]\n", i + 1, j + 1);
				return 0;//0?
			}
		}

	fclose(fin); //problems?

	if (Print) {
		fprintf(stdout, "\n vxodnaya matrica imeet sleduyushiy vid \n");
		int row, col;
		for (row = 0; row < n; ++row) {
			for (col = 0; col < n; ++col) {
				fprintf(stdout, "%1.9lf ", A[row * n + col]);
			}
			fprintf(stdout, "\n");
		}
		fprintf(stdout, "\n");
	}

	////!-matlab-!
	//fprintf(fout, "x=[");
	//for (i = 0; i < n; ++i) {
	//	for (j = 0; j < n; ++j) {
	//		fprintf(fout, "%1.10lf ", A[i * n + j]);
	//	}
	//	fprintf(fout, ";");
	//}
	//fprintf(fout, "];\n");

	mem_occupied = sim_memsize_13_13(n);	
	double* overhead = (double*)malloc(mem_occupied);

	if (overhead == NULL) {
		if (Error) fprintf(stderr, "\n ne smog videlit %d double-ov\n", mem_occupied);
		return 0;//0?
	}

	if (Debug) {
		fprintf(stdout, "\nDebug : main\n");
		fprintf(stdout, "\n zapuskaem proceduru privedeniya k pochti treugolnomu vidu : sim_13_13\n");
	}

	if (sim_13_13(n, A, overhead, prec) == -1) {
		if (Error) fprintf(stderr, "\n metod ne primenim : matrica ne simmetrichna libo opredelitel == 0 \n");

		fprintf(fout, "0");

		free(A);
		free(overhead);

		return 0; // 0?
	}

	//mesto dlya Eigen - massiv gde budut otveti
	double* Eigen = (double*)malloc(n * sizeof(double));
	if (Eigen == NULL) {
		if (Error) fprintf(stderr, "\n ne smog videlit %d double-ov \n", n);
		return 0;//0?
	}

	//elementi pervoy poddiagonali : B
	double* B = (double*)malloc((n - 1) * sizeof(double));

	if (B == NULL) {
		if (Error) fprintf(stderr, "\n ne smog videlit %d double-ov \n", n - 1);
		return 0;//0?
	}

	for (i = 0; i + 1 < n; ++i) {
		B[i] = A[(i + 1) * n + i];
	}	
	//dalshe idut nexitrie manipulyacii , s celyu osvobodit n * n elementov v A iz kotorix dalee potrebuetsa tolko n shtuk
	free(overhead);
	overhead = (double*)malloc(n * sizeof(double));

	if (overhead == NULL) {
		if (Error) fprintf(stderr, "\n ne smog videlit %d double-ov \n", n);
		return 0;//0?
	}

	for (i = 0; i < n; ++i) {
		overhead[i] = A[i * n + i];
	}
	free(A);
	A = (double*)malloc(n * sizeof(double));

	if (A == NULL) {
		if (Error) fprintf(stderr, "\n ne smog videlit %d double-ov \n", n);
		return 0;//0?
	}

	for (i = 0; i < n; ++i) {
		A[i] = overhead[i];
	}
	free(overhead);

	mem_occupied = evc_memsize_13_13(n);
	overhead = (double*)malloc(evc_memsize_13_13(n));	

	if (overhead == NULL) {
		if (Error) fprintf(stderr, "\n ne smog videlit %d double-ov \n", mem_occupied);
		return 0;//0?
	}

	if (Debug) {
		fprintf(stdout, "\nDebug : main\n");
		fprintf(stdout, "\nzapuskaem osnovnoy algoritm naxojdeniya sobstvennix znacheniy\n");
	}
		
	//vizov osnovnoy funkcii
	int respond = evc_13_13(n, max_iter, eps, A, B, Eigen, overhead, prec);
	
	free(A);
	free(B);
	free(overhead);

	if (respond == -1) {
		//never falls here tak kak v sim_13_13 uje proveryal i simmetrichnost i opredelitel
		if (Error) fprintf(stderr, "\n metod ne primenim \n");

		free(Eigen);

		return 0; //0?
	} else

	if (respond == 1) {
		if(Error) fprintf(stderr, "metod ne sxoditsya za ukazannoe chislo iteraciy \n");

		free(Eigen);

		return 0; // 0?
	} else
	if (respond == 0) {
		fprintf(fout, "%d \n", n);
		for (i = 0; i < n; ++i) {
			fprintf(fout, "%1.9lf \n", Eigen[i]);
		}

		free(Eigen);
		
		time_end = clock();

		if (Time) {
			fprintf(stdout, "\n Vremya vipolneniya : %d \n", time_end - time_begin);
		}
	}

	return 0;
}