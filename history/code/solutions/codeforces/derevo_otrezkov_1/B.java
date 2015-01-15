
import static java.lang.Math.min;
import static java.lang.Math.max;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	String FileName = "rvq";
	
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
		sz = 100000;
		v = new int[sz];
		
		a = 12345;
		b = 23456;
		
		for (int i = 0, t; i < sz; ++i) {
			t = (i + 1) % a;
			v[i] = (t * t) % a;
			t = (i + 1) % b;
			v[i] += (t * t % b) * t % b;
		}
		
		tree tr = new tree(sz);
		tr.build(1, 0, sz - 1, v);
		int k = next();		
		
		for (int i = 0, x, y; i < k; ++i) {
			x = next();
			y = next();
			
			if (x > 0) {
				out.println(tr.gmax(1, 0, sz - 1, x - 1, y - 1) - tr.gmin(1, 0, sz - 1, x - 1, y - 1));
			} else {
				tr.upd(1, 0, sz - 1, -x - 1, y);
			}
		}
	}	
	
	int sz, a, b;
	int[] v;
}

class tree {
	int[] tmin, tmax;
	int sz;
	
	tree (int size) {
		sz = size;
		tmin = new int[4 * sz];
		tmax = new int[4 * sz];
	}
	
	void build(int v, int lt, int rt, int[] src) {
		if (lt == rt) 
			tmin[v] = tmax[v] = src[lt];
		else {
			int mt = (lt + rt) >>> 1;
			build(v + v, lt, mt, src);
			build(v + v + 1, mt + 1, rt, src);
			tmin[v] = min(tmin[v + v], tmin[v + v + 1]);
			tmax[v] = max(tmax[v + v], tmax[v + v + 1]);
		}
	}
	
	void upd(int v, int lt, int rt, int p, int nv) {
		if (lt == rt) 
			tmin[v] = tmax[v] = nv;
		else {
			int mt = (lt + rt) >>> 1;
			if (p <= mt)
				upd(v + v, lt, mt, p, nv);
			else
				upd(v + v + 1, mt + 1, rt, p, nv);
			tmin[v] = min(tmin[v + v], tmin[v + v + 1]);
			tmax[v] = max(tmax[v + v], tmax[v + v + 1]);
		}
	}
	
	int gmin(int v, int lt, int rt, int l, int r) {
		if (l == lt && r == rt)
			return tmin[v];
		else {
			int mt = (lt + rt) >>> 1;
			if (r <= mt) return gmin(v + v, lt, mt, l, r);
			if (mt + 1 <= l) return gmin(v + v + 1, mt + 1, rt, l, r);
			return min(gmin(v + v, lt, mt, l, mt), gmin(v + v + 1, mt + 1, rt, mt + 1, r));
		}
	}
	
	int gmax(int v, int lt, int rt, int l, int r) {
		if (l == lt && r == rt)
			return tmax[v];
		else {
			int mt = (lt + rt) >>> 1;
			if (r <= mt) return gmax(v + v, lt, mt, l, r);
			if (mt + 1 <= l) return gmax(v + v + 1, mt + 1, rt, l, r);
			return max(gmax(v + v, lt, mt, l, mt), gmax(v + v + 1, mt + 1, rt, mt + 1, r));
		}
	}
}
