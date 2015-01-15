
#include <cmath>
#include <cstdlib>
#include <cstdio>
#include <algorithm>
#include <hash_set>
#include <iostream>

using namespace std;

double eps = 1e-10;

bool zero(double a, double b) {
	return abs(a - b) < eps;
}

const int maxn = 300;
pair<double, double> inp[maxn];
int answer[maxn];
int n;

bool cmp(int fst, int scd) {
	pair<double, double> f, s;
	double t1, t2;

	f = inp[answer[fst]];
	s = inp[answer[scd]];
	
	if (f.first > s.second) {
		t1 = s.second + (f.first - s.second) * f.second / (f.first + f.second);	
	} else {
		t1 = f.first + (s.second - f.first) * s.first / (s.first + s.second);
	}

	if (f.second < s.first) {
		t2 = f.second + (s.first - f.second) * s.second / (s.first + s.second);
	} else {
		t2 = s.first + (f.second - s.first) * f.first / (f.first + f.second);
	}

	return t1 < t2;
}

void Sort() {
	for (int i = 1; i < n; ++i)
		for (int j = n - 1; j >= i; --j)
			if (cmp(j - 1, j)) {
				int tmp = answer[j - 1];
				answer[j - 1] = answer[j];
				answer[j] = tmp;
			}
}

int main() {
	
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	cin >> n;

	for (int i = 0; i < n; ++i)
		cin >> inp[i].first >> inp[i].second;

	for (int i = 0; i < n; ++i)
		answer[i] = i;
	
	Sort();
	//sort(answer, answer + n, cmp); must > in comparator , vse ravno wrong answer; nujno bubble sort

	double time = 0;
	int lt = 0, rt = n - 1;

	while (lt <= rt) {
		pair<double, double> &f = inp[answer[lt]];
		pair<double, double> &s = inp[answer[rt]];
		
		if (lt == rt) {
			time += f.first * f.second / (f.first + s.second);
			break;
		}
		
		if (zero(f.first, s.second)) {
			time += f.first;
			++lt;
			--rt;
			continue;
		}
		if (f.first < s.second) {
			time += f.first;
			s.first *= (s.second - f.first) / s.second;
			s.second -= f.first;			
			++lt;
		} else {
			time += s.second;
			f.second *= (f.first - s.second) / f.first;
			f.first -= s.second;			
			--rt;
		}
	}

	printf("%.10lf\n", time);
	for (int i = 0; i < n; ++i)
		cout << answer[i] + 1 << " ";

	return 0;
}