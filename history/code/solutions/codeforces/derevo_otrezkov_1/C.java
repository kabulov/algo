
import static java.lang.Math.min;
import static java.lang.Math.max;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	String FileName = "sum";
	
	public void run() {
		try {
			in = new StreamTokenizer(new FileReader(FileName + ".in"));
			out = new PrintWriter(FileName + ".out");
//			in = new StreamTokenizer(new FileReader("in.txt"));
//			out = new PrintWriter("out.txt");
			solve();			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	void solve() throws IOException {
		n = next();
		k = next();
		
		tree tr = new tree(n);
		
		char c;
		for (int i = 0, l, r, x; i < k; ++i) {
			in.nextToken();
			c = in.sval.charAt(0);
			l = next() - 1;
			r = next() - 1;
			if (c == 'A') {
				x = next();
				tr.upd(1, 0, n - 1, l, r, x);
			} else {
				out.println(tr.sum(1, 0, n - 1, l, r));
			}
		}
	}	
	
	int n, k;
	
}

class tree {
	int sz;
	long[] t;
	int[] c;
	
	tree(int size) {
		sz = size;
		t = new long[4 * sz]; 
		c = new int[4 * sz];
		Arrays.fill(t, 0);
//		Arrays.fill(c, 0);
		build(1, 0, sz - 1);
	}
	
	void build(int v, int lt, int rt) {
		if (lt == rt) {
			c[v] = 0;
		} else {
			c[v] = -1;
			int mt = (lt + rt) >>> 1;
			build(v + v, lt, mt);
			build(v + v + 1, mt + 1, rt);
		}
	}
	
	void push(int v, long lt, long rt) {
		if (c[v] == -1) return;
		c[v + v] = c[v + v + 1] = c[v];
		
		t[v + v] = lt * c[v];	
		t[v + v + 1] = rt * c[v];
		
		c[v] = -1;
	}
	
	void upd(int v, int lt, int rt, int l, int r, int col) {
		if (l == lt && r == rt) {
			c[v] = col;
			t[v] = (r - l + 1) * (long)col;
		} else {
			int mt = (lt + rt) >>> 1;
			push(v, mt - lt + 1, rt - mt);
			
			if (r <= mt) 
				upd(v + v, lt, mt, l, r, col);
			else
			if (mt + 1 <= l)
				upd(v + v + 1, mt + 1, rt, l, r, col);
			else {
				upd(v + v, lt, mt, l, mt, col);
				upd(v + v + 1, mt + 1, rt, mt + 1, r, col);
			}
			
			t[v] = t[v + v] + t[v + v + 1];
		}
	}
	
	long sum(int v, int lt, int rt, int l, int r) {
		if (l == lt && r == rt) {
//			if (c[v] != -1) return (r - l + 1) * (long)c[v];
			return t[v]; //
		} else {
			long ans;
			int mt = (lt + rt) >>> 1;
			push(v, mt - lt + 1, rt - mt);
			
			if (r <= mt) 
				ans = sum(v + v, lt, mt, l, r);
			else
			if (mt + 1 <= l) 
				ans = sum(v + v + 1, mt + 1, rt, l, r);
			else
				ans = sum(v + v, lt, mt, l, mt) + sum(v + v + 1, mt + 1, rt, mt + 1, r);
			
			t[v] = t[v + v] + t[v + v + 1];
			return ans;
		}
	}
}