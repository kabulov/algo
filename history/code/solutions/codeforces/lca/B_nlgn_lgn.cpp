
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <algorithm>
#include <vector>

using namespace std;

const int maxn = 500001;

vector<int> g[maxn];
char word[4];

int n = 0, m = 0;
pair<int, int> q[maxn];

int h;
int up[maxn][30];

int tin[maxn], tout[maxn];
int timer;

void dfs(int v, int p) {
	tin[v] = ++timer;
	
	up[v][0] = p;
	for (int i = 1; i <= h; ++i)
		up[v][i] = up[up[v][i - 1]][i - 1];

	for (int i = 0, mysz = g[v].size(); i < mysz; ++i) 
		dfs(g[v][i], v);	

	tout[v] = ++timer;
}

bool upper(int a, int b) {
	return tin[a] <= tin[b] && tout[a] >= tout[b];
}

int lca(int a, int b) {
	if (upper(a, b)) return a;
	if (upper(b, a)) return b;
	
	for (int i = h; i >= 0; --i)
		if (!upper(up[a][i], b))
			a = up[a][i];

	return up[a][0];
}

int main() {
	freopen("lca.in", "r", stdin);
	freopen("lca.out", "w", stdout);

	int k;
	scanf("%d", &k);

	for (int i = 0, a, b; i < k; ++i) {
		scanf("%s", word);
		scanf("%d %d", &a, &b);
		--a;
		--b;
		if (word[0] == 'A') {
			g[a].push_back(b);
			++n;
		} else {
			q[m] = make_pair(a, b);
			++m;
		}
	}

	h = 1;
	while ((1 << h) <= n) ++h;

	timer = 0;
	dfs(0, 0);

	for (int i = 0; i < m; ++i) 
		printf("%d\n", lca(q[i].first, q[i].second) + 1);

	return 0;
}

