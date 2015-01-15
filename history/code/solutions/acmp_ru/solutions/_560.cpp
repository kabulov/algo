
#include <cstdlib>
#include <math.h>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

class segment {
public:
	int size;
	stack<pair<int, int> > f, s; //num, min

	segment() {
		size = 0;
	}

	void add(int param) {
		if (f.empty()) {
			f.push(make_pair(param, param));
		} else {
			f.push(make_pair(param, min(param, f.top().second)));
		}

		++size;
	}

	void remove() {
		if (s.empty()) {
			//if f.empty() crash! erroneous
			s.push(make_pair(f.top().first, f.top().first));
			f.pop();
			while (!f.empty()) {
				s.push(make_pair(f.top().first, min(f.top().first, s.top().second)));
				f.pop();
			}
		}
		s.pop();
		--size;
	}

	int getmin() {
		//if both empty -> crash! erroneous
		if (f.empty()) return s.top().second;
		if (s.empty()) return f.top().second;
		return min(f.top().second, s.top().second);
	}

	void clear() {
		size = 0;
		while (!f.empty()) f.pop();
		while (!s.empty()) s.pop();
	}
};

int n, l, L, r, R;
int a[20000 + 100];
string nosol = "No solution.";

segment seg;
int dp[2][20000 + 10];
const int inf = (int)1e9;

int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	
	cin >> n >> l >> L >> r >> R;

	if (n * (l + r) + r > L || n * (l + R) + R < L) {
		cout << nosol;
		return 0;
	}

	a[0] = 0;
	for (int i = 1; i <= L; ++i) {
		scanf("%d", &a[i]);
		a[i] += a[i - 1];
	}

	dp[0][0] = inf;
	for (int i = 1; i <= L - r; ++i) {
		if (l + r > i || l + R < i) 
			dp[0][i] = inf;
		else 
			dp[0][i] = a[i] - a[i - l];
	}

	dp[1][0] = inf;
	int cur = 0, prev;
	for (int i = 2; i <= n; ++i) {
		prev = cur;
		cur ^= 1;
		seg.clear();

		for (int j = 1; j <= L - r; ++j) {
			if (j >= l + r) seg.add(dp[prev][j - l - r]);
			if (seg.size > R - r + 1) seg.remove();
			if (i * (l + r) > j || i * (l + R) < j || seg.getmin() == inf) {
				dp[cur][j] = inf;
			} else {
				dp[cur][j] = a[j] - a[j - l] + seg.getmin();
			}
		}
	}

	int ans = dp[cur][0];
	for (int i = L - R; i <= L - r; ++i) ans = min(ans, dp[cur][i]);
	if (ans == inf)
		cout << nosol;
	else
		cout << ans;

	return 0;
}