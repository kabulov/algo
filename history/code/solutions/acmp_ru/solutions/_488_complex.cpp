
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <complex>

using namespace std;

typedef complex<long double> base;
const int maxn = 105;

base vect[maxn][2];
long double radius;
int n;
long double X1, Y1, X2, Y2;

long double cross(const base& a, const base& b) {return imag(conj(a) * b);}

base intersect(base a, base b, base p, base q) {
	long double d1 = cross(p - a, b - a);
	long double d2 = cross(q - a, b - a);
	return (d1 * q - d2 * p) / (d1 - d2);
}

long double eps = 1e-10;

bool inside(base p) {
	return norm(p) < radius * radius + eps;
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	
	cin >> radius >> n;	
	cin >> X1 >> Y1 >> X2 >> Y2;
	vect[0][0] = base(X1, Y1);
	vect[0][1] = base(X2, Y2);
	int answer = 2;

	for (int i = 1; i < n; ++i) {
		++answer;
		cin >> X1 >> Y1 >> X2 >> Y2;
		vect[i][0] = base(X1, Y1);
		vect[i][1] = base(X2, Y2);
		for (int j = 0; j < i; ++j) {
			if (inside(intersect(vect[j][0], vect[j][1], vect[i][0], vect[i][1]))) ++answer;
		}
	}

	cout << answer;
	return 0;
}