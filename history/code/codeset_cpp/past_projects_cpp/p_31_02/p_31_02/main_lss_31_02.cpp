
#include "lss_31_02.h"
#include "main_lss_31_02.h"

#include <stdio.h>
#include <stdlib.h>

//1 oshibka argv t.e. parametrov
//2 oshibka otkritiya vx ili vix fayla
//3 oshibka vo xvodnom fayle
//4 oshibka videleniya pamyati
//5 oshibka v funkciyax iz podklyuchaemix moduley
int main(int argc, char* argv1, char* argv2) {
	//vxodnie vixodnie fayli
	char *defIn, *defOut;

	if (argc==0) {//ili argumentov 2 ili net
		//po umolchaniyu
		defIn="lss_31_02_in.txt";
		defOut="lss_31_02_out.txt";
	} else
	if (argc == 2) {
		defIn=argv1;
		defOut=argv2;
	} else {
		fprintf(stderr, "bad parameter\n");
		return 1;
	}

	FILE *In, *Out;

	if ((In=fopen(defIn,"r"))==NULL||(Out=fopen(defOut,"w"))==NULL) {
		fprintf(stderr, "bad input or output file : can not open");
		return 2;
	}

	int p,n,k;//iz usloviya
	double a,b;//iz usloviya
	double *x, *f, *koef, *val;
	//koef - koefficienti priblijayushey funkcii, val - znacheniya priblijayushey v k+1 tochkax

	//ne budu proveryat vxod, vse doljno bit norm
	if(fscanf(In, "%d", &p)!=1||p!=0){
		fprintf(stderr, "no p or p != 0\n");
		return 3;
	}
	if(fscanf(In, "%d", &n)!=1||n<2){
		fprintf(stderr, "no n or n < 2\n");
		return 3;
	}

	x=(double*)malloc(sizeof(double)*n);
	f=(double*)malloc(sizeof(double)*n);

	if (x==NULL||f==NULL) {
		fprintf(stderr, "memory allocation error\n");	
		return 4;
	}

	int i;
	for (i=0;i<n;++i){
		fscanf(In, "%lf %lf", &x[i], &f[i]);
	}

	if(fscanf(In, "%lf", &a)!=1||fscanf(In, "%lf", &b)!=1||a>b) {
		fprintf(stderr, "no a or no b or a > b\n");
		return 3;
	}

	if(fscanf(In, "%d", &k)!=1||k<2){
		fprintf(stderr, "no k or k < 2\n");
		return 3;
	}

	koef=(double*)malloc(sizeof(double)*n);
	val=(double*)malloc(sizeof(double)*(k+1));

	if (koef==NULL||val==NULL) {
		fprintf(stderr, "memory allocation error\n");	
		return 4;
	}

	//vichislyaem koefficienti priblijayushey funkcii(PF) zdes, otvet v koef
	if(calcKoef(n, x, f, koef)!=0){
		fprintf(stderr, "error in calcKoef function\n");		
		return 5;
	}

	//vichislyaem znacheniya PF v k+1 tochke, otvet v val
	if(calcFunc(koef, a, b, k, x, val, n)!=0){
		fprintf(stderr, "error in calcFunc function\n");
		return 5;
	}		

	for(i=0;i<k+1;i++)
		fprintf(Out,"%.10lf\n",val[i]);

	free(x);
	free(f);
	free(koef);
	free(val);

	fclose(In);
	fclose(Out);

	return 0;
}
