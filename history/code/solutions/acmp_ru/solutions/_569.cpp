#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <stack>

using namespace std;

const int maxn = 100000 + 10;

int n, d;
int di[maxn], pi[maxn], a[maxn], b[maxn];

int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> d;
	for (int i = 0; i < n; ++i) scanf("%d", &di[i]);
	for (int i = 0; i < n; ++i) pi[i] = i;
	for (int i = 0; i < n; ++i) a[i] = b[i] = 0;

	int j = 0;
	for (int i = 0; i < n; ++i) {
		if (di[i] <= d) {
			swap(di[i], di[j]);
			swap(pi[i], pi[j]);
			++j;
		}
	}

	for (int i = 0; i < n; ++i) {
		if (di[i] < d) {
			a[pi[i]] = pi[j] + 1;
			b[pi[i]] = d - di[i];
			di[j] -= d - di[i];
			if (di[j] <= d) ++j;
		}
	}

	for (int i = 0; i < n; ++i) {
		printf("%d %d\n", a[i], b[i]);
	}

	return 0;

}