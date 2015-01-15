
#include <cstdlib>
#include <math.h>
#include <cstdio>
#include <algorithm>
#include <iostream>

using namespace std;

const int maxn = 50000;
const long double pi = 3.14159265358979323846;

pair<long double, int> vect[4 * maxn + 10];
int n, len;
long double x, y, eps = 1e-10;

bool equal(long double a, long double b) {
	return abs(a - b) < eps;
}

long double vmul(long double x1, long double y1, long double x2, long double y2) {
	return x1 * y2 - x2 * y1;
}

void add(long double tx, long double ty, long double tr) {
	long double x1, y1, x2, y2, kat, dst, mid, vx, vy, mx, my, ang1, ang2;
	
	dst = tx * tx + ty * ty;
	kat = dst - tr * tr;
	dst = sqrt(dst);
	mid = kat / dst;
	vx = tx / dst;
	vy = ty / dst;
	mx = vx * mid;
	my = vy * mid;
	swap(vx, vy);
	vx = -vx;
	kat = sqrt(kat);
	mid = tr * kat / dst;
	vx *= mid;
	vy *= mid;
	
	x1 = mx + vx;
	y1 = my + vy;
	x2 = mx - vx;
	y2 = my - vy;

	if (vmul(mx, my, x1, y1) > 0) {
		swap(x1, x2);
		swap(y1, y2);
	}

	ang1 = atan2(y1, x1);
	ang2 = atan2(y2, x2);

	if (ang1 > 0 && ang2 < 0) { //maybe eps : erroneous
		vect[len++] = make_pair(ang1, 0);
		vect[len++] = make_pair(pi + eps, 1);	//eps?
		vect[len++] = make_pair(-pi - eps, 0);	//eps?
		vect[len++] = make_pair(ang2, 1);
	} else {
		vect[len++] = make_pair(ang1, 0);
		vect[len++] = make_pair(ang2, 1);
	}	
}

bool cmp(const pair<long double, int>& a, const pair<long double, int>& b) {
	if (equal(a.first, b.first)) return a.second < b.second; //0 - open; 1 - close;
	return a.first +eps < b.first; //eps important ?
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	
	len = 0;

	cin >> n;
	cin >> x >> y;

	long double tx, ty, tr;

	for (int i = 0; i < n; ++i) {
		scanf("%lf %lf %lf", &tx, &ty, &tr);
		tx -= x;
		ty -= y;
		add(tx, ty, tr);
	}

	sort(vect, vect + len, cmp);	
	
	long double left = -pi + eps, right = pi - eps;
	
	if (vect[0].first > left) {//check if equal first : erroneous
		cout << "NO";
		return 0;
	}

	if (vect[len - 1].first < right) {//check if equal first : erroneous
		cout << "NO";
		return 0;
	}

	int amt = 0;
	for (int i = 0; i < len; ++i) {
		if (vect[i].second == 0)
			++amt;
		else
			--amt;

		//maybe check if two adjacent segments are almost overlapping(difference between nearest edges is < eps) : erroneous
		if (i + 1 < len && amt == 0) {
			cout << "NO";
			return 0;
		}
	}

	cout << "YES";
	return 0;
}