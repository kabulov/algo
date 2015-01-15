
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Random;

public class Solution  { //implements Runnable
	public static void main(String[] argv) throws IOException {
//		new Thread(new Solution()).start();
//		in = new StreamTokenizer(new FileReader("in.txt"));
//		out = new PrintWriter("out.txt");
		in = new StreamTokenizer(new FileReader(FileName + ".in"));
		out = new PrintWriter(FileName + ".out");
		solve();
		out.close();
	}
	
	static StreamTokenizer in;
	static PrintWriter out;
	
	static String FileName = "unionday";
	
//	public void run() {
//		try {
//			in = new StreamTokenizer(new InputStreamReader(System.in));
//			out = new PrintWriter(System.out);
//			in = new StreamTokenizer(new FileReader(FileName + ".in"));
//			out = new PrintWriter(FileName + ".out");
//			in = new StreamTokenizer(new FileReader("in.txt"));
//			out = new PrintWriter("out.txt");
//			solve();			
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(1);
//		} finally {
//			out.close();
//		}
//	}
	
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static void solve() throws IOException {
		int n = next();
		x = new int[n];
		y = new int[n];
		elist = new edge[n * n / 2];
		
		for (int i = 0; i < n; ++i) {
			x[i] = next();
			y[i] = next();
		}
		
		size = 0;
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j < n; ++j)
				elist[size++] = new edge(i, j, (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
		
		Arrays.sort(elist, 0, size);
		par = new int[n];
		for (int i = 0; i < n; ++i) par[i] = i;
		rand = new Random();
		
		double ans = 0;

		edge tmp;
		for (int i = 0, a, b; i < size; ++i) {
			tmp = elist[i];
			a = get(tmp.a);
			b = get(tmp.b);
			if (a != b) {
				unite(a, b);
				ans += Math.sqrt(tmp.c);
			}
		}
		
		out.println(ans);
	}
	
	static int get(int v) {
		return v == par[v] ? v : (par[v] = get(par[v]));
	}
	
	static void unite(int a, int b) {
		if (rand.nextBoolean()) {
			int t = a;
			a = b;
			b = t;
		}
		par[a] = b;
	}
	
	static Random rand;
	static int[] par;
	
	static int size;
	static edge[] elist;
	static int[] x, y;
	
}

class edge implements Comparable<edge>{
	int a, b, c;
	edge(int A, int B, int C) {
		a = A;
		b = B;
		c = C;
	}

	public int compareTo(edge arg) {		
		return c - arg.c;
	}
	
}