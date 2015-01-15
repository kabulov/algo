
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <vector>
#include <stack>

using namespace std;

const int maxn = 111;
int a, b, c, k;

int one[5000][3];
int len;

short dp[maxn][maxn][maxn], place[2][maxn * maxn * maxn][3];
int size[2];

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> a >> b >> c >> k;
	if (a + b + b + c + c + c < k) {
		cout << 0;
		return 0;
	}

	len = 0;
	for (int i = 0; i <= c; ++i) {
		if (i + i + i >= k) {
			one[len][0] = i;
			one[len][1] = one[len][2] = 0;
			++len;
			break;
		} 
		for (int j = 0; j <= b; ++j) {
			if (i + i + i + j + j >= k) {
				one[len][0] = i;
				one[len][1] = j;
				one[len][2] = 0;
				++len;
				break;
			}
			for (int t = 0; t <= a; ++t) {
				if (i + i + i + j + j + t >= k) {
					one[len][0] = i;
					one[len][1] = j;
					one[len][2] = t;
					++len;
					break;
				}
			}
		}
	}

//	cout << len;
	
	if (k <= 20) {	//best when k = 20 +- something; but works even if k == 0 :)
		dp[0][0][0] = 0;
		int x, y, z, tmp;
		for (int i = 0; i <= c; ++i) {
			for (int j = 0; j <= b; ++j) {
				for (int t = 0; t <= a; ++t) {
					dp[i][j][t] = 0;
					for (int p = 0; p < len; ++p) {
						x = one[p][0], y = one[p][1], z = one[p][2];
						if (x > i || y > j || z > t) continue;
						tmp = dp[i - x][j - y][t - z] + 1;
						if (tmp > dp[i][j][t]) dp[i][j][t] = tmp;
					}
				}
			}
		}		
		cout << dp[c][b][a];
	} else {
		for (int i = 0; i <= c; ++i)
			for (int j = 0; j <= b; ++j)
				for (int t = 0; t <= a; ++t)
					dp[i][j][t] = 0;

		for (int i = 0; i < len; ++i) {
			place[0][i][0] = one[i][0];
			place[0][i][1] = one[i][1];
			place[0][i][2] = one[i][2];
			dp[one[i][0]][one[i][1]][one[i][2]] = 1;
		}

		size[0] = len;
//		size[1] = 0;	!!

		int cur = 0, next = 0, step = 1;
		int x, y, z, tmp;

		do {
			++step;
			cur = next;
			next = cur ^ 1;
			tmp = 0;

			for (int i = 0, sz = size[cur]; i < sz; ++i) {
				for (int j = 0; j < len; ++j) {
					x = place[cur][i][0] + one[j][0];
					y = place[cur][i][1] + one[j][1];
					z = place[cur][i][2] + one[j][2];
					if (x > c || y > b || z > a) continue;
					if (step > dp[x][y][z]) {
						dp[x][y][z] = step;
						place[next][tmp][0] = x;
						place[next][tmp][1] = y;
						place[next][tmp][2] = z;
						++tmp;
					}
				}	
			}

			size[next] = tmp;
		} while (size[next] != 0);

		cout << step - 1;
	}

	return 0;
}