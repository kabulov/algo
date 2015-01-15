
#include <cstdlib>
#include <math.h>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

int n, m;
int cap[10 + 5];
int size[100 + 10];
pair<int, pair<int, int> > que[200 + 20];

int loc[100 + 10]; //location of i th cargo
int con[10 + 5]; // content size of i th container

int search(int& from, int& to, int w) {
	int num = -1;

	for (int i = 0; i < m; ++i) {
		if (loc[i] < 0) continue;
		if (cap[loc[i]] - (con[loc[i]] - size[i]) < w) continue;
		for (int j = 0; j < n; ++j) {
			if (j == loc[i]) continue;
			if (cap[j] - con[j] >= size[i]) {
				if (num == -1) {
					num = i;
					to = j;
				} else {
					if (size[num] < size[i]) continue;
					if (size[num] > size[i]) {
						num = i;
						to = j;
						continue;
					}

					if (cap[loc[num]] - (con[loc[num]] - size[num]) < cap[loc[i]] - (con[loc[i]] - size[i])) continue;
					if (cap[loc[num]] - (con[loc[num]] - size[num]) > cap[loc[i]] - (con[loc[i]] - size[i])) {
						num = i;
						to = j;
						continue;
					}

					if (cap[to] - con[to] - size[num] < cap[j] - con[j] - size[i]) continue;
					if (cap[to] - con[to] - size[num] > cap[j] - con[j] - size[i]) {
						num = i;
						to = j;
						continue;
					}

					//if (num > i && to > j) {
					//	num = i;
					//	to = j;
					//}


					//if (num < i) continue;
					//if (num > i) {
					//	num = i;
					//	to = j;
					//	continue;
					//}

					//if (to < j) continue;
					//if (to > j) {
					//	num = i;
					//	to = j;
					//	continue;
					//}
					
					//never falls here	?
				}
			}
		}			
	}

	if (num != -1) from = loc[num];
	return num;
}

bool cmp(const pair<int, pair<int, int> >& a, const pair<int, pair<int, int> >& b) {
	if (a.first == b.first) {
		return a.second.first < b.second.first; //when >= WA 11
	}
	return a.first < b.first;
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	
	cin >> n >> m;
	for (int i = 0; i < n; ++i) cin >> cap[i];

	int len = 0;
	for (int i = 0; i < m; ++i) {
		cin >> size[i];

		cin >> que[len].first;
		que[len].second.first = i;
		que[len].second.second = 0;	//0 means put
		++len;
		
		cin >> que[len].first;
		que[len].second.first = i;
		que[len].second.second = 1;	//1 means get
		++len;

//		if (que[len - 1].first == que[len - 2].first) while (true);
	}

	sort(que, que + len, cmp);

	//for (int i = 1; i < len; ++i) 
	//	if (que[i].first == que[i - 1].first) {
	//		while (true);
	//	}

	for (int i = 0; i < n; ++i) con[i] = 0;
	for (int i = 0; i < m; ++i) loc[i] = -1; //-1 means cant locate cargo, -2 means cargo was but it is removed from system

	for (int iter = 0, cargo; iter < len; ++iter) {
		cargo = que[iter].second.first;
		if (que[iter].second.second == 1) {
			if (loc[cargo] < 0) { //== -1
				continue; // no this cargo in any container
			} else {
				printf("take cargo %d from cell %d\n", cargo + 1, loc[cargo] + 1);
				con[loc[cargo]] -= size[cargo];
				loc[cargo] = -2;
			} 
		} else {
			int pos = -1;

			for (int i = 0; i < n; ++i) 
				if (con[i] + size[cargo] <= cap[i] && (pos == -1 || cap[i] - con[i] < cap[pos] - con[pos])) 
					pos = i;
					
			if (pos > -1) {//!= -1
				loc[cargo] = pos;
				con[pos] += size[cargo];
				printf("put cargo %d to cell %d\n", cargo + 1, pos + 1);
			} else {
				// no free space
				int from, to, num;
				num = search(from, to, size[cargo]); //if num == -1 cannot find free space
				if (num != -1) {
					con[from] -= size[num];
					con[from] += size[cargo];
					con[to] += size[num];
					loc[num] = to;
					loc[cargo] = from;
					printf("move cargo %d from cell %d to cell %d\n", num + 1, from + 1, to + 1);
					printf("put cargo %d to cell %d\n", cargo + 1, from + 1);
				} else {
					printf("cargo %d cannot be stored\n", cargo + 1);	
					//
				}
			}
		}
	}		
	
	return 0;
}