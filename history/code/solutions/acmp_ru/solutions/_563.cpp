
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <stack>

using namespace std;

int n, ans;
int	vect[100000 + 10];

void solve(int lt, int rt, int dep) {
	if (lt >= rt) return;

	if (dep == 32) {
		if ((((long(rt - lt + 1) * (rt - lt)) >> 1) & 1) == 1) {
			ans ^= -1;
		}	

		return;
	}

	int i = lt, j = rt;
	
	while (i < j) {
		if (((vect[i] >> dep) & 1) == 0) {
			++i;
		} else {
			swap(vect[i], vect[j]);
			--j;
		}
	}
	
	if (((vect[i] >> dep) & 1) == 0) ++i;

	if (((i - lt) & 1) == 1 && ((rt - lt + 1 - (i - lt)) & 1) == 1) {
		ans ^= (1 << dep) - 1;			
	}

	solve(lt, i - 1, dep + 1);
	solve(i, rt, dep + 1);
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n;
	
	ans = 0;

	for (int i = 0; i < n; ++i) {
		scanf("%d", &vect[i]);
		ans ^= vect[i];
	}


	solve(0, n - 1, 0);

	cout << ans;

	return 0;
}