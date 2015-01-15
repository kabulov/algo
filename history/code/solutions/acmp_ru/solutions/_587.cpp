
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <stack>

using namespace std;

const double pi = 3.14159265358979323846;
const int maxn = 100000 + 10;

int n;
pair<double, double> coord[maxn + maxn];
pair<double, pair<double, int> > inp[maxn + maxn];

double sum(int lt, int rt) {
	if (lt > rt) return 0;
	if (lt == 0) return coord[rt].first * coord[rt].first + coord[rt].second * coord[rt].second;	
	return (coord[rt].first - coord[lt - 1].first) * (coord[rt].first - coord[lt - 1].first) + (coord[rt].second - coord[lt - 1].second) * (coord[rt].second - coord[lt - 1].second);
}

int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n;
	for (int i = 0; i < n; ++i) {
		scanf("%lf %lf", &inp[i].second.first, &inp[i].first);
		inp[i].second.second = i + 1;
	}

	sort(inp, inp + n);
	
	for (int i = 0; i < n; ++i) {
		coord[i].first = inp[i].second.first * cos(inp[i].first * pi / 180.0);//x
		coord[i].second = inp[i].second.first * sin(inp[i].first * pi / 180.0);//y
	}

	for (int i = 0; i < n + 10; ++i) {
		inp[i + n] = inp[i];
		inp[i + n].first += 360.0;
		coord[i + n] = coord[i];
	}

	for (int i = 1; i < n + n + 10; ++i) {
		coord[i].first += coord[i - 1].first;
		coord[i].second += coord[i - 1].second;
	}		

	int i = 0, j = 0, bestlt = 0, bestrt = 0;
	double best = 0;

	for (; i < n; ++i) {
		if (j < i) ++j;//while

		while (j - i + 1 < n && inp[j + 1].first - inp[i].first < 180.0) {//<=
			++j;
		}

		double tmp = sum(i, j);
		
		if (tmp > best) {
			best = tmp;
			bestlt = i;
			bestrt = j;
		}

		tmp = sum(j + 1, i + n - 1);
		
		if (tmp > best) {
			best = tmp;
			bestlt = j + 1;
			bestrt = i + n - 1;
		}
	}
	
	cout << bestrt - bestlt + 1 << endl;
	for (int i = bestlt; i <= bestrt; ++i) {
		printf("%d ", inp[i].second.second);
	}

	return 0;

}