
import static java.lang.Math.min;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		m = in.nextInt();
		
		c = new int[n][n];
		f = new int[n][n];
		
		for (int i = 0; i < n; ++i) {
			Arrays.fill(c[i], 0);
			Arrays.fill(f[i], 0);
		}
		
		for (int i = 0, f, s, cst; i < m; ++i) {
			f = in.nextInt() - 1;
			s = in.nextInt() - 1;
			cst = in.nextInt();
			c[f][s] = cst;
		}
		
		d = new int[n];
		ptr = new int[n];
		q = new int[n];
		
		flow = 0;
		s = 0;
		t = n - 1;
		
		while (true) {
			if (!bfs()) break;
			Arrays.fill(ptr, 0);
			while (true) {
				int push = dfs(s, inf);
				if (push == 0) break;
				flow += push;
			}
		}
		
		out.println(flow);
		out.close();
	}

	static int dfs(int v, int flw) {
		if (flw == 0) return 0;
		if (v == t) return flw;
		for (; ptr[v] < n; ++ptr[v]) {
			int to = ptr[v];
			if (d[to] != d[v] + 1) continue;
			int push = dfs(to, min(flw, c[v][to] - f[v][to]));
			if (push > 0) {
				f[v][to] += push;
				f[to][v] -= push;
				return push;
			}
		}
		return 0;
	}
	
	static int flow;
	static int inf = (int)1e8;
	
	static boolean bfs() {
		Arrays.fill(d, -1);
		int lt = 0, rt = 1;
		q[0] = s;
		d[s] = 0;
		while (lt < rt) {
			int v = q[lt++];
			for (int to = 0; to < n; ++to) 
				if (d[to] == -1 && f[v][to] < c[v][to]) {
					q[rt++] = to;
					d[to] = d[v] + 1;
				}			
		}
		return d[t] != -1;
	}
	
	static int[] d, ptr, q;
	static int n, m, s, t;
	static int[][] c, f;
}
