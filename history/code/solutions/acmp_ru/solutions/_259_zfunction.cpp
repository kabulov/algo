
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>

using namespace std;

const int maxn = 500000;
int n;
char v[maxn];
int z[maxn];

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n;
	scanf("%s", v);
	for (int i = 0; i < n; ++i) v[n + i + 1] = v[n - i - 1];	

	for (int i = 0; i <= n + n; ++i) z[i] = 0;
	for (int i = 1, lt = 0, rt = 0; i <= n + n; ++i) {
		if (rt >= i) z[i] = min(rt - i + 1, z[i - lt]);
		while (i + z[i] <= n + n && v[z[i]] == v[i + z[i]]) ++z[i];		
		if (i + z[i] - 1 > rt) lt = i, rt = i + z[i] - 1;
	}

	for (int i = n + n; i > n; --i)
		printf("%d ", z[i]);

	return 0;
}