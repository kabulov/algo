
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

long long d, prlen, baselen, answer, amt;//, vectlen;
long long prime[100], base[100], vect[100];

long long size[100];
long long table[100][100];

bool isprime(long long n) {
	for (long long div = 2; div * div <= n; ++div) 
		if (n % div == 0) return false;
	
	return true;
}

long long inf = (long long)(1e15 + 2.5); //1e15 + 1

//void check() {
//	sort(vect, vect + vectlen);
//	reverse(vect, vect + vectlen);
//	//base , vect
//	long long tmp = 1;
//
//	for (int i = 0; i < vectlen; ++i) {
//		for (int j = 1; j < vect[i]; ++j) {
//			tmp *= base[i];
//			if (tmp >= inf) {
//				tmp = inf;
//				break;
//			}
//		}
//		if (tmp >= inf) {
//			tmp = inf;
//			break;
//		}
//	}
//
//	answer = min(tmp, answer);
//}
//
//void gen(int pos, int amt, long long sum) {
//	if (pos == prlen) {
//		vect[vectlen] = sum;
//		++vectlen;
//		check();
//		--vectlen;
//		return;
//	}
//
//	if (amt == 0) {
//		vect[vectlen] = prime[pos];
//		++vectlen;
//		gen(pos + 1, 0, sum);
//		--vectlen;
//	} else {//amt > 0
//		gen(pos + 1, amt - 1, sum * prime[pos]);
//		int len = 0;
//		for (int i = pos + 1; i <= prlen - amt; ++i) {
//			vect[vectlen] = prime[i - 1];
//			++len;
//			++vectlen;
//			gen(i + 1, amt - 1, sum * prime[i]);
//		}
//		vectlen -= len;
//	}
//}

void check() {
	for (int i = 0; i < amt; ++i) {
		vect[i] = 1;
		for (int j = 0; j < size[i]; ++j) {
			vect[i] *= table[i][j];
		}
	}	
	sort(vect, vect + amt);
	reverse(vect, vect + amt);

	long long tmp = 1;

	for (int i = 0; i < amt; ++i) {
		for (int j = 1; j < vect[i]; ++j) {
			tmp *= base[i];
			if (tmp >= inf) {
				tmp = inf;
				break;
			}
		}
		if (tmp >= inf) {
			tmp = inf;
			break;
		}
	}

	answer = min(tmp, answer);
}

//generaciya vsex razbieniy
void gen(long long k, long long n) {
	if (k == 1) {
		int len = 0;
		for (int i = 0; i < n; ++i) {
			table[0][size[0]] = prime[i];
			++size[0];
			++len;
		}
		check();
		size[0] -= len;
		return;
	}

	if (k == n) {
		for (int i = 0; i < n; ++i) {
			table[i][size[i]] = prime[i];
			++size[i];
		}	
		check();
		for (int i = 0; i < n; ++i) {
			--size[i];
		}
		return;
	}

	table[k - 1][size[k - 1]] = prime[n - 1];
	++size[k - 1];
	gen(k - 1, n - 1);
	--size[k - 1];

	for (int i = 0; i < k; ++i) {
		table[i][size[i]] = prime[n - 1];
		++size[i];
		gen(k, n - 1);
		--size[i];
	}
}

long long solve(long long d) {
	if (d == 1) {
		return 1;
	}

	prlen = 0;
	long long tmp = d, div = 2;
	while (tmp > 1) {
		if (tmp % div == 0) {
			prime[prlen++] = div;
			tmp /= div;
		} else {
			++div;
			if (div * div > tmp) break;
		}
	}
	if (tmp > 1) {
		prime[prlen++] = tmp;
	}

	reverse(prime, prime + prlen);

	baselen = 0;
	tmp = 1;

	while (baselen < prlen) {
		++tmp;
		while (!isprime(tmp)) ++tmp;
		base[baselen++] = tmp;
	}	

	answer = inf;
	
	//1 multiplier
	tmp = 1;
	for (int j = 1; j < d; ++j) {
		tmp *= 2;
		if (tmp >= inf) {
			tmp = inf;
			break;
		}
	}
	answer = min(answer, tmp);

	//prlen multipliers
	tmp = 1;
	for (int i = 0; i < prlen; ++i) {
		for (int j = 1; j < prime[i]; ++j) {
			tmp *= base[i];
			if (tmp >= inf) {
				tmp = inf;
				break;
			}
		}
		if (tmp >= inf) {
			tmp = inf;
			break;
		}
	}
	answer = min(answer, tmp);
	
	for (amt = 2; amt < prlen; ++amt) {
		//amt, prlen;
		for (int i = 0; i < amt; ++i) size[i] = 0;
		gen(amt, prlen);
		//vectlen = 0;
		//gen(0, int(prlen - (i - 1)), 1); //prlen long long -> int
	}

	//for (int i = 0; i < baselen; ++i) {
		//int basepos = i, primepos = prlen - 1;
		//long long num = 1;
		//while (basepos > 0) {
		//	for (int j = 1; j < prime[primepos]; ++j) {
		//		num *= base[basepos];
		//		if (num >= inf) {//inequality sign erroneous
		//			num = inf;				
		//			break;
		//		}
		//	}
		//	if (num >= inf) break;
		//	--primepos;
		//	--basepos;
		//}
		//if (num < inf) {
		//	long long deg = 1;		
		//	while (primepos >= 0) {
		//		deg *= prime[primepos];
		//		--primepos;
		//	}
		//	for (long long j = 1; j < deg; ++j) {
		//		num *= base[0];
		//		if (num >= inf) {
		//			num = inf;
		//			break;
		//		}
		//	}
		//}
		//answer = min(answer, num);
	//}	

	return (answer >= inf ? 0 : answer);
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	//char was[5001];
	//for (int i = 0; i < 5001; ++i) was[i] = false;

	//for (long long iter = 2; iter <= 10000; ++iter) {
	//	if (iter == 7560) {
	//		iter = 7560;
	//	}

	//	long long tmp = iter;
	//	d = 2;
	//	long long ans = 1;
	//		
	//	while (tmp > 1) {
	//		if (tmp % d == 0) {
	//			long long mid = 1;
	//			while (tmp % d == 0) {
	//				++mid;
	//				tmp /= d;
	//			}
	//			ans *= mid;
	//		} else {
	//			++d;
	//			if (d * d > tmp) break;
	//		}
	//	}
	//	if (tmp > 1) {
	//		ans *= 2;
	//	}

	//	if (!was[ans]) {
	//		was[ans] = true;
	//		if (solve(ans) != iter) {
	//			cout << iter << " " << ans << endl;
	//		}
	//	}
	//}

	cin >> d;
	cout << solve(d);

	//for (long long iter = 2; iter <= 5000; ++iter) {
	//	cout << solve(iter) << endl;
	//}

	return 0;

}