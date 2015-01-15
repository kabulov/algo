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
		class edge {
			int bgn, end, w;
			edge(int a, int b, int c) {
				bgn = a;
				end = b;
				w = c;
			}
		}
		
		edge[] v = new edge[n * (n + 1)];
		int len = 0;
		
		int tmp;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if ((tmp = in.nextInt()) != 100000) {
					v[len] = new edge(i, j, tmp);
					++len;
				}
		
		int[] d = new int[n];
		Arrays.fill(d, 0);
		
		boolean have = false;
		for (int i = 0; i < n; i++) {
			have = false;
			for (int k = 0; k < len; ++k)
				if (d[v[k].bgn] + v[k].w < d[v[k].end]) {
					d[v[k].end] = Math.max(-inf, d[v[k].bgn] + v[k].w); 
					have = true;
				}
		}
		
		out.println(have ? "YES" : "NO");
		out.close();
	}
	
	static int inf = (int)1e7;
}