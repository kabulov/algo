
#include "task_13_13.h"

#include <math.h>
#include <stdio.h>

//proverka matrici na simmetrichnost
int is_sim_13_13(int n, double* A, double precision){
	int i, j;
	double ij, ji;

	extern bool Debug;

	for (i = 0; i + 1 < n; ++i) {
		for (j = i + 1; j < n; ++j) {
			ij = A[i * n + j];
			ji = A[j * n + i];

			//elementi [i][j] [j][i] doljni sovpadat
			//proveryaetsya otnositelnaya pogreshnost!!! 
			if (abs(ij / ji - 1) >= precision) {
				if (Debug) {
					fprintf(stdout, "\nDebug : is_sim_13_13\n proverka simmetrichnosti:\n");
					fprintf(stdout, "  elementi v poziciyax [%d][%d] i [%d][%d] ne sovpadayut !(format : [stroka][stolbec], 1 indeksaciya)\n", i + 1, j + 1, j + 1, i + 1);
					fprintf(stdout, "   - matrica ne simmetrichna. Sledovatelno metod ne primenim\n");
				}

				return -1; //mojet luchshe abs(ij - ji) >= precision -> absolyutnaya pogreshnost?, !-nujno podumat-!
			}
		}
	}

	if (Debug) {
		fprintf(stdout, "\nDebug : is_sim_13_13\n proverka simmetrichnosti matrici zavershena\n");
	}

	return 0;	//vse xorosho, matrica simmetrichnaya
}

//proverka virojdennosti matrici
//0 - virojdena
//1 - ne virojdena
int is_det_zero_13_13(int n, double* A, double precision){
	int i, j, k;
	double tmp;

	extern bool Debug;

	for (i = 0; i < n; ++i) {
		k = i;
		
		//poisk maksimalnogo po modulyu elementa v tekushem stolbce
		for (j = i + 1; j < n; ++j) {
			if (abs(A[j * n + i]) > abs(A[k * n + i]))	//abs!!!!
				k = j;
		}
		
		//maksimalniy element v stolbce == 0 -> opredelitel == 0
		if (abs(A[k * n + i]) < precision) {
			if (Debug) {
				fprintf(stdout, "\nDebug : is_det_zero_13_13\n na %d-m shage privedeniya matrici k treugolnomu vidu(pri vichislenii opredelitelya) v stolbce ne nayden nenulevoy element\n");
			}

			return 0;	//opredelitel == 0
		}

		//menyaem mestami stroki, pri etom izmenenie znaka opredelitelya ignoriruem t.k. nas interesuet tolko ego absolyutnoe znachenie
		if (k != i) {
			
			if (Debug) {
				fprintf(stdout, "\n menyaem mestami stroki %d i %d\n", k + 1, i + 1);
			}
			
			for (j = i; j < n; ++j) { //swap
				tmp = A[i * n + j];
				A[i * n + j] = A[k * n + j];
				A[k * n + j] = tmp;
			}	
		}

		//ot kajdoy nijestoyashey stroki otnimaem tekushuyu umnojennuyu na sootvetstvuyushuyu konstantu(obnulyaem elementi tekushego stolbca)
		tmp = A[i * n + i];

		for (j = i; j < n; ++j) {
			A[i * n + j] /= tmp;
		}
		
		for (k = i + 1; k < n; ++k) {
			tmp = A[k * n + i];
		
			for (j = i; j < n; ++j) {
				A[k * n + j] -= A[i * n + j] * tmp;				
			}
		}

		if (Debug) {
			fprintf(stdout, "\n matrica posle %d-go shaga : \n", i + 1);

			int row, col;
			for (row = 0; row < n; ++row) {
				for (col = 0; col < n; ++col) {
					fprintf(stdout, "%1.9lf ", A[row * n + col]);
				}
				fprintf(stdout, "\n");
			}
			fprintf(stdout, "\n");
		}

	}

	return 1;
}

