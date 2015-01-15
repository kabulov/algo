import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		m = in.nextInt();
		
		if (n == 1 && m == 1) {
			out.println(in.next());
			out.close();
			return;
		}
		
		int[][] map = new int[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				map[i][j] = in.nextInt();
		
		class edge {
			int bgn, end, cst;
			edge(int a, int b, int c) {
				bgn = a;
				end = b;
				cst = c;
			}
		}
		
		edge[] v = new edge[4 * n * m];
		int len = 0, src, dst;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				src = i * m + j;
				if (good(i - 1, j)) {
					dst = (i - 1) * m + j;
					v[len] = new edge(src, dst, map[i - 1][j]);
					++len;
				}
				if (good(i + 1, j)) {
					dst = (i + 1) * m + j;
					v[len] = new edge(src, dst, map[i + 1][j]);
					++len;
				}
				if (good(i, j - 1)) {
					dst = i * m + j - 1;
					v[len] = new edge(src, dst, map[i][j - 1]);
					++len;
				}
				if (good(i, j + 1)) {
					dst = i * m + j + 1;
					v[len] = new edge(src, dst, map[i][j + 1]);
					++len;
				}
			}
				
		n *= m;
		int[] d = new int[n];
		Arrays.fill(d, inf);
		d[0] = map[0][0];
		
		for (int i = 1; i < n; ++i)
			for (int j = 0; j < len; ++j)
				if (d[v[j].bgn] < inf && d[v[j].bgn] + v[j].cst < d[v[j].end])
					d[v[j].end] = d[v[j].bgn] + v[j].cst;
		
		out.println(d[n - 1]);
		out.close();
	}
	
	static int inf = (int)1e6 + 1;
	
	static int n, m;
	static boolean good(int i, int j) {
		return 0 <= i && i < n && 0 <= j && j < m;
	}
}