//
//#include <cstdlib>
//#include <cmath>
//#include <cstdio>
//#include <algorithm>
//#include <iostream>
//#include <hash_set>
//#include <set>
//
//#include <time.h>
//#include <stdio.h>
//#include <stdlib.h>
//
//using namespace std;
////using namespace stdext;
//
//int sizei[11];
//pair<int, int> para[11][500000]; 
//
//int mid, lt, key, rt;
//
//int binsearch(pair<int, int> v[], int lt, int rt, int key) {
////	if (key < vect1[0]) return false;
//
//	while (lt + 1 < rt) {
//		int mid = (lt + rt) >> 1;
//		if (key < v[mid].first)
//			rt = mid;
//		else
//			lt = mid;
//	}
//	
//	return v[lt].first == key ? lt : -1;
//}
//
//pair<int, int> dop[500000];
//
//void mymerge(pair<int, int> v[], int lt, int rt) {
//	for (int i = 0; i < lt; ++i) 
//		dop[i] = v[i];
//
//	int i = 0, j = lt, k = 0;
//	
//	while (i < lt || j < rt) {
//		if (i == lt){
//			v[k++] = v[j++];
//		}else
//		if (j == rt){
//			v[k++] = dop[i++];
//		}else
//		if (dop[i].first < v[j]){
//			v[k++] = dop[i++];	
//		}else{
//			v[k++] = v[j++];
//		}
//	}
//}
//
//int n, k, siz;
//int flag[51];
//int inp[50];
//
//long long gcd(long long a, long long b) {
//	return b == 0 ? a : gcd(b, a % b);
//}
//
//long long lcm(long long a, long long b) {
//	return a / gcd(a,b) * b;
//}
//
//int random(int base){
//	return rand() % base;
//}
//
//void sort(pair<int, int> v[], int lt, int rt) {
//	int i = lt, j = rt;
//	int mid = v[lt + random(rt - lt + 1)].first;
//	while (i <= j) {
//		for(;v[i].first<mid;++i);
//		for(;mid<v[j].first;--j);
//		if (i<=j){
//			pair<int, int> tmp = v[i];
//			v[i] = v[j];
//			v[j] = tmp;
//			++i;
//			--j;
//		}
//	}
//	if (lt < j) sort(v, lt, j);
//	if (i < rt) sort(v, i, rt);
//}
//
//int unique(pair<int, int> v[], int lt, int rt) {
//	int wide = lt + 1;
//
//	for (int i = lt + 1; i < rt; ++i) {//<important!
//		if (v[i].first == v[i - 1].first) {
//			v[wide - 1].second += v[i].second;
//		} else {
//			v[wide++] = v[i];
//		}
//	}
//
//	return wide;
//}
//
//void solve() {
//	//optimize : 1) merge dlya kajdogo otdelno; 
//
//	for (int i = 0; i < 11; ++i) sizei[i] = 0;
//
//	int len = 0, sz, bs;
//	long long inf = n, tmp, tmpint, num;
//
//	//for (num = 2; num < 51; ++num){
//	for (int i = 0; i < siz; ++i) { 
//		num = inp[i];
//		//cout << num << endl << endl;
//
//		for (int str = len; str > 0; --str) {
//
//			switch(str) {
//				case 10:
//					sz = size10;
//					for (int j = 0; j < size9; ++j) {
//						//tmp = num * vect9[j];
//						tmp = lcm(num, vect9[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size10 == 0){
//								vect10[size10] = key;
//								amt10[size10++] = amt9[j];
//							} else {
//								rt = sz;
//								bs = binsearch10();
//								if (bs < 0) {
//									vect10[size10] = key;
//									amt10[size10++] = amt9[j];
//								} else {
//									amt10[bs] += amt9[j];
//								}
//							}
//						}
//					}
//					if (sz + 1 < size10) {
//						sort(vect10, sz, size10 - 1, amt10);
//						unique(vect10, sz, size10, amt10);
//					}
//					if (0 < sz && sz < size10) mymerge(vect10, sz, size10, amt10);
//				break;
//				case 9:
//					sz = size9;
//					for (int j = 0; j < size8; ++j) {
//						//tmp = num * vect8[j];
//						//if (i == 30 && j == 3341) {
//					//		i = 30;
//					//	}
//						tmp = lcm(num, vect8[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size9 == 0){
//								vect9[size9] = key;
//								amt9[size9++] = amt8[j];
//							} else {
//								rt = sz;
//								bs = binsearch9();
//								if (bs < 0) {
//									vect9[size9] = key;
//									if (i == 30 && j == 3342) {
//										i = 30;
//									}
//									amt9[size9++] = amt8[j]; //j==3342 writes to size0
//								} else {
//									amt9[bs] += amt8[j];
//								}
//							}
//						}
//					}
//					if (sz + 1 < size9) {
//						sort(vect9, sz, size9 - 1, amt9);
//						unique(vect9, sz, size9, amt9);
//					}
//					if (0 < sz && sz < size9) mymerge(vect9, sz, size9, amt9);
//				break;
//				case 8:
//					sz = size8;
//					for (int j = 0; j < size7; ++j) {
//						//tmp = num * vect7[j];
//						tmp = lcm(num, vect7[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size8 == 0){
//								vect8[size8] = key;
//								amt8[size8++] = amt7[j];
//							} else {
//								rt = sz;
//								bs = binsearch8();
//								if (bs < 0) {
//									vect8[size8] = key;
//									amt8[size8++] = amt7[j];
//								} else {
//									amt8[bs] += amt7[j];
//								}
//							}
//						}
//					}		
//					if (sz + 1 < size8) {
//						sort(vect8, sz, size8 - 1, amt8);
//						unique(vect8, sz, size8, amt8);
//					}
//					if (0 < sz && sz < size8) mymerge(vect8, sz, size8, amt8);
//				break;
//				case 7:
//					sz = size7;
//					for (int j = 0; j < size6; ++j) {
//						//tmp = num * vect6[j];
//						tmp = lcm(num, vect6[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size7 == 0){
//								vect7[size7] = key;
//								amt7[size7++] = amt6[j];
//							} else {
//								rt = sz;
//								bs = binsearch7();
//								if (bs < 0) {
//									vect7[size7] = key;
//									amt7[size7++] = amt6[j];
//								} else {
//									amt7[bs] += amt6[j];
//								}
//							}
//						}
//					}		
//					if (sz + 1 < size7) {
//						sort(vect7, sz, size7 - 1, amt7);
//						unique(vect7, sz, size7, amt7);
//					}
//					if (0 < sz && sz < size7) mymerge(vect7, sz, size7, amt7);
//				break;
//				case 6:
//					sz = size6;
//					for (int j = 0; j < size5; ++j) {
//						//tmp = num * vect5[j];
//						tmp = lcm(num, vect5[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size6 == 0){
//								vect6[size6] = key;
//								amt6[size6++] = amt5[j];
//							} else {
//								rt = sz;
//								bs = binsearch6();
//								if (bs < 0) {
//									vect6[size6] = key;
//									amt6[size6++] = amt5[j];
//								} else {
//									amt6[bs] += amt5[j];
//								}
//							}
//						}
//					}		
//					if (sz + 1 < size6) {
//						sort(vect6, sz, size6 - 1, amt6);
//						unique(vect6, sz, size6, amt6);
//					}
//					if (0 < sz && sz < size6) mymerge(vect6, sz, size6, amt6);
//				break;
//				case 5:
//					sz = size5;
//					for (int j = 0; j < size4; ++j) {
//						//tmp = num * vect4[j];
//						tmp = lcm(num, vect4[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size5 == 0){
//								vect5[size5] = key;
//								amt5[size5++] = amt4[j];
//							} else {
//								rt = sz;
//								bs = binsearch5();
//								if (bs < 0) {
//									vect5[size5] = key;
//									amt5[size5++] = amt4[j];
//								} else {
//									amt5[bs] += amt4[j];
//								}
//							}
//						}
//					}		
//					if (sz + 1 < size5) {
//						sort(vect5, sz, size5 - 1, amt5);
//						unique(vect5, sz, size5, amt5);
//					}
//					if (0 < sz && sz < size5) mymerge(vect5, sz, size5, amt5);
//				break;
//				case 4:
//					sz = size4;
//					for (int j = 0; j < size3; ++j) {
//						//tmp = num * vect3[j];
//						tmp = lcm(num, vect3[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size4 == 0){
//								vect4[size4] = key;
//								amt4[size4++] = amt3[j];
//							} else {
//								rt = sz;
//								bs = binsearch4();
//								if (bs < 0) {
//									vect4[size4] = key;
//									amt4[size4++] = amt3[j];
//								} else {
//									amt4[bs] += amt3[j];
//								}
//							}
//						}
//					}		
//					if (sz + 1 < size4) {
//						sort(vect4, sz, size4 - 1, amt4);
//						unique(vect4, sz, size4, amt4);
//					}
//					if (0 < sz && sz < size4) mymerge(vect4, sz, size4, amt4);
//				break;
//				case 3:
//					sz = size3;
//					for (int j = 0; j < size2; ++j) {
//						//tmp = num * vect2[j];
//						tmp = lcm(num, vect2[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size3 == 0){
//								vect3[size3] = key;
//								amt3[size3++] = amt2[j];
//							} else {
//								rt = sz;
//								bs = binsearch3();
//								if (bs < 0) {
//									vect3[size3] = key;
//									amt3[size3++] = amt2[j];
//								} else {
//									amt3[bs] += amt2[j];
//								}
//							}
//						}
//					}		
//					if (sz + 1 < size3) {
//						sort(vect3, sz, size3 - 1, amt3);
//						unique(vect3, sz, size3, amt3);
//					}
//					if (0 < sz && sz < size3) mymerge(vect3, sz, size3, amt3);
//				break;
//				case 2:
//					sz = size2;
//					for (int j = 0; j < size1; ++j) {
//						//tmp = num * vect1[j];
//						tmp = lcm(num, vect1[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size2 == 0){
//								vect2[size2] = key;
//								amt2[size2++] = amt1[j];
//							} else {
//								rt = sz;
//								bs = binsearch2();
//								if (bs < 0) {
//									vect2[size2] = key;
//									amt2[size2++] = amt1[j];
//								} else {
//									amt2[bs] += amt1[j];
//								}
//							}
//						}
//					}		
//					if (sz + 1 < size2) {
//						sort(vect2, sz, size2 - 1, amt2);
//						unique(vect2, sz, size2, amt2);
//					}
//					if (0 < sz && sz < size2) mymerge(vect2, sz, size2, amt2);
//				break;
//				case 1:
//					sz = size1;
//					for (int j = 0; j < size0; ++j) {
//						//tmp = num * vect0[j];
//						tmp = lcm(num, vect0[j]);
//						if (tmp <= inf) {
//							key = (int)tmp;
//							if (size1 == 0){
//								vect1[size1] = key;
//								amt1[size1++] = amt0[j];
//							} else {
//								rt = sz;
//								bs = binsearch1();
//								if (bs < 0) {
//									vect1[size1] = key;
//									amt1[size1++] = amt0[j];
//								} else {
//									amt1[bs] += amt0[j];
//								}
//							}
//						}
//					}
//					if (sz + 1 < size1) {
//						sort(vect1, sz, size1 - 1, amt1);
//						unique(vect1, sz, size1, amt1);
//					}
//					if (0 < sz && sz < size1) mymerge(vect1, sz, size1, amt1);
//				break;
//			}
//		}
//
//		vect0[size0] = (int)num;
//		amt0[size0++] = 1;
//
//		if (len < 10) ++len;
//	}
//}
//
//int calc() {
//	long long ans = 0, two = 1, sum, inf = n, one = 1;
//
//	for (int j = 0; j < 11; ++j) {
//		sum = 0;
//		for (int i = 0; i < sizei[j]; ++i) sum += (inf / para[j][i].first) * para[j][i].second;	
//		ans += one * two * sum;
//		two *= 2;
//		one = -one;
//	}
//
//	return (int)ans;//is it int??
//}
//
//int main() {
//	//itogovaya formula nepravilna, da eshe pri maks teste vikidivaet che to
//	// v obshem nujno polovinki sz .. sizei sortirovat, ostavlyat unikalnie(prichem amt odinakovix skladivat), i tolko potom merge!!!
//	srand(time(NULL));
//
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);
//
//	scanf("%d %d", &n, &k);
//	for (int i = 1; i < 51; ++i) flag[i] = 0;
//
//	for (int i = 0, t; i < k; ++i) {
//		scanf("%d", &t);
//		flag[t] ^= 1;
//	}
//
//	siz = 0;
//	for (int i = 2; i < 51; ++i)
//		if (flag[i] == 1) {
//			inp[siz++] = i;
//		}
//
//	solve();
//
//	int answer = calc();
//	if (flag[1] == 1) answer = n - answer;
//	//answer = 8;
//
//	printf("%d", answer);
//
//	return 0;
//
//}
