
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <hash_set>
#include <iostream>
#include <stack>
#include <string.h>

using namespace std;

typedef pair<long long , pair<long long , long long> > base;

bool cmp(const base& a, const base& b) {
	//if (a.first == b.first) {
	//	if (a.second.first == b.second.first) return a.second.second < b.second.second;
	//	return a.second.first < b.second.first;
	//}
	return a.first < b.first;
}

long long sqr(long long a) {
	return a * a;
}

const int maxn = 105;

int n, len;
int map[maxn][maxn];
char used[maxn];
int node[maxn];

void topsort(int v) {
	used[v] = true;
	for (int i = 0; i < n; ++i)
		if (!used[i] && map[v][i] == 1) 
			topsort(i);
		
	node[len++] = v;
}

//void dfs(int v) {
//	used[v] = true;
//	for (int i = 0 ; i < n; ++i) {
//		if (!used[i] && map[v][i] == 1) {
//			map[v][i] = 0;
//			dfs(i);
//			break;
//		}
//	}
//	//int ctr = 0, chmax = 0;
//	//
//	//for (int i = 0; i < n; ++i)
//	//	if (map[v][i] == 1) {
//	//		if (!used[i]) {
//	//			++ctr;
//	//			chmax = max(chmax, dfs(i));
//	//		}
//	//	}
//
//	//ctr = max(chmax, ctr);
//	//return max(1, ctr);
//}

int part[maxn];
bool dfs(int v) {
	if (used[v]) return false;
	used[v] = true;
	for (int i = 0; i < n; ++i) {
		if (map[v][i] == 0) continue;
		if (part[i] == -1 || dfs(part[i])) {			
			part[i] = v;
			return true;
		}
	}
	return false;
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	long long v;

	cin >> n;
	cin >> v;

	base vect[maxn];

	for (int i = 0; i < n; ++i) {
		char tmp[10];
		scanf("%s", tmp);
		vect[i].first = ((tmp[0] - '0') * 10 + (tmp[1] - '0')) * 60 + (tmp[3] - '0') * 10 + (tmp[4] - '0');
		cin >> vect[i].second.first >> vect[i].second.second;
	}

	sort(vect, vect + n, cmp);
	
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < n; ++j)
			map[i][j] = 0;

	for (int i = 1; i < n; ++i) {
		for (int j = 0; j < i; ++j) {
			long long time = vect[i].first - vect[j].first;
			long long dist = sqr(vect[i].second.first - vect[j].second.first) + sqr(vect[i].second.second - vect[j].second.second);
			if (3600 * dist <= v * v * time * time) 
				map[j][i] = 1;		
		}
	}	
	
	//int len = 0;
	//for (int i = 0; i < n; ++i) used[i] = false;
	//for (int i = 0; i < n; ++i) {
	//	if (!used[i])
	//		topsort(i);
	//}
	//reverse(node, node + n);

//	int answer = 0;
//	for (int i = 0; i < n; ++i) used[i] = false;
//
//	for (int i = 0; i < n; ++i)
//		if (!used[i]) {
////			answer += dfs(i);
//			dfs(i);
//			++answer;
//		}
	
	int answer = 0;

	for (int i = 0; i < n; ++i) part[i] = -1;
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) used[j] = false;
		if (dfs(i)) ++answer;		
	}

	cout << n - answer;
	return 0;
}