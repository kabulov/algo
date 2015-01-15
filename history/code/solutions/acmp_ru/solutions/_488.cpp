
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>

using namespace std;

const int maxn = 105;

long double vect[maxn][3];
long double radius;
int n;

long double x1, y1, x2, y2;

void makeline(int i) {
	vect[i][0] = y2 - y1;
	vect[i][1] = x1 - x2;
	vect[i][2] = x1 * y2 - x2 * y1;
}

long double x, y;

void intersect(int i, int j) {
	long double det = vect[i][0] * vect[j][1] - vect[i][1] * vect[j][0];
	x = (vect[i][2] * vect[j][1] - vect[i][1] * vect[j][2]) / det;
	y = (vect[i][0] * vect[j][2] - vect[i][2] * vect[j][0]) / det;
}

long double eps = 1e-10;

bool inside() {
	return x * x + y * y < radius * radius + eps;
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	
	cin >> radius >> n;	
	cin >> x1 >> y1 >> x2 >> y2;
	makeline(0);
	
	int answer = 2;
	for (int i = 1; i < n; ++i) {
		++answer;
		cin >> x1 >> y1 >> x2 >> y2;
		makeline(i);
		for (int j = 0; j < i; ++j) {
			intersect(i, j);
			if (inside()) ++answer;
		}
	}

	cout << answer;
	return 0;
}