//videlyaem pamyat
int sim_memsize_13_13(int n){
	//return n * n > n + n ? n * n : n + n; // + 1 or 2 ??
	extern bool Debug;
	
	int result = n * n * sizeof(double);

	if (Debug) {
		fprintf(stdout, "\nDebug : sim_memsize_13_13\n");
		fprintf(stdout, "videleno elementov : %d\n", result);
	}

	return result; //nujen dlya proveki opredelitelya na ravenstvo nulyu, no ne dlya algoritma privedeniya k pochti treugolnomu vidu
}

int sim_13_13(int n, double* A, double* tmp, double precision){
	if (is_sim_13_13(n, A, precision) != 0) {
		//printf("bad input : matrix is not symmetric!"); //metod ne primenim : matrica ne simmetrichna
		return -1; //0 ?
	}		

	int i, j, k;

	//kopiruem v vremennuyu peremennuyu t.k. pri vichislenii opredelitelya dannie menyayutsya
	for (i = 0; i < n; ++i) 
		for (j = 0; j < n; ++j)
			tmp[i * n + j] = A[i * n + j];

	if (is_det_zero_13_13(n, tmp, precision) != 1) {
		//printf("bad input : |A| = 0"); // metod ne primenim : opredelitel == 0
		return -1;
	}	

	extern bool Debug;

	if (n < 2) {
		if (Debug) {
			fprintf(stdout, "\nDebug : sim_13_13\n razmer matici doljen bit > 1\n");
		}
		
		return -1;	//razmernost matrici ne mojet bit otricatelnoy, ravnoy nulyu, v matrice razmernosti 1 prosto nechego menyat
	}
	
	//privedenie k pochti treugolnomu vidu : osnovnoy algoritm, metod otrajeniy
	double sk, norm, buf;

	for (k = 0; k + 2 < n; ++k) {
		sk = 0;
		
		for (i = k + 2; i < n; ++i) {
			sk += A[i * n + k] * A[i * n + k];	//vichislyaem summu kvadratov n - k - 2 elementov
		}

		norm = sqrt(A[(k + 1) * n + k] * A[(k + 1) * n + k] + sk); //dobavlyaem ostavshiysa chlen i berem koren, teper summa n - k - 1 elementov!

		if (Debug) {
			fprintf(stdout, "\nDebug : sim_13_13\n");
			fprintf(stdout, "vichislyaem otrajayushiy vektor na %d-m shage\n\n", k + 1);
			fprintf(stdout, "norma vektora : (%d, %d, ..., %d) ravna %1.9lf\n", (k + 1) + 1, (k + 2) + 1, n, norm);
			fprintf(stdout, "vektor raspolojen po indeksam : ([%d][%d], [%d][%d], ..., [%d][%d])\n", (k + 1) + 1, k + 1, (k + 2) + 1, k + 1, n, k + 1);
		}
		
		buf = norm;			//ocherednoy poddiagonalniy element
		
		//naprimer v edinichnoy matrice danniy sluchay vstrechaetsa
		if (abs(norm) < precision) {
			//fprintf(stdout, "\n norma vektora ravna 0, propuskaem shag\n");		
			continue; //simmetrichnost i samosopryajennost - trexdiagonalnost ne narushena na etom shage(nulevoy vektor)
		}
	
		A[(k + 1) * n + k] -= norm;		//sleduem formule
		
		if (Debug) {
			fprintf(stdout, "otnimem ot pervogo elementa vektora ego normu, posle chego poluchim\n");
			int ind;
			for (ind = k + 1; ind < n; ++ind) {
				fprintf(stdout, "%1.9lf\n", A[ind * n + k]);
			}
			fprintf(stdout, "\n");
		}
		
		norm = sqrt(A[(k + 1) * n + k] * A[(k + 1) * n + k] + sk); //vichislyaem normu otrajayushego x
		
		if (Debug) {
			fprintf(stdout, "\n norma izmenennogo vektora : %1.9lf\n", norm);
		}

		if (abs(norm) < precision) {//!!!!!!!!!!!!!!!!
			//fprintf(stdout, "\n norma vektora ravna 0, propuskaem shag\n");		
			continue; //simmetrichnost i samosopryajennost - trexdiagonalnost ne narushena na etom shage(nulevoy vektor)
		}
		
		for (i = k + 1; i < n; ++i) {
			A[i * n + k] /= norm;	//vichislyaem sam vektor x(otrajayushiy)
		}

		if (Debug) {
			fprintf(stdout, "\n otrajayushiy vektor : \n");
			int ind;
			for (ind = k + 1; ind < n; ++ind) {
				fprintf(stdout, "%1.9lf\n", A[ind * n + k]);
			}
			fprintf(stdout, "\n");
		}
		
		//umnojaem podmatricu na U(x) sleva i U(x)* sprava, no t.k. matrica samosopryajennaya primenyaem formulu iz bogacheva
		//kotoraya sokrashaet vichisleniya(stranica 76)
		for (i = k + 1; i < n; ++i) {
			A[k * n + i] = 0;
			for (j = k + 1; j < n; ++j) {
				A[k * n + i] += A[i * n + j] * A[j * n + k];	//vichislyaem y=Ax
			}
			
			if (Debug) {
				fprintf(stdout, "\n vektor y=Ax : \n");
				int ind;
				for (ind = k + 1; ind < n; ++ind) {
					fprintf(stdout, "%1.9lf\n", A[k * n + ind]);
				}
				fprintf(stdout, "\n");
			}
		}

		sk = 0;
		for (i = k + 1; i < n; ++i) {
			sk += A[i * n + k] * A[k * n + i];	//vichislyaem (y,x) - skalyarnoe proizvedenie. po formule iz bogacheva
		}

		if (Debug) {
			fprintf(stdout, "\n znachenie (y, x) : %1.9lf \n", sk);
		}

		for (i = k + 1; i < n; ++i) {
			A[k * n + i] -= sk * A[i * n + k];		//vektor z = 2(y - (y,x)x) iz formuli v bogacheve
			A[k * n + i] *= 2;
		}
		
		if (Debug) {
			fprintf(stdout, "\n vektor z = 2(y - (y,x)x) :\n");
			int ind;
			for (ind = k + 1; ind < n; ++ind) {
				fprintf(stdout, "%1.9lf\n", A[k * n + ind]);
			}
			fprintf(stdout, "\n");

		}

		for (i = k + 1; i < n; ++i) { 
			for (j = i; j < n; ++j) {
				A[i * n + j] = A[j * n + i] = A[i * n + j] - A[k * n + i] * A[j * n + k] - A[i * n + k] * A[k * n + j]; //umnojaem na Ui
			}
		}

		if (Debug) {
			fprintf(stdout, "\n matrica posle %d-go shaga : \n", k + 1);

			int row, col;
			for (row = 0; row < n; ++row) {
				for (col = 0; col < n; ++col) {
					fprintf(stdout, "%1.9lf ", A[row * n + col]);
				}
				fprintf(stdout, "\n");
			}
			fprintf(stdout, "\n");
		}
		
		//poluchivshuyusa normu vektora tekushego stolbca soxranyaem v sootvetstvuyushem poddiagonalnom elemente
		A[(k + 1) * n + k] = buf; //A[k * n + (k + 1)] = buf; //mojno tolko odnu storonu, a snaruji 3 vektora!

		if (Debug) {
			fprintf(stdout, "\n soxranim v pozicii [%d][%d] vichislenniy poddiagonalniy element ocherednoy : %1.9lf\n", k + 2, k + 1, buf);
		}

		//for (i = k + 2; i < n; ++i) {
			//A[i * n + k] = A[k * n + i] = 0; //zachem obnulyat?
		//}
	}

	return 0;
}
