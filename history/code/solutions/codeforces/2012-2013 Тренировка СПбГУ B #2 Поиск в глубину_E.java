
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
	
	String FileName = "firesafe";
	
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
		m = next();
		
		sz = 0;
		v = new edge[m];
		for (int i = 0, f, s; i < m; ++i) {
			f = next() - 1;
			s = next() - 1;
			if (f == s) continue;
			v[sz++] = new edge(f, s);
		}
		
		m = sz;
		Arrays.sort(v, 0, m);		
		sz = 1;
		for (int i = 1; i < m; ++i) 
			if (!v[i].equal(v[i - 1])) 
				v[sz++] = v[i];			
		m = sz;

		g = new ArrayList[n];
		for (int i = 0; i < n; ++i) g[i] = new ArrayList<Integer>();
		gt = new ArrayList[n];
		for (int i = 0; i < n; ++i) gt[i] = new ArrayList<Integer>();
		
		for (int i = 0, f, s; i < m; ++i) {
			f = v[i].f;
			s = v[i].s;
			g[f].add(s);
			gt[s].add(f);
		}
		
		used = new boolean[n];
		Arrays.fill(used, false);
		
		sz = 0;
		order = new int[n];
		
		for (int i = 0; i < n; ++i) 
			if (!used[i])
				tsort(i);
				
		col = new int[n];
		rep = new int[n];
		Arrays.fill(col, -1);
		
		sz = 0;
		
		for (int i = 0, v; i < n; ++i) {
			v = order[n - 1 - i];
			if (col[v] == -1) {
				dfs(v, sz);
				rep[sz++] = v;
			}
		}		
		
		good = new boolean[sz];
		Arrays.fill(good, true);
		Arrays.fill(used, false);
		
		for (int i = 0; i < n; ++i)
			if (!used[i])
				last(i);
		
		int amt = 0;
		for (int i = 0; i < sz; ++i)
			if (good[i])
				++amt;
		
		out.println(amt);
		for (int i = 0; i < sz; ++i)
			if (good[i]) {
				out.print((rep[i] + 1) + " ");
			}
	}
	
	void last(int v) {
		used[v] = true;
		for (int i = 0, sz = g[v].size(), to; i < sz; ++i) {
			to = g[v].get(i);
			if (col[v] != col[to]) 
				good[col[v]] = false;
			if (!used[to])
				last(to);
		}
	}
	
	void dfs(int v, int c) {
		col[v] = c;
		for (int i = 0, sz = gt[v].size(), to; i < sz; ++i) {
			to = gt[v].get(i);
			if (col[to] == -1) 
				dfs(to, c);			
		}
	}
	
	int[] col, rep;
	boolean[] good;
	
	void tsort(int v) {
		used[v] = true;
		for (int i = 0, sz = g[v].size(), to; i < sz; ++i) {
			to = g[v].get(i);
			if (!used[to])
				tsort(to);
		}
		order[sz++] = v;
	}
	
	int[] order;
	boolean[] used;
	
	ArrayList<Integer> gt[];
	ArrayList<Integer> g[];
	
	edge[] v;
	int n, m, sz;
}

class edge implements Comparable <edge> {
	int f, s;
	
	edge(int fst, int scd) {
		f = fst;
		s = scd;
	}

	public int compareTo(edge arg) {
		if (f == arg.f) return s - arg.s; 
		return f - arg.f;
	}
	
	public boolean equal(edge arg) {
		return f == arg.f && s == arg.s;
	}
}