
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
		n = next();
		par = new int[n];
		size = new int[n];
		for (int i = 0; i < n; ++i) {
			par[i] = i;
			size[i] = 1;
		}
		
		int[] fst, scd;
		fst = new int[n];
		scd = new int[n];
		int len = 0;
		
		for (int i = 0, a, b, f, s; i + 1 < n; ++i) {
			a = get(f = (next() - 1));
			b = get(s = (next() - 1));
			if (a == b) {
				fst[len] = f;
				scd[len] = s;
				++len;
				continue;
			}
			unite(a, b);
		}
		
		out.println(len);
		
		boolean[] h = new boolean[n];
		Arrays.fill(h, false);
		for (int i = 0; i < n; ++i)
			h[get(i)] = true;
		
		int cur = 0;
		while (!h[cur]) ++cur;
		
		for (int i = 0, prev; i < len; ++i) {
			prev = cur;
			for (cur = cur + 1; !h[cur]; ++cur);
			out.println((fst[i] + 1) + " " + (scd[i] + 1) + " " + (prev + 1) + " " + (cur + 1));		
		}
	}
	
	int n;
	int[] par, size;
	
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
