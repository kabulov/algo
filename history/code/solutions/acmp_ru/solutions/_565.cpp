
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

struct trio {
	int f, s, p;
};

typedef struct trio base;
const int maxn = 100010;
int n, r, len;
base vect[maxn];

bool cmp(const base& a, const base& b) {
	return a.s < b.s; 
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> r;
	len = 0;

	for (int i = 0, a, b; i < n; ++i) {
		scanf("%d %d", &a, &b);
		if (a > b) {
			vect[len].f = a;
			vect[len].s = b;
			vect[len].p = i + 1;
			++len;
		}
	}
	
	n = len;
	if (n == 0) return 0;
	if (r == 1) {
		cout << "Impossible";
		return 0;
	}

	for (int i = 0; i < n; ++i) {
		vect[i].f = (r - 2 + vect[i].f - vect[i].s) / (r - 1);
		if (vect[i].f > vect[i].s) {
			cout << "Impossible";
			return 0;
		}
	}

	sort(vect, vect + n, cmp);

	int time = 0;
	len = 0;
	int ans[maxn];

	for (int i = 0; i < n; ++i) {
		if (time + vect[i].f > vect[i].s) {
			cout << "Impossible";
			return 0;
		}
		time += vect[i].f;
		vect[i].f = time - vect[i].f;
		ans[len++] = i;
	}

	for (int i = 0; i < len; ++i)  {
		printf("%d %d\n", vect[ans[i]].f, vect[ans[i]].p);
	}

	return 0;
}