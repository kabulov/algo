
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>

using namespace std;
//int count(int h, int f, int t, int tmax, int x) {
//	if (x < f) return 0;
//	if (x <= f + h - 1) return 1 + min((x - f) / t, tmax);	//bound also
//	int tsmall, tbig;
//	tsmall = min((x - f - h) / t, tmax);
//	tbig = min((x - f) / t, tmax);	//tbig cannot be bigger than ...
//	return tbig - tsmall;
//}
int fst[200010], scd[200010], tfst[200010], tscd[200010];
int x[200010];

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int u, h, t, n, l;
//	cin >> u >> h >> t >> l >> n;
	scanf("%d %d %d %d %d", &u, &h, &t, &l, &n);
	int tmax = l <= u ? 0 : (l - u + t - 1) / t;

	int dp[1000];
	for (int i = 0; i <= u; ++i) dp[i] = 0;

	for (int i = 0; i < n; ++i) scanf("%d", &x[i]);

	int f, tsmall, tbig, tmp, bnd = u - h, s, ans = (int)1e9;
	//int x;
	//for (i = 0; i < n; ++i) {
	//	scanf("%d", &x);
	//	for (f = 0; f <= bnd; ++f) {
	//		if (x < f) continue;
	//		if (x <= f + h - 1) {
	//			tmp = (x - f) / t;
	//			if (tmp > tmax) tmp = tmax;
	//			dp[f] += 1 + tmp;	//bound also
	//		} else {
	//			tmp = (x - f - h) / t;
	//			if (tmp > tmax) tmp = tmax;
	//			tsmall = tmp;
	//			tmp = (x - f) / t;
	//			if (tmp > tmax) tmp = tmax;
	//			tbig = tmp;	//tbig cannot be bigger than ...
	//			dp[f] += tbig - tsmall;
	//		}
	//	}
	//}
	int l1, l2, X, position = 0;
	
	s = 0;
	for (int i = 0; i < n; ++i) {
		fst[i] = x[i] / t;
		tfst[i] = t * fst[i];
//		if (x[i] > h - 1) 
			scd[i] = (x[i] - h) / t;
			tscd[i] = t * scd[i];
//		else
//			scd[i] = -1;

		if (x[i] <= h - 1) {
			s += 1 + min(tmax, fst[i]);
		} else {
			s += min(tmax, fst[i]) - min(tmax, scd[i]);
		}
	}
	if (s < ans) ans = s;
	
	int i;
	l1 = h - 1;
	l2 = h;
	for (f = 1; f <= bnd; ++f) {
		s = 0;
		++l1;
		++l2;
		for (i = 0; i < n; ++i) {
			X = x[i];
			if (X < f) continue;

			//if (t * fst[i] > X - f) --fst[i];
			//if (t * fst[i] + f > X) --fst[i];
			if (tfst[i] + f > X) { --fst[i]; tfst[i] -= t;}

			if (X <= l1) {
				tmp = fst[i];
//				if (tmp > tmax) tmp = tmax;
				s += 1 + (tmp > tmax ? tmax : tmp);//tmp;	//bound also
			} else {
				//if (t * scd[i] > X - f - h) --scd[i];
				//if (t * scd[i] + l2 > X) --scd[i];
				if (tscd[i] + l2 > X) {--scd[i]; tscd[i] -= t;}

				tsmall = scd[i];
				if (tsmall > tmax) tsmall = tmax;
//				tsmall = tmp;
				tbig = fst[i];
				if (tbig > tmax) tbig = tmax;
//				tbig = tmp;	//tbig cannot be bigger than ...
				s += tbig - tsmall;
			}
		}
		if (s < ans) {
			ans = s;
			position = f;
		}
	}

//	int mn = dp[0];
//	for (i = 1; i <= u - h; ++i) mn = min(mn, dp[i]);
//	cout << mn;
////	cout << ans;
	//printf("%d %d", position, ans);
	printf("%d", ans);

	return 0;
}
