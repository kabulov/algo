
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	String FileName = "lca";
	
	public void run() {
		try {
//			in = new StreamTokenizer(new InputStreamReader(System.in));
//			out = new PrintWriter(System.out);
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
	
	void solve() throws Exception {
		g = new ArrayList[maxn + 1];
		for (int i = 0; i <= maxn; ++i) g[i] = new ArrayList<Integer>();
		
		q = new ArrayList[maxn + 1];
		for (int i = 0; i <= maxn; ++i) q[i] = new ArrayList<Integer>();

		ord = new ArrayList[maxn + 1];
		for (int i = 0; i <= maxn; ++i) ord[i] = new ArrayList<Integer>();

		n = 1;
		m = 0;
		int k = next();
		
		String str;
		for (int i = 0, a, b; i < k; ++i) {
			in.nextToken();
			str = in.sval;
			a = next() - 1;
			b = next() - 1;
			if (str.equals("ADD")) {
				g[a].add(b);
				++n;				
			} else {
				q[a].add(b);
				q[b].add(a);
				ord[a].add(m);
				ord[b].add(m);
				++m;
			}
		}
		
		qans = new int[m];
		
		used = new boolean[n];
		Arrays.fill(used, false);
		
		dsu = new int[n];
		ancs = new int[n];
		
//		//
//		for (int i = 0; i < n; ++i) {
//			dsu[i] = ancs[i] = i;
//		}
		
		rand = new Random();
		dfs(0);
		
		for (int i = 0; i < m; ++i)
			out.println(qans[i] + 1);
	}	
	
	void dfs(int v) {
		dsu[v] = v;
		ancs[v] = v;
		used[v] = true;
		
		for (int i = 0, sz = g[v].size(), to; i < sz; ++i) {
			to = g[v].get(i);
			 dfs(to);
			unite(v, to, v);
		}
		
		for (int i = 0, sz = q[v].size(), to; i < sz; ++i) {
			to = q[v].get(i);
			if (used[to]) {
				qans[ord[v].get(i)] = ancs[get(to)];
			}
		}
	}
	
	int get(int v) {
		return dsu[v] == v ? v : (dsu[v] = get(dsu[v]));
	}
	
	void unite(int a, int b, int parent) {
		a = get(a);
		b = get(b);
		if (rand.nextBoolean()) {
			int t = a;
			a = b;
			b = t;
		}
		dsu[a] = b;
		ancs[b] = parent;
	}
	
	Random rand;
	
	int[] dsu, ancs;
	boolean[] used;
	
	int n, m;
	int maxn = 500000;
	int[] qans;
	
	ArrayList<Integer> q[], g[], ord[];
}
