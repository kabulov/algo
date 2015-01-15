#include <algorithm>
#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <stdlib.h>
#include <functional>
#include <string.h>
#include <string>
 
#include <vector>
#include <stack>
 
//#include <set>
//#include <map>
//
//#include <hash_set>
//#include <hash_map>
 
using namespace std;
 
const int maxn = 22222;
int n, m; 
vector<int> g[maxn];
int answer[maxn];
bool used[maxn];
int tin[maxn];
int fup[maxn];
int timer;

void dfs(int v, int p) {
	used[v] = true;
	tin[v] = fup[v] = ++timer;
	int children = 0;
	int sum = 0, mul = 0; //!!-1?

	for (int i = 0, sz = g[v].size(); i < sz; ++i) {
		int to = g[v][i];
		if (to == p) continue;
		if (used[to]) {
			fup[v] = min(fup[v], tin[to]);
		} else {
			int abc = timer;
			dfs(to, v);
			fup[v] = min(fup[v], fup[to]);
			int size = timer - abc;// something
			if (fup[to] >= tin[v]) {
				if (sum != 0) mul += sum * size;				
				sum += size;
			}
			++children;
		}
	}

	if (p == -1) {
		if (children > 1) {
			
		}
	} else {
		mul += sum * (n - 1 - sum);
	}

	answer[v] += mul;
}

int main() {
 
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif

	cin >> n >> m;
	for (int t = 0; t < m; ++t) {
		int i, j;
		scanf("%d%d", &i, &j);
		--i, --j;
		if (i == j) while(true); //!!
		g[i].push_back(j);
		g[j].push_back(i);
	}

	for (int i = 0; i < n; ++i) answer[i] = n - 1;

	timer = 0;
	dfs(0, -1);

	for (int i = 0; i < n; ++i) {
		printf("%d\n", answer[i]);
	}

    return 0;
}