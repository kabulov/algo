//
//#include <cstdlib>
//#include <math.h>
//#include <cstdio>
//#include <algorithm>
//#include <iostream>
//#include <stack>
//#include <string>
//#include <ctime>
//
//using namespace std;
//
//typedef long double ld;
//
//struct mytype {
//	int pos, amt;
//	ld ang, len;
//};
//
//typedef struct mytype four;
//
//ld pi = 3.14159265358979323846;
//
//int n;
//pair<ld, pair<ld, int> > vect[100000]; //1e5
//
//ld rad2deg(ld param) {
//	return param * pi / 180.0;
//}
//
//ld eps = 1e-10; //??
//
//void add(ld ang1, ld len1, ld ang2, ld len2, ld& ang, ld& len) {
//	//len2 > 0
//	//ang1 <= ang2
//	//ang2 - ang1 < pi
//	len = sqrt( len1 * len1 + len2 * len2 - 2 * len1 * len2 * cos(pi - (ang2 - ang1)) );
//	ang = ang1 + acos((len * len + len1 * len1 - len2 * len2) / (2 * len * len1));	//ok ?? maybe < 0 ??
//}
//
//void sub(ld ang1, ld len1, ld ang2, ld len2, ld& ang, ld& len) {
//	//ang1 <= ang2
//	//if ang2 - ang1 < pi : guaranteed ??? erroneous
//	//if len2 == 0
//	len = sqrt( len1 * len1 + len2 * len2 - 2 * len1 * len2 * cos(pi - (pi - (ang2 - ang1))) );
//	ang = ang2 + acos((len2 * len2 + len * len - len1 * len1) / (2 * len2 * len)); // ok ?? maybe < 0 ??
//}
//
//pair<ld, ld> inp[100];
//
//int random(int base) {
//	return rand() % base;
//}
//
//void testgen(int &n, int base, int size) {
//	srand(time(NULL));
//	n = base + random(size);
//	for (int i = 0; i < n; ++i) {
//		inp[i].first = random(360);
//		inp[i].second = random(10000);
//	}
//	sort(inp, inp + n);
//}
//
//int position[100], poslen;
//
//ld solve() {
//	poslen = 0;
//	ld x = 0, y = 0;
//	for (int i = 0; i < (1 << n); ++i) {
//		ld curx = 0, cury = 0;
//		for (int j = 0; j < n; ++j) {
//			if ((i >> j) & 1) {
//				curx += inp[j].second * cos(rad2deg(inp[j].first));
//				cury += inp[j].second * sin(rad2deg(inp[j].first));
//			}
//		}
//		if (curx * curx + cury * cury > x * x + y * y) {
//			x = curx;
//			y = cury;
//			poslen = 0;
//			for (int j = 0; j < n; ++j) 
//				if ((i >> j) & 1) {
//					position[poslen++] = j;
//				}
//		}
//	}
//	return sqrt(x * x + y * y);
//}
//
//ld myangle(ld x1, ld y1, ld x2, ld y2) {
//	ld ang1, ang2;
//	ang1 = atan2(y1, x1);
//	ang2 = atan2(y2, x2);
//	if (ang1 < 0 && ang2 > 0) ang2 += pi + pi;	//!!
//
//	if (ang1 < 0) ang1 += pi + pi;
//	if (ang2 < 0) ang2 += pi + pi;
//	return ang2 - ang1;
//}
//
//int main() {
//	freopen("input.txt", "r", stdin);
//	freopen("output.txt", "w", stdout);
//
////	while (true){
//	cin >> n;
//	{
////		int size, base;
////		cin >> base >> size;
//		//testgen(n, base, size);
////		testgen(n, 20, 1);
//		//cout << n << endl;
//		//for (int i = 0; i < n; ++i) {
//		//	cout << inp[i].second << " " << inp[i].first << endl;
//		//}	
//	}
//
//	for (int i = 0; i < n; ++i) {
//		vect[i].second.second = i + 1;
//		//read
//		//{
//		//	vect[i].first = inp[i].first;
//		//	vect[i].second.first = inp[i].second;
//		//}
//		scanf("%lf %lf", &vect[i].second.first, &vect[i].first);
//		//read
//		if (vect[i].second.first < 0) {
//			if (vect[i].first > 180) {
//				vect[i].first -= 180;
//			} else {
//				vect[i].first += 180;
//			}
//			vect[i].second.first *= -1; //!
//		}
//	}
//	
//	sort(vect, vect + n);
//	
//	for (int i = 0; i < n; ++i) {
//		vect[i].first = rad2deg(vect[i].first);
//	}
//
//	for (int i = 0; i < n; ++i) {
//		ld a = vect[i].first;
//		ld l = vect[i].second.first;
//		vect[i].first = l * cos(a);
//		vect[i].second.first = l * sin(a);
//
//		vect[i + n] = vect[i];
//	}
//
//	{
//		ld x = 0, y = 0;
//		for (int i = 1; i < 13; ++i) {
//			x += vect[i].first;
//			y += vect[i].second.first;
//			cout << sqrt(x * x + y * y) << endl;
//		}
//		x += vect[14].first;
//		y += vect[14].second.first;
//		cout << sqrt(x * x + y * y) << endl;
//		x += vect[13].first;
//		y += vect[13].second.first;
//		cout << sqrt(x * x + y * y) << endl;
//	}
//
//	four cur, best;
//	
//	cur.pos = 0;
//	cur.amt = 1;
//	cur.ang = vect[0].first;
//	cur.len = vect[0].second.first;
//
//	{
//		ld ang, len, tmpx, tmpy;
//		for (; cur.amt < n;) {
//			//if (myangle(cur.ang, cur.len, vect[cur.amt].first, vect[cur.amt].second.first) >= pi - eps) break; //eps??
//			tmpx = cur.ang + vect[cur.amt].first;
//			tmpy = cur.len + vect[cur.amt].second.first;
//			if (tmpx * tmpx + tmpy * tmpy + eps < cur.ang * cur.ang + cur.len *  cur.len) break;
//			cur.ang += vect[cur.amt].first;
//			cur.len += vect[cur.amt].second.first;
//			++cur.amt; 
//		}
//	}
//
//	best = cur; //copy??
//
//	{
//		ld ang, len, tmpx, tmpy;
//		++cur.pos;
//		while (cur.pos < n) {
//			//subtract
//			//if amt == 1 then ....
//			--cur.amt;
//			if (cur.amt == 0) {
//				cur.ang = 0.0;
//				cur.len = 0.0;
//			} else {
//				//sub(vect[cur.pos - 1].first, vect[cur.pos - 1].second.first, cur.ang, cur.len, ang, len); //fparamang <= sparamang
//				cur.ang -= vect[cur.pos - 1].first;
//				cur.len -= vect[cur.pos - 1].second.first;
//			}
//
//			//add
//			while (cur.amt < n) {
//				if (cur.amt == 0) {
//					cur.ang = vect[cur.pos + cur.amt].first;
//					cur.len = vect[cur.pos + cur.amt].second.first;
//					cur.amt = 1;
//				} else {
//					//if (myangle(cur.ang, cur.len, vect[cur.pos + cur.amt].first, vect[cur.pos + cur.amt].second.first) >= pi - eps) break; //eps??					
//					tmpx = cur.ang + vect[cur.pos + cur.amt].first;
//					tmpy = cur.len + vect[cur.pos + cur.amt].second.first;
//					if (tmpx * tmpx + tmpy * tmpy + eps < cur.ang * cur.ang + cur.len *  cur.len) break;
//					cur.ang += vect[cur.pos + cur.amt].first;
//					cur.len += vect[cur.pos + cur.amt].second.first;
//					++cur.amt;
//				}
//			}
//
//			//optimize
//			if (cur.len * cur.len + cur.ang * cur.ang - eps > best.len * best.len + best.ang * best.ang) {
//				best = cur;
//			}
//
//			++cur.pos;
//		}
//	}
//
//
//	//for (int i = 0; i < n; ++i) {
//	//	vect[n + i] = vect[i];
//	//	vect[n + i].first += 2 * pi;
//	//}
//
//	//four cur, best;
//	//
//	//cur.pos = 0;
//	//cur.amt = 1;
//	//cur.ang = vect[0].first;
//	//cur.len = vect[0].second.first;
//
//	//{
//	//	ld ang, len;
//	//	for (; cur.amt < n;) {
//	//		if (vect[cur.amt].first - cur.ang >= pi) break; //eps??
//	//		add(cur.ang, cur.len, vect[cur.amt].first, vect[cur.amt].second.first, ang, len);
//	//		if (len < cur.len) break; //eps ?? 
//	//		cur.ang = ang;
//	//		cur.len = len;
//	//		++cur.amt; 
//	//	}
//	//}
//
//	//best = cur; //copy??
//
//	//{
//	//	ld ang, len;
//	//	++cur.pos;
//	//	while (cur.pos < n) {
//	//		//subtract
//	//		//if amt == 1 then ....
//	//		--cur.amt;
//	//		if (cur.amt == 0) {
//	//			cur.ang = 0.0;
//	//			cur.len = 0.0;
//	//		} else {
//	//			sub(vect[cur.pos - 1].first, vect[cur.pos - 1].second.first, cur.ang, cur.len, ang, len); //fparamang <= sparamang
//	//			cur.ang = ang;
//	//			cur.len = len;
//	//		}
//
//	//		//add
//	//		while (cur.amt < n) {
//	//			if (cur.amt == 0) {
//	//				cur.ang = vect[cur.pos + cur.amt].first;
//	//				cur.len = vect[cur.pos + cur.amt].second.first;
//	//				cur.amt = 1;
//	//			} else {
//	//				if (vect[cur.pos + cur.amt].first - cur.ang >= pi) break; //eps??
//	//				add(cur.ang, cur.len, vect[cur.pos + cur.amt].first, vect[cur.pos + cur.amt].second.first, ang, len);
//	//				if (len < cur.len) break; //eps??
//	//				cur.len = len;
//	//				cur.ang = ang;
//	//				++cur.amt;
//	//			}
//	//		}
//
//	//		//optimize
//	//		if (cur.len > best.len) {
//	//			best = cur;
//	//		}
//
//	//		++cur.pos;
//	//	}
//	//}
//
//	if (cur.len * cur.len + cur.ang * cur.ang - eps > best.len * best.len + best.ang * best.ang) {
//		best = cur;
//	}
//
//	best.ang = sqrt(best.ang * best.ang + best.len * best.len);
//
//		//ld sol = solve();
//		//if (abs(sol - best.ang) > eps) {
//		//	cout << n << endl;
//		//	for (int i = 0; i < n; ++i) {
//		//		cout << inp[i].first << " " << inp[i].second << endl;
//		//	}	
//		//	cout << endl << sol << endl << best.ang;	
//		//	cout << endl << poslen << endl << endl;
//		//	for (int i = 0; i < poslen; ++i) cout << position[i] + 1 << endl;
//
//		//	break;
//		//}
////	cout << endl << solve() << endl << best.ang;	
////	cout << endl << poslen << endl << endl;
////	for (int i = 0; i < poslen; ++i) cout << position[i] + 1 << endl;
//	cout << best.ang << " " << best.amt << endl;
//	for (int i = 0; i < best.amt; ++i) {
//		printf("%d", vect[best.pos + i].second.second);
//		printf(" ");
//	}
////	}
//	return 0;
//}