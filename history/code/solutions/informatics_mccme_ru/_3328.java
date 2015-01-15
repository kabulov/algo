
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;


public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	BufferedReader in; 
	PrintWriter out;
	
	void solve() throws IOException {
		Pattern pat = Pattern.compile(" ");
		String[] buf;
		
		int n = Integer.parseInt(in.readLine());
		buf = pat.split(in.readLine());
		
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = Integer.parseInt(buf[i]);
		
		tree tr = new tree(n);
		tr.build(1, 0, n - 1, v);
		
		int m = Integer.parseInt(in.readLine());
		for (int i = 0, lt, rt, add; i < m; ++i) {
			buf = pat.split(in.readLine());
			lt = Integer.parseInt(buf[0]);
			rt = Integer.parseInt(buf[1]);
			long mn;
			if (buf.length == 2) {//rmq
				if (lt <= rt) {
					mn = tr.get(1, 0, n - 1, lt, rt);
				} else {
					mn = min(tr.get(1, 0, n - 1, 0, rt), tr.get(1, 0, n - 1, lt, n - 1));
				}
				out.println(mn);
			} else {//upd
				add = Integer.parseInt(buf[2]);
				if (lt <= rt) {
					tr.upd(1, 0, n - 1, lt, rt, add);
				} else {
					tr.upd(1, 0, n - 1, 0, rt, add);
					tr.upd(1, 0, n - 1, lt, n - 1, add);
				}
			}
		}
	}
}

class tree {
	int sz;
	pair[] t;
	
	tree (int size) {
		sz = size;
		t = new pair[4 * sz];
	}
	
	void push(int v) {
		t[v + v].a += t[v].a;
		t[v + v + 1].a += t[v].a;
		t[v].a = 0;
	}
	
	void build(int v, int lt, int rt, int[] src) {
		if (lt == rt)
			t[v] = new pair(src[lt], 0);
		else {
			int mt = (lt + rt) >>> 1;
			build(v + v, lt, mt, src);
			build(v + v + 1, mt + 1, rt, src);
			t[v] = new pair(min(t[v + v].m, t[v + v + 1].m), 0);
		}
	}
	
	void upd(int v, int lt, int rt, int l, int r, int add) {
		if (l == lt && r == rt) 
			t[v].a += add;
		else {
			push(v);
			
			int mt = (lt + rt) >>> 1;
			if (r <= mt) 
				upd(v + v, lt, mt, l, r, add);
			else
			if (mt + 1 <= l)
				upd(v + v + 1, mt + 1, rt, l, r, add);
			else {
				upd(v + v, lt, mt, l, mt, add);
				upd(v + v + 1, mt + 1, rt, mt + 1, r, add);
			}
			
			t[v].m = min(t[v + v].m + t[v + v].a, t[v + v + 1].m + t[v + v + 1].a);
		}
	}
	
	long get(int v, int lt, int rt, int l, int r) {
		if (l == lt && r == rt)
			return t[v].a + t[v].m;
		else {
			push(v);
			int mt = (lt + rt) >>> 1;
			long ans;
			if (r <= mt) 
				ans = get(v + v, lt, mt, l, r);
			else
			if (mt + 1 <= l)
				ans = get(v + v + 1, mt + 1, rt, l, r);
			else
				ans = min(get(v + v, lt, mt, l, mt), get(v + v + 1, mt + 1, rt, mt + 1, r));
			t[v].m = min(t[v + v].m + t[v + v].a, t[v + v + 1].m + t[v + v + 1].a);
			return ans;	
		}
	}
}

class pair {
	long m, a;
	
	pair (long min, long add) {
		m = min;
		a = add;
	}
}