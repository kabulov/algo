
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <hash_set>
#include <iostream>

using namespace std;

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	long long n;
	cin >> n;

	int i, j;

	long long dp[100][100];
	for (i = 0; i < 10; ++i) dp[i][0] = 0;
	dp[9][0] = 1;

	long long sum = 0;

	for (j = 1; ; ++j) {
		for (i = 1; i < 10; ++i) {
			dp[i][j] = 0;
			for (int k = i; k < 10; ++k)
				dp[i][j] += dp[k][j - 1];
			if (sum + dp[i][j] >= n) break;
			sum += dp[i][j];
		}
		if (i < 10) break;
	}

	long long answer[100];
	int anslen = j;
	answer[j] = i;
	n -= sum;
	--j;

	while (j > 0) {
		while (n > dp[i][j]) {
			n -= dp[i][j];
			++i;
		}
		answer[j] = i;
		--j;
	}

	for (; anslen > 0; --anslen)
		cout << answer[anslen];

	return 0;
}