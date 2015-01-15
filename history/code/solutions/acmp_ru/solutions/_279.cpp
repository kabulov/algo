
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <hash_set>
#include <iostream>
#include <stack>
#include <string.h>

using namespace std;

bool open(char c) {
	return c == '(' || c == '[';
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	const int maxn = 100005;

	char v[maxn];
	scanf("%s", v);
	int n = strlen(v);

	int answer = 0;
	stack<char> st;

	for (int i = 0; i < n; ++i) {
		if (open(v[i])) {
			st.push(v[i]);
		} else {
			if (st.empty()) {
				st.push('a');
				break;
			}
			if (v[i] == ')' && st.top() == '[' || v[i] == ']' && st.top() == '(') ++answer;
			st.pop();
		}
	}
		
	if (!st.empty())
		cout << -1;
	else
		cout << answer;

	return 0;
}