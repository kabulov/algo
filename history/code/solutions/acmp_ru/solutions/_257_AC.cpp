
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <vector>
#include <stack>

using namespace std;

const long double eps = 1e-10;

bool zero(long double num) {
	return abs(num) < eps;
}

void solve(long long a, long long b, long long c, int& amt, long long v[]) {
	amt = 0;
	
	long double d = (long double)b * b - 4 * (long double)a * c;
	if (zero(d)) {
		if (b % (2 * a) == 0) v[amt++] = -b / (2 * a);
	} else
	if (d < 0) {
		//no solution -> amt == 0
	} else {
		d = sqrt(d);
		long double x1 = (-b + d) / (2 * a);
		long double x2 = (-b - d) / (2 * a);
		x1 = floor(x1 + 0.5); //round mode
		x2 = floor(x2 + 0.5); //round mode
		if (zero(a * x1 * x1 + b * x1 + c)) v[amt++] = (long long)x1;
		if (zero(a * x2 * x2 + b * x2 + c)) v[amt++] = (long long)x2;
	}
}

int len;
long long divis[100], size[100];

long long a, b, c, d;
int amt;
long long v[10];

long double f(long double x) {
	return a * x * x * x + b * x * x + c * x + d;
}

void offer(int p, long double mul) {
	if (p == len) {
		if (zero(f(mul))) v[amt++] = mul;
		if (zero(f(-mul))) v[amt++] = -mul;
		return;
	}

	offer(p + 1, mul);
	for (int i = 0; i < size[p]; ++i) {
		mul *= divis[p];
		offer(p + 1, mul);
	}
}	

void hard(long long a, long long b, long long c, long long d) {
	amt = 0;
	
	if (d == 1) {
		if (a + b + c + d == 0) v[amt++] = 1;
		if (-a + b - c + d == 0) v[amt++] = -1;
		return;
	}
	
	len = 0;
	long long tmp = d, prime = 2;
	if (tmp < 0) tmp = -tmp;
	while (tmp > 1) {
		if (tmp % prime == 0) {
			size[len] = 0;
			divis[len] = prime;
			while (tmp % prime == 0) {
				tmp /= prime;
				++size[len];
			}
			++len;
		} else {
			++prime;
			if (prime * prime > tmp) break;
		}
	}
	if (tmp > 1) {
		size[len] = 1;
		divis[len] = tmp;
		++len;
	}
	
	offer(0, 1.0);
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> a >> b >> c >> d;

	if (a == 0 && b == 0 && c == 0) {
		cout << (d == 0 ? "-1" : "0");
		return 0;
	}

	if (a == 0 && b == 0) {
		if (d % c == 0) {
			cout << "1" << " " << -d / c;
		} else {
			cout << "0";
		}
		return 0;
	}

	if (a == 0) {
		int amt = 0;
		long long v[3];
		solve(b, c, d, amt, v);
		if (amt > 0) sort(v, v + amt);
		cout << amt;
		for (int i = 0; i < amt; ++i) cout << " " << v[i];
	
		return 0;
	}

	if (d == 0) {
		solve(a, b, c, amt, v);
		v[amt++] = 0;
	} else {
		hard(a, b, c, d);
	}

	if (amt > 0) sort(v, v + amt);
	
	if (amt > 0) 
	{	//delete equal numbers
		int s = 1;
		for (int i = 1; i < amt; ++i) 
			if (v[i] != v[i - 1])
				v[s++] = v[i];
		amt = s;
	}

	cout << amt;
	for (int i = 0; i < amt; ++i) cout << " " << v[i];

	return 0;
}