
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
	
	void solve() throws IOException {
		prepare();
		
		String str;
		int amt = next();
		for (int i = 0, a, b; i < amt; ++i) {
			in.nextToken();
			str = in.sval;
			a = next() - 1;
			b = next() - 1;
			if (str.equals("ADD")) {
				par[b] = a;
			} else {
//				System.out.println("halo");
				out.println(lca(a, b) + 1);
			}
		}
	}	
	
	int lca(int a, int b) {
//		System.out.println(a + " " + b);
		++curcol;
		
		col[a] = curcol;
		while (par[a] != -1) {
			a = par[a];
			col[a] = curcol;
		}
		
//		System.out.println(b + " " + par[b]);
		
		while (col[b] != curcol) {
			b = par[b];
		}
		
		return b;
	}
	
	int[] col;
	
	void prepare() {
		maxn = 1000;
		par = new int[maxn + 1];
		par[0] = -1;		
		col = new int[maxn + 1];
		curcol = 0;
	}
	
	int maxn, curcol;
	int[] par;
}
