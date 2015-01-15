
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

const int maxn = 150 + 10;

int n, k;

int gr[maxn][maxn];
int sz[maxn];

int mn;
int dp[maxn][maxn];

int f[maxn], s[maxn];

void dfs(int v, int p = -1) {

	if (sz[v] == 1 && p != -1) {
		dp[v][1] = 0;
		return;
	}

	int t;

	for (int i = 0; i < sz[v]; ++i) {
		t = gr[v][i];
		if (t != p) {
			dfs(t, v);
		}
	}

	//dp here
	int to = gr[v][0], buf = 0;
	if (to == p) to = gr[v][1], buf = 1;

	f[1] = 1;	
	for (int i = 2; i <= k; ++i) f[i] = dp[to][i - 1];

	t = 0;
	for (int i = 1 + buf; i < sz[v]; ++i) {
		to = gr[v][i];
		if (to == p) continue;
		t ^= 1;
		if (t == 1) {
			//f -> s
			s[1] = i + 1;
			for (int i = 2; i <= k; ++i) {
				s[i] = -1;
				if (f[i] != -1) {
					s[i] = f[i] + 1;
				}

				for (int j = 1; j < i; ++j) {
					if (f[i - j] != -1 && dp[to][j] != -1) {
						int sum = f[i - j] + dp[to][j];
						if (s[i] == -1)
							s[i] = sum;
						else if (sum < s[i])
							s[i] = sum;
					}
				}				
			}
		} else {
			//s -> f
			f[1] = i + 1;
			for (int i = 2; i <= k; ++i) {
				f[i] = -1;
				if (s[i] != -1) {
					f[i] = s[i] + 1;
				}

				for (int j = 1; j < i; ++j) {
					if (s[i - j] != -1 && dp[to][j] != -1) {
						int sum = s[i - j] + dp[to][j];
						if (f[i] == -1)
							f[i] = sum;
						else if (sum < f[i])
							f[i] = sum;
					}
				}				
			}
		}
	}

	if (t == 1) {
		//s
		for (int i = 1; i <= k; ++i)
			dp[v][i] = s[i];
	} else {
		//f
		for (int i = 1; i <= k; ++i)
			dp[v][i] = f[i];
	}
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	scanf("%d %d", &n, &k);

	if (k == n) {
		printf("0");
		return 0;
	}

	for (int i = 0; i < n; ++i) sz[i] = 0;

	for (int i = 0; i + 1 < n; ++i) {
		int a, b;
		scanf("%d %d", &a, &b);

		--a, --b;
		gr[a][sz[a]++] = b;
		gr[b][sz[b]++] = a;
	}

	mn = n; //n - 1 

	for (int t = 0; t < n; ++t) {
		for (int i = 0; i < n; ++i)
			for (int j = 1; j <= k; ++j)
				dp[i][j] = -1;
		dfs(t);
		if (dp[t][k] < mn) mn = dp[t][k];
	}	

	printf("%d", mn);

	return 0;
}