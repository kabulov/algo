
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
	
	String FileName = "sum2";
	
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
		fenw f = new fenw(n);
		for (int i = 0; i < n; ++i) f.put(i + 1, next());
//		int[] v = new int[n];
//		for (int i = 0; i < n; ++i) v[i] = next();
		
//		tree tr = new tree(n, v);
		
		int m = next();
		for (int i = 0, l, r; i < m; ++i) {
//			l = next() - 1;
//			r = next() - 1;

//			out.println(tr.sum(l, r));
			out.println(f.sum(next(), next()));
		}
	}	
	
	int n, m;
}

class fenw {
	long[] v;
	int sz;
	
	fenw(int size) {
		sz = size;
		v = new long[sz + 1];
		Arrays.fill(v, 0);
	}
	
	void put(int pos, long add) {
		for (; pos <= sz; pos = pos | (pos + 1))
			v[pos] += add;
	}
	
	long sum(int l, int r) {
		return get(r) - get(l - 1);
	}
	
	long get(int pos) {
		long ans = 0;
		for (; pos > 0; pos = (pos & (pos + 1)) - 1)
			ans += v[pos];
		return ans;
	}
}

class tree {
	long[] t;
	int sz;
	
	tree(int size, int[] src) {
		sz = size;
		t = new long[4 * sz];
		build(1, 0, sz - 1, src);
	}
	
	void build(int v, int lt, int rt, int[] src) {
		if (lt == rt) 
			t[v] = src[lt];
		else {
			int mt = (lt + rt) >>> 1;
			build(v + v, lt, mt, src);
			build(v + v + 1, mt + 1, rt, src);
			t[v] = t[v + v] + t[v + v + 1];
		}
	}
	
	long sum(int l, int r) {
		return get(1, 0, sz - 1, l, r);
	}
	
	long get(int v, int lt, int rt, int l, int r) {
		if (l == lt && r == rt)
			return t[v];
		else {
			int mt = (lt + rt) >>> 1;
			if (r <= mt) return get(v + v, lt, mt, l, r);
			if (mt + 1 <= l) return get(v + v + 1, mt + 1, rt, l, r);
			return get(v + v, lt, mt, l, mt) + get(v + v + 1, mt + 1, rt, mt + 1, r);
		}
	}
}