
import static java.lang.Math.min;
import static java.lang.Math.max;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	String FileName = "sparse";
	
	public void run() {
		try {
			in = new StreamTokenizer(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
//			in = new StreamTokenizer(new FileReader(FileName + ".in"));
//			out = new PrintWriter(FileName + ".out");
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
		m = next();
		s = next() - 1;
		
		g = new ArrayList[n];
		for (int i = 0; i < n; ++i) g[i] = new ArrayList<pair>();
		
		F = new int[m];
		T = new int[m];
		C = new int[m];
		
		for (int i = 0, a, b, c; i < m; ++i) {
			a = next() - 1;
			b = next() - 1;
			c = next();
			
			g[a].add(new pair(b, c));
			g[b].add(new pair(a, c));
			
			F[i] = a;
			T[i] = b;
			C[i] = c;
		}
		
		d = new int[n];
		Arrays.fill(d, inf);
		
		levit();		
		
		L = next();		
		long ans = 0;
		for (int i = 0; i < n; ++i)
			if (d[i] == L)
				++ans;
		
		for (int i = 0, a, b, c, i1, i2; i < m; ++i) {
			a = F[i];
			b = T[i];
			c = C[i];
			
			i1 = L - d[a];
			if (!(0 < i1 && i1 < c)) i1 = 0;
			if (L > d[b] + c - i1) i1 = 0;
			
			i2 = d[b] + c - L;
			if (!(0 < i2 && i2 < c)) i2 = 0;
			if (L > d[a] + i2) i2 = 0;
			
			if (i1 == 0 && i2 == 0) continue;
			if (i1 == 0 || i2 == 0) {
				++ans;
				continue;
			}
			
			if (i1 == i2)
				++ans;
			else
				ans += 2;
		}
		
		out.println(ans);	
	}	

	int[] F, T, C;
	
	void levit() {
		boolean[] f = new boolean[n];
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		Arrays.fill(f, false);
		f[s] = true;
		q.addLast(s);
		d[s] = 0;
		int v, to, cost;
		pair dest;
				
		while (q.size() > 0) {
			v = q.pollFirst();
			f[v] = false;
			for (int i = 0, sz = g[v].size(); i < sz; ++i) {
				dest = g[v].get(i);
				to = dest.v;
				cost = dest.c;
				
				if (d[v] + cost < d[to]) {
					d[to] = d[v] + cost;
					if (!f[to]) {
						f[to] = true;
						q.addLast(to);
					}
				}
			}
		}
	}
	
	int[] d;
	int inf = (int)1e9;
	ArrayList<pair> g[];
	int n, m, s, L;
}

class pair {
	int v, c;
	pair(int V, int C) {
		v = V;
		c = C;
	}
}