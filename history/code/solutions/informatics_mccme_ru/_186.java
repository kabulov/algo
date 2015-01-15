
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

		m = in.nextInt();
		n = in.nextInt();
		
		row = new int[m];
		col = new int[n];
		
		for (int i = 0; i < m; ++i) row[i] = in.nextInt();
		for (int i = 0; i < n; ++i) col[i] = in.nextInt();
		
		map = new int[m][n];
		for (int i = 0; i < m; ++i)
			for (int j = 0; j < n; ++j)
				map[i][j] = in.nextInt();
		
		boolean good = true;
		
		for (int i = 0, s; i < m; ++i) {
			s = 0;
			for (int j = 0; j < n; ++j)
				if (map[i][j] != -1)
					s += map[i][j];
			if (s > row[i]) good = false;
		}
		
		for (int j = 0, s; j < n; ++j) {
			s = 0;
			for (int i = 0; i < m; ++i)
				if (map[i][j] != -1)
					s += map[i][j];
			if (s > col[j]) good = false;			
		}
		
		if (!good) {
			out.println(-1);
			out.close();
			return;
		}
		
		sz = 1 + m + n + 1;
		c = new int[sz][sz];
		f = new int[sz][sz];
		
		for (int i = 0; i < sz; ++i) {
			Arrays.fill(c[i], 0);
			Arrays.fill(f[i], 0);
		}
		
		s = 0;
		t = sz - 1;
		
		for (int i = 0; i < m; ++i)
			c[s][i + 1] = row[i];
		
		for (int i = 0; i < n; ++i)
			c[m + 1 + i][t] = col[i];
		
		for (int i = 0; i < m; ++i) 		
			for (int j = 0; j < n; ++j) 
				c[i + 1][m + j + 1] = min(row[i], col[j]);	
		
		for (int i = 0; i < m; ++i)
			for (int j = 0; j < n; ++j)
				if (map[i][j] != -1) {
					c[i + 1][m + j + 1] = 0;
					c[s][i + 1] -= map[i][j];
					c[m + 1 + j][t] -= map[i][j];
				}
		
		d = new int[sz];
		q = new int[sz];
		ptr = new int[sz];
		
		while (true) {
			if (!bfs()) break;
			Arrays.fill(ptr, 0);
			while (true) {
				int push = dfs(s, inf);
				if (push == 0) break;
			}
		}
		
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) 
				out.print((map[i][j] == -1 ? f[i + 1][m + j + 1] : map[i][j]) + " ");
			out.println();
		}
		
		out.close();
	}
	
	static int inf = (int)1e8;
	
	static int dfs(int v, int flow) {
		if (flow == 0) return 0;
		if (v == t) return flow;
		for (int to; ptr[v] < sz; ++ptr[v]) {
			to = ptr[v];
			if (d[to] != d[v] + 1) continue;
			int push = dfs(to, min(flow, c[v][to] - f[v][to]));
			if (push > 0) {
				f[v][to] += push;
				f[to][v] -= push;
				return push;
			}
		}
		return 0;
	}
	
	static boolean bfs() {
		Arrays.fill(d, -1);
		d[s] = 0;
		q[0] = s;
		lt = 0;
		rt = 1;
		int v, to;
		while (lt < rt) {
			v = q[lt++];
			for (to = 0; to < sz; ++to) {
				if (d[to] == -1 && f[v][to] < c[v][to]) {
					q[rt++] = to;
					d[to] = d[v] + 1;
				}
			}
		}
		return d[t] != -1;
	}
	
	static int rt, lt;
	static int[] q, d, ptr;
	
	static int sz, s, t;
	static int[][] c, f;
	
	static int m, n;
	static int[] row, col;
	static int[][] map;
}
