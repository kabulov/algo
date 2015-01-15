
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <vector>
#include <stack>

using namespace std;

const int maxn = 110;

int n, m, k;
int map[maxn][maxn], dp[maxn][maxn];

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	scanf("%d %d %d", &n, &m, &k);

	for (int i = 1; i <= n; ++i)
		for (int j = 1; j <= m; ++j)
			map[i][j] = 0;

	for (int iter = 0, ilu, jlu, ird, jrd; iter < k; ++iter) {
		scanf("%d %d %d %d", &ilu, &jlu, &ird, &jrd);
		for (int i = ilu; i <= ird; ++i)
			for (int j = jlu; j <= jrd; ++j)
				map[i][j] = 1;
	}

	for (int i = 0; i <= n + 1; ++i) {
		dp[i][0] = dp[i][m + 1] = 0;
	}
	for (int j = 1; j <= m; ++j) {
		dp[0][j] = dp[n + 1][j] = 0;
	}

	for (int i = 1; i <= n + 1; ++i)
		for (int j = 1; j <= m + 1; ++j)
			dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];

	int answer = 0, tmp;
	for (int i = 2; i <= n + 1; ++i)
		for (int j = 2; j <= m + 1; ++j)
			for (int iin = 0; iin + 1 < i; ++iin) 
				for (int jin = 0; jin + 1 < j; ++jin) {
					if (iin == 0) {
						if (jin == 0) {
							tmp = dp[i][j];
						} else {
							tmp = dp[i][j] - dp[i][jin - 1];
						}
					} else
					if (jin == 0) {
						tmp = dp[i][j] - dp[iin - 1][j];
					} else {
						tmp = dp[i][j] - dp[i][jin - 1] - dp[iin - 1][j] + dp[iin - 1][jin - 1];
					}
					if (tmp == 0) {
						tmp = (i - iin - 1) * (j - jin - 1);
						if(tmp > answer) answer = tmp;
					}
				}

	printf("%d", answer);
	return 0;
}