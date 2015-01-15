
#include <stdio.h>

const int maxn = 10 + 10;

int n, m, k;
int vect[maxn];
int answer[maxn];

int order[1 << 16][16];
unsigned short tomask[1 << 16][16];
long long dp[1 << 16][16];
short size[1 << 16];

//int tomask(int mask, int p) {
//	for (int j = 0; j < n; ++j) {
//		if (((mask >> j) & 1) == 1) {
//			if (p == 0) return mask ^ (1 << j);
//			--p;//p>=0
//		}
//	}
//	return -1;//very bad
//}

//int size(int mask) {
//	int ans = 0;
//	while (mask > 0) {
//		++ans;
//		mask &= mask - 1;
//	}
//	return ans;
//}

void init() {
	int tmp;
	
	//size
	for (int i = 0; i < (1 << n); ++i) {
		size[i] = 0;
		tmp = i;
		while (tmp > 0) {
			++size[i];
			tmp = tmp & (tmp - 1);	//??
		}	
	}

	//tomask
	for (int i = 0; i < (1 << n); ++i) {
		tmp = 0;
		for (int j = 0; j < n; ++j) {
			if (((i >> j) & 1) == 1) {
				tomask[i][tmp] = (unsigned short)(i ^ (1 << j)); //??
				++tmp;
			}
		}
	}

	//order
	for (int i = 0; i < (1 << n); ++i) {
		tmp = 0;
		for (int j = 0; j < n; ++j) {
			if (((i >> j) & 1) == 1) {
				order[i][tmp] = vect[j]; //??
				++tmp;
			}
		}
	}
	
	//dp
	for (int i = 0; i < (1 << n); ++i) {
		for (int j = 0; j < n; ++j) {
			dp[i][j] = -1;
		}
	}

	//pos
}

int gcd(int a, int b) {
	return b == 0 ? a : gcd(b, a % b);
}

void precalc(int mask, int p) {
	//maybe parameters need
	dp[mask][p] = 0;
	
	//p < size[mask]
	int sz = size[mask];
	if (sz == 1) {
		dp[mask][p] = 1; //all vect[i] >= k
		return;
	}
	
	int fst = order[mask][p];
	int to = (unsigned int)tomask[mask][p];//tomask(mask, p);
	//if (to < 0) while (true);

	for (int j = 0, szto = size[to]; j < szto; ++j) {
		if (gcd(fst, order[to][j]) < k) continue;
		if (dp[to][j] == -1) precalc(to, j);		
		dp[mask][p] += dp[to][j];
	}
}

void solve(int mask, int pos, long long sum) {
	if (pos + 1 == n) return;	
		
	long long s = 0;

	for (int i = 0, szmask = size[mask], to; i < szmask; ++i) {
		to = order[mask][i];
		if (gcd(answer[pos], to) < k) continue;
		if (s + dp[mask][i] >= sum) {
			answer[pos + 1] = order[mask][i];
			solve((unsigned int)tomask[mask][i]/*tomask(mask, i)*/, pos + 1, sum - s);
			break;
		}
		s += dp[mask][i];
	}

}

int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	scanf("%d %d %d", &n, &m, &k);
	for (int i = 0; i < n; ++i) scanf("%d", &vect[i]);

	if (n == 1) {
		if (m == 1) {
			printf("%d", vect[0]);
		} else {
			printf("-1");
		}
		return 0;
	}

	for (int i = 0; i < n; ++i) {
		if (vect[i] < k) {
			printf("-1");
			return 0;
		}
	}

	//-1 esli m > chisla k-perestanovok
	for (int i = 0; i < n; ++i) {
		int m = i;

		for (int j = i + 1; j < n; ++j)
			if (vect[j] < vect[m])
				m = j;

		if (m > i) {
			int tmp = vect[m];
			vect[m] = vect[i];
			vect[i] = tmp;
		}
	}

	init();

	for (int mask = 1; mask < (1 << n); ++mask) {//from 1
		for (int j = 0, szmask = size[mask]; j < szmask; ++j) {
			if (dp[mask][j] == -1) {
				precalc(mask, j);
			}
		}
	}
	
	long long s = 0;
	
	for (int i = 0; i < n; ++i) {
		s += dp[(1 << n) - 1][i];
	}

	long long M = m;
	if (M > s) {
		printf("-1");
		return 0;
	}

	s = 0;

	for (int i = 0; i < n; ++i) {
		if (s + dp[(1 << n) - 1][i] >= m) {
			answer[0] = order[(1 << n) - 1][i];
			solve((unsigned int)tomask[(1 << n) - 1][i]/*tomask((1 << n) - 1, i)*/, 0, m - s); //here is solution
			break;
		}//else		
		
		s += dp[(1 << n) - 1][i];
	}

	for (int i = 0; i < n; ++i) {
		printf("%d ", answer[i]);
	}

	return 0;
}