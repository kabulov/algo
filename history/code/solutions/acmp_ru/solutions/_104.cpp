
#include <cstdlib>
#include <cmath>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <stack>
#include <string>

using namespace std;

char str[300], pat[300];
int strsz, patsz;
char dp[300][300];

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	scanf("%s", str);
	scanf("%s", pat);
	strsz = strlen(str);
	patsz = strlen(pat);
	
	if (strsz == 0 || patsz == 0) while (true);		//what to do?

	char flag = false;
	for (int i = 0; i < strsz; ++i) 
		if (str[i] == '?' || str[i] == '*') {
			flag = true;
			break;
		}

	if (flag) {
		char tmp[300];
		for (int i = 0; i < strsz; ++i) tmp[i] = str[i];
		for (int i = 0; i < patsz; ++i) str[i] = pat[i];
		for (int i = 0; i < strsz; ++i) pat[i] = tmp[i];
		int buf = strsz;
		strsz = patsz;
		patsz = buf;
	}
	
	dp[0][0] = true;
	for (int i = 1; i <= strsz; ++i) dp[0][i] = false;

	int i = 1;

	while (i <= patsz && pat[i - 1] == '*') {
		dp[i][0] = true;
		++i;
	}

	while (i <= patsz) {
		dp[i][0] = false;
		++i;
	}

	for (i = 1; i <= patsz; ++i) {
		flag = dp[i - 1][0];
		for (int j = 1; j <= strsz; ++j) {
			flag |= dp[i - 1][j];
			
			if (pat[i - 1] == '?') {
				dp[i][j] = dp[i - 1][j - 1];
			} else	
			if (pat[i - 1] == '*') {
				dp[i][j] = flag;
			} else {
				if (pat[i - 1] == str[j - 1])
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = false;
			}
		}
	}	
	
	cout << (dp[patsz][strsz] ? "YES" : "NO");
	return 0;
}