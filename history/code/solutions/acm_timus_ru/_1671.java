
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	PrintWriter out;
	StreamTokenizer in;
	
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
	
	int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	void solve() throws IOException {
		int n = next(), m = next();
		int[] f, s;
		f = new int[m];
		s = new int[m];
		for (int i = 0; i < m; ++i) {
			f[i] = next() - 1;
			s[i] = next() - 1;
		}
		
		boolean[] present = new boolean[m];
		Arrays.fill(present, true);
		
		int q = next();
		int[] d = new int[q];
		for (int i = 0; i < q; ++i) {
			d[i] = next() - 1;
			present[d[i]] = false;
		}
		
		int amt = n;
		dsu set = new dsu(n);
		
		for (int i = 0, a, b; i < m; ++i) {
			if (!present[i]) continue;
			a = set.get(f[i]);
			b = set.get(s[i]);
			if (a != b) {
				--amt;
				set.unite(a, b);
			}
		}
		
		int[] ans = new int[q];
		for (int i = q - 1, a, b; i >= 0; --i) {
			ans[i] = amt;
			a = set.get(f[d[i]]);
			b = set.get(s[d[i]]);
			if (a != b) {
				set.unite(a, b);
				--amt;
			}
		}
		
		for (int i = 0; i < q; ++i) 
			out.print(ans[i] + " ");
	}
}

class dsu {
	int[] par, size;
	
	dsu(int n) {
		par = new int[n];
		size = new int[n];
		for (int i = 0; i < n; ++i) {
			par[i] = i;
			size[i] = 1;
		}
	}
	
	void unite(int a, int b) {
		if (size[a] < size[b]) {
			int t = a;
			a = b;
			b = t;
		}
		par[b] = a;
		size[a] += size[b];
	}
	
	int get(int v) {
		return v == par[v] ? v : (par[v] = get(par[v]));
	}
}