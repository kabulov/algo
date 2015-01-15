import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int node = in.nextInt();
		int m = in.nextInt();
		
		int len = 0;
		edge[] ed = new edge[n * m];
		for (int i = 0; i < m; i++) {
			int amt = in.nextInt();
			int b, e, tb, te;
			
			e = in.nextInt();
			te = in.nextInt();
			for (int j = 1; j < amt; j++) {
				b = e;
				tb = te;
				e = in.nextInt();
				te = in.nextInt();
				ed[len++] = new edge(b, e, tb, te);
			}
		}
		
		int[] d = new int[n + 1];
		Arrays.fill(d, inf);
		d[1] = 0;
		
		for (int i = 1; i < n; ++i) {
//			boolean done = false;
			for (int j = 0; j < len; ++j)
				if (d[ed[j].b] < inf && d[ed[j].b] <= ed[j].tb && ed[j].te < d[ed[j].e]) {
					d[ed[j].e] = ed[j].te;
//					done = true;
				}
//			if (!done) break;		
		}
		
		out.println(d[node] == inf ? -1 : d[node]);
		out.close();
	}
	
	static int inf = (int)1e9 + 1;
}

class edge {
	int b, e, tb, te;
	edge(int bb, int ee, int tbb, int tee) {
		b = bb;
		e = ee;
		tb = tbb;
		te = tee;
	}
}
