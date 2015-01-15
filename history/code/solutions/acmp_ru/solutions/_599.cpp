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

const int maxn = (int)1e5 + 11;
const long long inf = (long long)1e18;

int n;
int src[maxn + maxn + maxn];
char buf[10];

long long dpneg[26][2], dppos[26][2];

int main() {

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif

	cin >> n;
	if (n == 1) {
		cout << 0 << endl << 1;
		return 0;
	}
	
	for (int i = 0; i < n; ++i) {
		scanf("%s", buf);
		src[i] = buf[0] - 'a';
	}

	for (int i = 0; i < n; ++i) {
		src[i + n] = src[i + n + n] = src[i];
	}

	int pos = n;
	int lt = pos, rt = pos;
	while (rt - pos < (n >> 1)) ++rt;
	while (pos - lt < (n >> 1)) --lt;
	if (n % 2 == 0) ++lt;

	memset(dpneg, 0, sizeof(dpneg));
	memset(dppos, 0, sizeof(dppos));

	for (int i = pos + 1; i <= rt; ++i) {
		++dppos[src[i]][0];
		dppos[src[i]][1] += i - pos;
	}

	for (int i = pos - 1; i >= lt; --i) {
		++dpneg[src[i]][0];
		dpneg[src[i]][1] += pos - i;
	}

	long long minans = 0, minpos = pos;
	for(int i = 0; i < 26; ++i) {
		if (dppos[i][0] > 0) {
			minans += abs(src[pos] - i) * dppos[i][1];
		}
		if (dpneg[i][0] > 0) {
			minans += abs(src[pos] - i) * dpneg[i][1];
		}
	}
	
	while (pos + 1 < n + n) {
		++pos, ++rt, ++lt;

		for (int i = 0; i < 26; ++i) {
			dppos[i][1] -= dppos[i][0];
		}
		--dppos[src[pos]][0];
		++dppos[src[rt]][0];
		dppos[src[rt]][1] += rt - pos;

		for (int i = 0; i < 26; ++i) {
			dpneg[i][1] += dpneg[i][0];
		}
		++dpneg[src[pos - 1]][0];
		dpneg[src[pos - 1]][1] += 1;
		dpneg[src[lt - 1]][0]--;
		dpneg[src[lt - 1]][1] -= pos - lt + 1; 

		long long curmin = 0;

		for(int i = 0; i < 26; ++i) {
			if (dppos[i][0] > 0) {
				curmin += abs(src[pos] - i) * dppos[i][1];
			}
			if (dpneg[i][0] > 0) {
				curmin += abs(src[pos] - i) * dpneg[i][1];
			}
		}

		if (curmin > minans) {
			minans = curmin;
			minpos = pos;
		}
	}	
	cout << minans << endl << minpos + 1 - n;

	return 0;
}