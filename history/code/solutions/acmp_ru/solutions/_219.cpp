
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

int n, m, s, inf = (int)1e8, lt, rt;  //erroneous inf
int r[100], c[100], a[100][100], cap[100][100];
int ptr[100], d[100], que[100], f[100][100];

bool bfs(int v) {
	for (int i = 0; i < 100; ++i) d[i] = -1;
	d[0] = v;
	lt = 0;
	rt = 1;
	que[0] = v;
	while (lt < rt) {
		v = que[lt++];
		for (int i = 0; i < n + m + 2; ++i) {
			if (d[i] != -1) continue;
			if (f[v][i] == cap[v][i]) continue;
			d[i] = d[v] + 1;
			que[rt++] = i;
		}
	}
	return d[n + m + 1] != -1;
}

int dfs(int v, int flow) {
	if (flow == 0) return 0;
	if (v == n + m + 1) return flow;
	
	for (int& to = ptr[v]; to < n + m + 2; ++to) {
		if (d[to] != d[v] + 1) continue;
		int push = dfs(to, min(flow, cap[v][to] - f[v][to]));
		if (push > 0) {
			f[v][to] += push;
			f[to][v] -= push;
			return push;
		}
	}

	return 0;
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> m;
	for (int i = 0; i < n; ++i) cin >> r[i];
	for (int i = 0; i < m; ++i) cin >> c[i];

	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
			cin >> a[i][j];
		}
	}

	for (int i = 0; i < n; ++i) {
		s = 0;
		for (int j = 0; j < m; ++j) 
			if (a[i][j] != -1) 
				s += a[i][j];
		if (s > r[i]) {
			cout << -1;
			return 0;
		}
	}	

	for (int j = 0; j < m; ++j) {
		s = 0;
		for (int i = 0; i < n; ++i) 
			if (a[i][j] != -1)
				s += a[i][j];
		if (s > c[j]) {
			cout << -1;
			return 0;
		}
	}

	s = 0;
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j)
			if (a[i][j] != -1)
				s += a[i][j];

	for (int i = 0; i < 100; ++i)
		for (int j = 0; j < 100; ++j)
			cap[i][j] = 0;

	for (int i = 0; i < n; ++i) cap[0][i + 1] = r[i];
	for (int j = 0; j < m; ++j) cap[n + j + 1][n + m + 1] = c[j];
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
			if (a[i][j] != -1) {
				cap[i + 1][n + j + 1] = 0; //nimportant
				cap[0][i + 1] -= a[i][j];
				cap[n + j + 1][n + m + 1] -= a[i][j];
			} else {
				cap[i + 1][n + j + 1] = min(cap[0][i + 1], cap[n + j + 1][n + m + 1]);//min(r[i], c[j]); //erroenous	
			}
		}
	}

	for (int i = 0; i < 100; ++i)
		for (int j = 0; j < 100; ++j)
			f[i][j] = 0;

	while (true) {
		if (!bfs(0)) break;
		for (int i = 0; i < 100; ++i) ptr[i] = 0;
		for (int flow = dfs(0, inf); flow > 0; flow = dfs(0, inf)) s += flow;
	}

	cout << s;
	return 0;
}