
import static java.lang.Math.max;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	public void run() {
		try {
			in = new StreamTokenizer(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	void solve() throws IOException {
		int n = nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = nextInt();
		
		tree tr = new tree(n);
		tr.build(1, 0, n - 1, v);
		
		int m = nextInt();
		for (int i = 0; i < m; ++i) {
			out.println(tr.get(1, 0, n - 1, nextInt() - 1, nextInt() - 1));
		}
	}
}


class tree {
	int sz;
	data[] t;
	
	tree(int size) {
		sz = size;
		t = new data[4 * sz];
	}
	
	void build(int v, int lt, int rt, int[] src) {
		if (lt == rt)
			t[v] = new data(src[lt]);
		else {
			int mt = (lt + rt) >>> 1;
			build(v + v, lt, mt, src);
			build(v + v + 1, mt + 1, rt, src);
			t[v] = combine(t[v + v], t[v + v + 1]);
		}
	}
	
	int get(int v, int lt, int rt, int l, int r) {
		if (lt == l && rt == r) return t[v].ans;
		int mt = (lt + rt) >>> 1;
		if (r <= mt) return get(v + v, lt, mt, l, r);
		if (mt + 1 <= l) return get(v + v + 1, mt + 1, rt, l, r);
		return get(v + v, lt, mt, l, mt) + get(v + v + 1, mt + 1, rt, mt + 1, r);
	}
	
	data combine(data d1, data d2) {
		return new data(d1.sum + d2.sum, max(d1.pref, d1.sum + d2.pref), max(d2.suf, d1.suf + d2.sum), max(max(d1.ans, d2.ans), d1.suf + d2.pref));
	}
}

class data {
	int sum, pref, suf, ans;
	
	data(int val) {
		sum = ans = pref = suf = val;
	}
	
	data(int sm, int pr, int sf, int an) {
		sum = sm;
		pref = pr;
		suf = sf;
		ans = an;
	}
}

