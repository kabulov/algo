
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int x, y;
	cin >> x >> y;

	if (x == y) {
		cout << "0";
		return 0;
	}

	if (x == 0 || y == 0) {
		cout << "-1";
		return 0;
	}

	if (x < 0 && y > 0 || x > 0 && y < 0) {
		cout << "-1";
		return 0;
	}

	if (x < 0) {//&& y < 0
		x = -x;
		y = -y;
	}

	if (x > y) {
		cout << "-1";
		return 0;	
	}

	if (y % x != 0) {
		cout << "-1";
		return 0;
	}

	y /= x;
	int answer = 0, d = 2;
	while (y > 1) {
		if (y % d == 0) {
			while (y % d == 0) {
				answer += d;
				y /= d;
			}
		} else {
			++d;
			if (d * d > y) break;
		}	
	}
	if (y > 1) {
		answer += y;
	}

	cout << answer;
	return 0;
}