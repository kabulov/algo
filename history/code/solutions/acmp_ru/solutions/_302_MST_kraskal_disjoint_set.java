import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		if (n == 1) {
			out.println("0.00");
			out.close();
			return;
		}
		
		double[] x, y;
		x = new double[n];
		y = new double[n];
		
		for (int i = 0; i < n; ++i) {
			x[i] = in.nextDouble();
			y[i] = in.nextDouble();
		}
		
		from = new short[n * (n - 1) / 2];
		to = new short[n * (n - 1) / 2];
		cst = new double[n * (n - 1) / 2];
		
		int len = 0;
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j < n; ++j) {
				from[len] = (short)i;
				to[len] = (short)j;
				cst[len] = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
				++len;
			}
				
		rand = new Random();
		sort(0, len - 1);
		
		double ans = -1;
		int amt = 0;
		
		Set st = new Set(n);
		
		for (int p = 0; p < len && amt < n - 1; ++p) 
			if (st.get(from[p]) != st.get(to[p])) {
				st.unite(from[p], to[p]);
				if (cst[p] > ans) ans = cst[p];
				++amt;
			}
		
		out.printf("%.2f", Math.sqrt(ans));
		out.close();
	}
	
	static short[] from, to;
	static double[] cst;
	
	static short buf;
	static Random rand;
	static double tmp, mid;

	static void sort(int l, int r) {
		int i = l, j = r;
		mid = cst[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; cst[i] < mid; ++i);
			for (; mid < cst[j]; --j);
			if (i <= j) {
				tmp = cst[i];
				cst[i] = cst[j];
				cst[j] = tmp;
				
				buf = from[i];
				from[i] = from[j];
				from[j] = buf;
				
				buf = to[i];
				to[i] = to[j];
				to[j] = buf;
				
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
}

class Set {
	short[] rep;
	short[] rank;
	
	Set(int n) {
		rep = new short[n];
		for (int i = 0; i < n; ++i)
			rep[i] = (short)i;
		
		rank = new short[n];
		Arrays.fill(rank, (short)0);
	}
	
	short get(short x) {
		return x == rep[x] ? x : (rep[x] = get(rep[x]));
	}
	
	void unite(short a, short b) {
		a = get(a);
		b = get(b);
		
		if (rank[a] < rank[b])
			rep[a] = b;
		else {
			if (rank[a] == rank[b]) ++rank[a];
			rep[b] = a;
		}
	}
}
