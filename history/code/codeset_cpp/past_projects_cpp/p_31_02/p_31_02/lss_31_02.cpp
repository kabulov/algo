
#include <stdlib.h>
#include "lss_31_02.h"

int calcKoef(int n, double* x, double* f, double* koef) {
	//n >= 2

	int i,j;
	
	//primenyaetsa sxema iz stranici 18 bogacheva
	//perviy stolbec
	for(i=0;i<n;++i)
		koef[i] = f[i];

	//ostalnie stolbci
	//sleva napravo, snizu vverx
	for (i=1; i<n; ++i){
		for (j=n-1;j>=i;--j){
			koef[j] = (koef[j]-koef[j-1])/(x[j]-x[j-i]);
		}
	}

	return 0;
}


int calcFunc(double* koef, double a, double b, int k, double* x, double* val, int n) { 
	double cur=a, //tekushaya tochka
		   step=(b-a)/k; //shag, dlina intervala mejdu tochkami dvumya sosednimi
	double eps=1e-14;

	//menshe eps ne doljno bit nichego
	if (step<eps)
		return -1;

	int i,j;
	//i-y interval, tochka pos v massive otvete
	//cur - tekushaya tochka
	//step dlini k kusochkov
	//primenyaetsa sxema gornera iz stranici 18 bogacheva, formula (3) dlya kajdoy tochki otdelno
	for(i=0; i <= k; ++i){
		//sxema gornera dlya tochki i{on je cur}
		val[i] = koef[n-1];	
		for(j=n-2;j>=0;--j)
			val[i]=val[i]*(cur-x[j])+koef[j];
		cur += step;//sleduyushaya tochka
	}

	return 0;
}