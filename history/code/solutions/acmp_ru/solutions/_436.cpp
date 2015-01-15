
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

long long n, k, ans, amt, len;
long long dv[100], sz[100];

int convert(long long base) {
	int ret = 0;
	long long tmp = n, rem;
	while (tmp > 0) {
		rem = tmp % base; //>= 0
		
		if (k == 0) {
			if (rem == 0) 
				++ret;
			else {
				while (rem % 10 == 0) {
					++ret;
					rem /= 10;
				}

				return ret;
			}
			tmp /= base;
			continue;
		}
		
		if (rem == 0) break; //return ret

		while (rem > 0) {
			if (rem % 10 == k) {
				++ret;
				rem /= 10;
			} else {
				return ret;
			}
		}
		
		tmp /= base;
	}
	return ret;
}

void gen(int pos, long long mul) {
	if (pos == len) {
		if (mul > 1 && mul > k) {
			pos = convert(mul);
			if (pos > amt) {
				amt = pos;
				ans = mul;
			}
		}
		return;
	}
	
	gen(pos + 1, mul);
	for (int i = 0; i < sz[pos]; ++i) {
		mul *= dv[pos];
		gen(pos + 1, mul);
	}
}

int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	
	cin >> n >> k;

	if (n == 1) {
		if (k == 1) {
			cout << 10 << " " << 1; //any system
		} else {
			cout << 10 << " " << 0; //any system
		}

		return 0;
	}

	if (n < k) {
		cout << 10 << " " << 0; // ant system
		return 0;
	}

	if (n == k) {
		cout << 10 << " " << 1; //one system
		return 0;
	}

	if (n == k + 1) {
		cout << 10 << " " << 0; // any system
		return 0;
	}

	ans = n + 1;
	amt = 0;

	{	//base == (n+1)
		long long tmp = n;
		while (tmp > 0) {
			if (tmp % 10 == k) {
				++amt;
				tmp /= 10;
			} else
				break;
		}
	}

	//n >= 2, n > k + 1

	len = 0;

	{	//faktorizaciya
		long long tmp = n - k, d = 2;
		while (tmp > 1) {
			if (tmp % d == 0) {
				sz[len] = 0;
				dv[len] = d;
				while (tmp % d == 0) {
					++sz[len];
					tmp /= d;
				}
				++len;
			} else {
				++d;
				if (d * d > tmp) break;
			}
		}
		if (tmp > 1) {
			sz[len] = 1;
			dv[len] = tmp;
			++len;
		}
	}
	
	gen(0, 1);

	cout << ans << " " << amt;

	return 0;
}