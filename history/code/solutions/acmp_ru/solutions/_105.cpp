
#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <stack>

using namespace std;

char inputstr[1000];
int inputlen;

struct Triple {
	bool type;
	int n;
	char c; 
};

typedef struct Triple triple;

int leftnumber;
triple src[100];
int srclen;

int posvect[100];
int posvectlen;

triple vect[100];
int vectlen;

stack<int> numst;
stack<char> charst;

char check() {
	while (!numst.empty()) numst.pop();
	while (!charst.empty()) charst.pop();

	for (int i = 0; i < vectlen; ++i) {
		if (vect[i].type == 1) {
			numst.push(vect[i].n);
		} else {
			if (vect[i].c == '(') {
				charst.push('(');
			} else 
			if (vect[i].c == ')') {
				while (charst.top() != '(') {
					int numscd = numst.top();
					numst.pop();
					int numfst = numst.top();
					numst.pop();
					if (charst.top() == '+') {
						numst.push(numfst + numscd);
					} else
					if (charst.top() == '-') {
						numst.push(numfst - numscd);
					} else
					if (charst.top() == '*') {
						numst.push(numfst * numscd);
					}
					charst.pop();
				}
				charst.pop();
			} else {//op
				//vitalkivaem
				while (!charst.empty() && charst.top() != '(') {					
					int numscd = numst.top();
					numst.pop();
					int numfst = numst.top();
					numst.pop();
					if (charst.top() == '+') {
						numst.push(numfst + numscd);
					} else
					if (charst.top() == '-') {
						numst.push(numfst - numscd);
					} else
					if (charst.top() == '*') {
						numst.push(numfst * numscd);
					}
					charst.pop();
				}
				charst.push(vect[i].c);
			}
		} 
	}

	while (!charst.empty()) {
		int numscd = numst.top();
		numst.pop();
		int numfst = numst.top();
		numst.pop();
		if (charst.top() == '+') {
			numst.push(numfst + numscd);
		} else
		if (charst.top() == '-') {
			numst.push(numfst - numscd);
		} else
		if (charst.top() == '*') {
			numst.push(numfst * numscd);
		}
		charst.pop();
	}

	return (numst.top() == leftnumber);
}

char gen(int pos) {
	if (pos == posvectlen) {
		return check();
	} 

	vect[posvect[pos]].c = '+';
	if (gen(pos + 1)) return true;
	vect[posvect[pos]].c = '-';
	if (gen(pos + 1)) return true;
	vect[posvect[pos]].c = '*';
	if (gen(pos + 1)) return true;

	return false;
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	gets(inputstr);
	inputlen = strlen(inputstr);
	
	leftnumber = 0;
	srclen = 0;

	int pos = 0;
	while (inputstr[pos] == ' ') ++pos;

	while ('0' <= inputstr[pos] && inputstr[pos] <= '9') {
		leftnumber = leftnumber * 10 + inputstr[pos] - '0';
		++pos;
	}

	while (inputstr[pos] == ' ') ++pos;
	++pos;

	while (pos < inputlen) {
		while (pos < inputlen && inputstr[pos] == ' ') ++pos;
		if (pos == inputlen) break;

		if ('0' <= inputstr[pos] && inputstr[pos] <= '9') {
			src[srclen].type = 1; //number
			src[srclen].n = 0;
			while (pos < inputlen && '0' <= inputstr[pos] && inputstr[pos] <= '9') {
				src[srclen].n = src[srclen].n * 10 + inputstr[pos] - '0';
				++pos;
			}
		} else {
			src[srclen].type = 0; //char
			src[srclen].c = inputstr[pos];
			++pos;
		}
		++srclen;
	}

	vectlen = posvectlen = 0;
	
	pos = 0;
	int state = 0;
	while (pos < srclen) {
		if (src[pos].type == 1) {//number
			if (state == 0 || state == 2) {
				vect[vectlen] = src[pos];
				++vectlen;
			} else {
				posvect[posvectlen] = vectlen;
				++posvectlen;
				vect[vectlen].type = 0;
				++vectlen;
				vect[vectlen] = src[pos];
				++vectlen;
			} 
			state = 1;
		} else 
		if (src[pos].c == '(') {//open
			if (state == 0 || state == 2) {
				vect[vectlen] = src[pos];
				++vectlen;
			} else {
				posvect[posvectlen] = vectlen;
				++posvectlen;
				vect[vectlen].type = 0;
				++vectlen;
				vect[vectlen] = src[pos];
				++vectlen;
			}
			state = 2;
		} else {//close
			if (state != 2) {
				vect[vectlen] = src[pos];
				++vectlen;
			} else {
				posvect[posvectlen] = vectlen;
				++posvectlen;
				vect[vectlen].type = 0;
				++vectlen;
				vect[vectlen] = src[pos];
				++vectlen;
			}
			state = 3;
		}		
		++pos;
	}

	char result = gen(0);

	if (!result) {
		cout << -1;
	} else {
		cout << leftnumber << "=";
		for (int i = 0; i < vectlen; ++i) {
			if (vect[i].type == 1) {
				cout << vect[i].n;
			} else {
				cout << vect[i].c;
			}
		}
	}

	return 0;
}