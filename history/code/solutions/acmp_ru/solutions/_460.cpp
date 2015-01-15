
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <hash_set>
#include <iostream>

using namespace std;

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	long long n;
	cin >> n;

	int number[100];
	long long temp = n, pos = 0;

	while (temp > 0) {
		++pos;
		number[pos] = temp % 10;
		temp /= 10;
	}

	long long dp[10][100];
	for (int i = 0; i < 10; ++i) dp[i][0] = 0;
	
	long long sum[100];
	for (int i = 0; i < 100; ++i) sum[i] = 0;

	long long ten[100];
	ten[1] = 1;
	for (int i = 2; i < 20; ++i) ten[i] = ten[i - 1] * 10;

	for (int j = 1; j < pos; ++j) {
		sum[j] = 10 * sum[j - 1] + ten[j];

		//for (int i = 1; i < 10; ++i) {
		//	dp[i][j] = sum[j - 1];
		//	if (i == 5) dp[i][j] += ten[j];							
		//	sum[j] += dp[i][j];
		//}
	}
	
	long long answer = 0, five = 0, cur, dig;
	while (pos > 0) {
		if (number[pos] > 0) {
			for (int i = 1; i <= number[pos]; ++i) {
				answer += sum[pos - 1] + ten[pos] * five;
				if (i == 6) answer += ten[pos];			
			}
			if (number[pos] == 5) {
				//if (pos == 1) 
				//	++answer;			
				//else
					++five;
			}
		} //else
		//if (pos == 1) {
		//	answer += five;	
		//}
		--pos;
	}

	printf("%I64d", answer + five);
	return 0;
}