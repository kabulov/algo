
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <vector>
#include <stack>

using namespace std;

const int maxn = 1010;

int n, m;
char line[maxn];
int h[maxn], lft[maxn], rgt[maxn];

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n >> m;
	for (int i = 0; i < m; ++i) h[i] = 0;

	int answer = 0;
	stack<int> st;

	for (int i = 0; i < n; ++i) {
		scanf("%s", line);
		for (int j = 0; j < m; ++j)
			if (line[j] == '0') {
				h[j] = 0;
			} else {
				++h[j];
			}
		
		while (!st.empty()) st.pop();
		for (int j = 0; j < m; ++j) {
			while (!st.empty() && h[st.top()] >= h[j]) st.pop();
			lft[j] = st.empty() ? -1 : st.top() ;
			st.push(j);
		}

		while (!st.empty()) st.pop();
		for (int j = m - 1; j >= 0; --j) {
			while (!st.empty() && h[st.top()] >= h[j]) st.pop();
			rgt[j] = st.empty() ? m : st.top() ;
			st.push(j);
		}

		for (int j = 0, size; j < m; ++j) {
			size = h[j] * (rgt[j] - lft[j] - 1);
			if (size > answer) answer = size;
		}
	}

	cout << answer;
	return 0;
}