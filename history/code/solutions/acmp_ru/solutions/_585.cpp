
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

int oldsolution(int n) {//works 2 times faster than newsolution
	long double ln_2 = log(2.0), ln_10 = log(10.0), ln_n, ln_np1, eps = 1e-10;
	
	if (n <= 0) {
		return -1;
	}

	if (n == 1) {
		return 4;
	}
		
	ln_n = log(n * 1.0);
	ln_np1 = log(n + 1.0);

	long long k = 0;
	long double mid = 0, lt = ln_n, rt = ln_np1, t = 0;

	while (true) {
		//if (mid < lt) {//<,  + eps
		//	mid += ln_2;
		//	++k;
		//	continue;
		//}	
		
		if (k * ln_2 < ln_n + t * ln_10) {
			++k;
			continue;
		}

		if (ln_np1 + t * ln_10 <= k * ln_2) {
			++t;
			continue;
		}
		//if (rt <= mid) {//<=	//eps ? , + eps
		//	lt += ln_10;
		//	rt += ln_10;
		//	continue;
		//}

		//n * 10^t <= 2^k < (n + 1) * 10^t
		break;
	}

	return k;
}

long long ten, bnd;

bool bad(long long num, long long n) {
	if (num >= bnd){
		return (num / ten) != n;
	} else {
		while (num > n) num /= 10;
		return num != n;
	}
}

long long newsolution(long long n) {//1e16, 1e16, 1e15
	//1e16(last), 16(len), 1e15(bnd), imenno s takimi parametrami proxodit sistemu testov
	//vozmojno pri popolnenii sistemi testov novimi testami pridetsa ivelichit do naprimer 1e17, 17, 1e16 ili bolshe
	// no togda ne budet proxodit po vremeni!!

	if (n <= 0) {
		return -1;
	}

	if (n == 1) {
		return 4;
	}
		
	long long num = 2, k = 1, last = (long long)1e16, tmp = n, len = 0; //if 1e17 or higher -> time limit !
	
	while (tmp > 0) {
		++len;
		tmp /= 10;
	}

	len = 16 - len; //if 1e17 or higher -> time limit ! 
	ten = 1;
	for (int i = 1; i <= len; ++i) ten *= 10;
	bnd = (long long)1e15; //if 1e16 or higher -> time limit !

	while (num < n) {
		num += num;
		++k;
	}
	
	bool flag;
	while (true) {//bad(num, n)
		if (num >= bnd) {
			flag = num / ten == n;
		} else {		
			tmp = num;
			while (tmp > n) tmp /= 10;
			flag = tmp == n;
		}
		if (flag) break;
		++k;
		num += num;
		if (num >= last) num /= 10;
	}

	return k;
}

int badsolution(long double n) {
	long double ln_10 = log(10.0) / log(2.0), ln_n = log(n) / log(2.0), eps = 1e-15;

	while (abs((long long)ln_n - ln_n) > eps) {
		ln_n += ln_10;
	}

	return (long long)ln_n;
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int n;
	cin >> n;
	
	//cout << newsolution(n);
	cout << oldsolution(n); //2 times faster than newsolution
	return 0;
}