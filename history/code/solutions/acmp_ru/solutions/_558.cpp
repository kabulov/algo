#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <stack>

using namespace std;

void offer(long double r3, long double r1, long double r2, long double &x, long double &y) {
	long double r = r3;
	x = r * (r1 - r2) / (r1 + r2);
	y = sqrt((r - x) * (r + x + 2 * r1));		
}

long double sqr(long double a) {
	return a * a;
}

long double dist(long double x1, long double y1, long double x2, long double y2) {
	return sqr(x1 - x2) + sqr(y1 - y2);
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int n;
	cin >> n;

	if (n < 4) {
		cout << 0;
		return 0;
	}

	int v[300];
	for (int i = 0; i < n; ++i) cin >> v[i];
	sort(v, v + n);
	
	long double r1, r2, r3, r, x1, y1, x2, y2, eps = 1e-10;

	int answer = 0;

	for (int i = 1; i < n - 2; ++i) {
		for (int j = i + 1; j < n - 1; ++j) {
			if (v[i] >= v[j]) continue;
			for (int k = j + 1; k < n; ++k) {
				if (v[j] >= v[k]) continue;

				r1 = v[j];
				r2 = v[k];
				r3 = v[i];

				offer(r3, r1, r2, x1, y1);
				
				int lt = 0, rt = i, mt;
				while (rt - lt > 1) {
					mt = (lt + rt) / 2;
					r = v[mt];
					offer(r, r1, r2, x2, y2);
					if (dist(x1, y1, x2, y2) + eps < sqr(r3+r))//bad
						rt = mt;
					else
						lt = mt;
				}

				if (lt == 0) {
					r = v[lt];
					offer(r, r1, r2, x2, y2);
					if (dist(x1, y1, x2, y2) + eps > sqr(r3+r)) ++answer;//if good
				} else {
					answer += lt + 1;
				}
				
			}	
		}
	}	

	cout << answer;

	return 0;
}

//bad solution downwards!

//r1 = v[j];
//r2 = v[k];
//r3 = v[i];

//t = r1 - r2;
//t /= (r1 + r2) * 1.0;

//x3 = t * r3;
//y3 = sqrt((r1 + r3) * (r1 + r3) - (t * r3 + r1) * (t * r3 + r1));
//
////long double d1 = sqrt((x3+r1) * (x3+r1) + (y3) * (y3)), d2 = sqrt((x3-r2) * (x3-r2) + (y3) * (y3));

//c = 2 * y3;
//a = x3*x3 + y3*y3 - r3*r3;
//b = 2 * (r1 - r3 - t * (r1 + x3));

////p = (1 - t*t)*4.0*y3*y3-b*b;
////m = (a*b+t*r1-r1)*4.0*y3*y3;
////r = (m+sqrt(m*m+(a*n)*(a*n)))/p;

//p = c * c * (1 - t*t) - 1;
//m = a*b+c*c*r1*(t-1);
//r = (m+sqrt(m*m+a*a*p)) / p;

//R = (int)(r+eps);
//
////if (R > v[i]) while (1);

//int z;
//for (z = 0; v[z] <= R && z < i; ++z);//binsearch
//	
//answer += z;				
