#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <stack>
#include <vector>

using namespace std;

class Treap {
public:
	int x;
	int y;

	Treap* left;
	Treap* right;
	Treap* parent;

	Treap(int x, int y, Treap* lt = NULL, Treap* rt = NULL, Treap* par = NULL):x(x),y(y),left(lt),right(rt),parent(par) {}

	static Treap* merge(Treap* lt, Treap* rt) {
		if (lt == NULL) return rt;
		if (rt == NULL) return lt;

		if (lt->y > rt->y) {//>=
			Treap* var = merge(lt->right, rt);
			return new Treap(lt->x, lt->y, lt->left, var);
		} else {
			Treap* var = merge(lt, rt->left);
			return new Treap(rt->x, rt->y, var, rt->right);
		}
	}

	void split(int x, Treap*& L, Treap*& R) {
		Treap* mid = NULL;

		if (this->x <= x) {
			if (right == NULL)
				R = NULL;
			else
				this->right->split(x, mid, R);
			L = new Treap(this->x, this->y, this->left, mid);	
		} else {
			if (left == NULL) 
				L = NULL;
			else
				this->left->split(x, L, mid);
			R = new Treap(this->x, this->y, mid, this->right);
		}
	}

	Treap* add(int x) {
		Treap *L, *R, *M;
		this->split(x, L, R);
		M = new Treap(x, rand());
		return merge(merge(L, M), R);
	}

	Treap* remove(int x) {
		Treap *L, *M, *R;
		this->split(x - 1, L, R);
		R->split(x, M, R);
		return merge(L, R);
	}

	static Treap* Build(int *x, int *y, int len) {
		Treap* last = new Treap(x[0], y[0]);

		for (int i = 1; i < len; ++i) {
			if (last->y > y[i]) {
				last->right = new Treap(x[i], y[i], NULL, NULL, last);
				last = last->right;
			} else {
				while (last->parent != NULL && last->y <= y[i]) last = last->parent;
				
				if (last->y <= y[i]) {
					last = new Treap(x[i], y[i], last);
				} else {
					last->right = new Treap(x[i], y[i], last->right, NULL, last);
					last = last->right;
				}
			}
		}

		while (last->parent != NULL) last = last->parent;
		return last;
	}

	void trace() {
		if (this == NULL) return;
		this->left->trace();
		cout << this->x << " ";
		this->right->trace();
	}
};

int main() {
	
	/*Treap* lt = new Treap(3, 1);
	Treap* rt = new Treap(10, 2);
	Treap* mid = Treap::merge(lt, rt);
	cout << mid->x << endl;
	mid = mid->add(20);
	lt = mid;
	while (mid->right != NULL) mid = mid->right;
	cout << mid->x << endl;

	lt = lt->remove(20);
	while (lt->right != NULL) lt = lt->right;
	cout << lt->x << endl;
	
	//mid->split(2, lt, rt);
	//cout << rt->left->x<< endl;

	*/

	int* x = new int[10];
	int* y = new int[10];

	for (int i = 0; i < 10; ++i) x[i] = i, y[i] = rand();

	Treap* t = Treap::Build(x, y, 10);
	t->trace();
	
	

	return 0;
}