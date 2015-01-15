
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

int n, k, answer;
int iv[100], jv[100];
int ilen, jlen;

bool contradict(int i1, int j1, int i2, int j2) {
	if (i1 == i2 || j1 == j2) return true;	
	if (abs(i1 - i2) == abs(j1 - j2)) return true;
	if (abs(i1 - i2) + abs(j1 - j2) == 3) return true;

	return false;
}

bool bad() {
	for (int i = 0; i + 1 < jlen; ++i) 
		if (contradict(iv[i], jv[i], iv[jlen - 1], jv[jlen - 1])) 
			return true;		

	return false;
}

void permgen(int level) {
	if (level == ilen) {
		++answer;	//!!here !! incrementation
		return;
	}

	if (level == 0) {
		jlen = 1;
		for (int i = 0; i < n; ++i) {
			jv[0] = i;
			permgen(level + 1);
		}
		jlen = 0;
	} else {
		++jlen;
		for (int i = 0; i < n; ++i) {
			jv[jlen - 1] = i;
			if (!bad()) permgen(level + 1);
		}
		--jlen;
	}
}

void cnkgen(int pos, int left) {
	if (left == 0) {
		jlen = 0; //!!
		permgen(0);
		return;
	}

	++ilen;
	for (int i = pos; i <= n - left; ++i) { //<= NOT < !!! erroneous
		iv[ilen - 1] = i;
		cnkgen(i + 1, left - 1);
	}
	--ilen;
}

int main() {
		
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> k;	
	answer = 0;
	
	ilen = jlen = 0; //jlen inside recursion
	cnkgen(0, k);

	cout << answer;
	return 0;

}