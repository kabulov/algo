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
		int[] cost = new int[n];
		for (int i = 0; i < n; ++i)
			cost[i] = in.nextInt();
		
		class edge {
			int bgn, end, cst;
			edge(int a, int b, int c) {
				bgn = a;
				end = b;
				cst = c;
			}
		}
		
		int m = in.nextInt();
		edge[] e = new edge[2 * m];
		for (int i = 0; i < m; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			e[i] = new edge(a, b, cost[a]);
			e[i + m] = new edge(b, a, cost[b]);
		}
		
		int[] d = new int[n];
		Arrays.fill(d, inf);
		d[0] = 0;
		
		m *= 2;
		int dst;
		for (int i = 1; i < n; ++i) 
			for (int j = 0; j < m; ++j) 
				if (d[e[j].bgn] < inf) {
					dst = d[e[j].bgn] + e[j].cst;
					if (dst < d[e[j].end]) d[e[j].end] = dst;
				}		
		
		out.println(d[n - 1] == inf ? -1 : d[n - 1]);
		out.close();
	}
	
	static int inf = (int)1e6 + 1;
}