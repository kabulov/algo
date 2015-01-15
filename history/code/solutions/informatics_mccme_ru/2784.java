
import static java.lang.Math.min;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main implements Runnable {
	public static void main(String[] args) throws IOException {
		new Thread(new Main()).start();
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new StreamTokenizer(new FileReader("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
		} catch (IOException e) {
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
	
	public void solve() throws IOException {
		n = next();
		m = next();
		
		elist = new edge[2 * m];
		g = new ArrayList[n];
		for (int i = 0; i < n; ++i) g[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < m; ++i) {
			add(next() - 1, next() - 1, next());
		}
		
		s = 0;
		t = n - 1;
		q = new int[n];
		d = new int[n];
		ptr = new int[n];
		long flow = 0, push;
				
		while (true) {
			if (!bfs()) break;
			Arrays.fill(ptr, 0);
			while (true) {
				push = dfs(s, inf);
				if (push == 0) break;
				flow += push;
			}
		}
		out.println(flow);
	}
	
	int dfs(int v, int flow) {
		if (flow == 0) return 0;
		if (v == t) return flow;
		int id, to, push;
		for (; ptr[v] < g[v].size(); ++ptr[v]) {
			id = g[v].get(ptr[v]);
			to = elist[id].b;
			if (d[to] != d[v] + 1) continue;
			push = dfs(to, min(flow, elist[id].c - elist[id].f));
			if (push > 0) {
				elist[id].f += push;
				elist[id ^ 1].f -= push;
				return push;
			}
		}
		return 0;
	}
	
	boolean bfs() {
		Arrays.fill(d, -1);
		d[s] = 0;
		q[0] = s;
		int lt = 0, rt = 1;
		int v, id, to;
		while (lt < rt) {
			v = q[lt++];
			for (int i = 0; i < g[v].size(); ++i) {
				id = g[v].get(i);
				to = elist[id].b;
				if (d[to] == -1 && elist[id].f < elist[id].c) {
					q[rt++] = to;
					d[to] = d[v] + 1;
				}
			}
		}
		return d[t] != -1;
	}
	
	int[] ptr, q, d;
	
	void add(int a, int b, int cap) {
		g[a].add(sz);
		elist[sz++] = new edge(a, b, cap);
		g[b].add(sz);
		elist[sz++] = new edge(b, a, 0);
	}
	
	edge[] elist;
	ArrayList<Integer> g[];
	
	int inf = (int)1e9;
	int n, m, s, t, sz;
}

class edge {
	int a, b, f, c;
	edge (int fst, int scd, int cap) {
		a = fst;
		b = scd;
		f = 0;
		c = cap;
	}
}