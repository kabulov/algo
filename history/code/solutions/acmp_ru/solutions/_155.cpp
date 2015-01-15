
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>

using namespace std;

typedef long double base;

const int maxn = 10;
int n;
base c, inp[maxn];

bool good(base param) {
	base eps = 1e-15;
	return abs(param - c) <= 0.01 - eps;	//!!!!
}

bool flag;

base subset[maxn];
int subslen;
int opvect[maxn];
int posvect[maxn];
bool used[maxn];
base mid[maxn];

void finish() {
	for (int i = 0; i < subslen; ++i) {
		mid[i] = subset[i];
		used[i] = false;
	}
	for (int i = 1; i < subslen; ++i) {
		int p = posvect[i];
		int lt = p - 1, rt = p;
		while (used[lt]) --lt;
		while (used[rt]) ++rt;
		if (opvect[p] == 0) {
			mid[rt] += mid[lt];
		} else {
			mid[rt] = mid[lt] * mid[rt] / (mid[lt] + mid[rt]);
		}
		used[lt] = true;
	}
	int p = -1;
	for (int i = 0; i < subslen; ++i) 
		if (!used[i]) {
			p = i;
			break;
		}
	if (good(mid[p])) flag = true;
}

void genagain(int pos) {
	if (pos == subslen) {
		finish();
		return;
	}
	genagain(pos + 1);
	for (int i = pos + 1; i < subslen && !flag; ++i) {
		swap(posvect[pos], posvect[i]);
		genagain(pos + 1);
		swap(posvect[pos], posvect[i]);
	}
}

void calc() {
	if (subslen == 1) {
		if (good(subset[0])) flag = true;
		return;
	}

	for (int i = 1; i < subslen; ++i) {
		posvect[i] = i;
	}

	genagain(1);
}
	
void connect(int pos) {
	if (pos == subslen) {
		calc();
		return;
	}	
	opvect[pos] = 0;
	connect(pos + 1);
	if (flag) return;
	opvect[pos] = 1;
	connect(pos + 1);
}

void gen(int pos) {
	if (pos == subslen) {
		connect(1);	
		return;
	}
	gen(pos + 1);
	for (int i = pos + 1; i < subslen && !flag; ++i) {
		swap(subset[pos], subset[i]);
		gen(pos + 1);
		swap(subset[pos], subset[i]);
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> c;

	if (good(0)) {
//		while (true);
		cout << "YES";
		return 0;
	}

	if (n == 0) {
//		while (true);		!!
		cout << "NO"; //!!
		return 0;
	}
	for (int i = 0; i < n; ++i) cin >> inp[i];

	flag = false; //!!

	for (int iter = 1; iter < (1 << n) && !flag; ++iter) {
		subslen = 0;
		for (int j = 0; j < n; ++j)
			if (((iter >> j) & 1) == 1) 
				subset[subslen++] = inp[j];
		gen(0);
	}	

	if (flag) 
		cout << "YES";
	else
		cout << "NO";

	return 0;
}