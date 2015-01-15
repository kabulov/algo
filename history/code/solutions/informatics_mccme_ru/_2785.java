
import static java.lang.Math.min;
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
		
		map = new char[n + 2][m + 2];
		int[][] num = new int[n + 2][m + 2];
		int[] cap = new int[n * m + 1];
		Arrays.fill(cap, 0);
		
		for (int i = 0; i < n + 2; ++i)
			for (int j = 0; j < m + 2; ++j) {
				map[i][j] = '.';
				num[i][j] = -1;
			}	
		
		boolean good = false;		
		int sum = 0;
		String buf;
		nsz = 0;
		
		for (int i = 0; i < n; ++i) {
			buf = in.next();
			for (int j = 0; j < m; ++j) {
				map[i + 1][j + 1] = buf.charAt(j);
				if (buf.charAt(j) != '.') {
					num[i + 1][j + 1] = ++nsz;
					good = true;				
					
					switch(buf.charAt(j)) {
					case 'H':
						cap[nsz] = 1;
						sum += 1;
						break;
					case 'O':
						cap[nsz] = 2;
						sum += 2;
						break;
					case 'N':
						cap[nsz] = 3;
						sum += 3;
						break;
					case 'C':
						cap[nsz] = 4;
						sum += 4;
						break;
					}
				}
			}
		}
		
		//mojno eshe otsech sluchay kogda u napromer N < 3 sosedey
		
		if (!good) {
			out.println("Invalid");
			out.close();
			return;
		}
		
		esz = 0;
		g = new ArrayList[2 * nsz + 2];
		elist = new edge[2 * nsz + 2 * nsz + 2 * 4 * nsz];
		for (int i = 0; i < 2 * nsz + 2; ++i) g[i] = new ArrayList<Integer>();
		
		s = 0;
		t = 2 * nsz + 1;
		
		for (int i = 1; i <= nsz; ++i) {
			add(s, i, cap[i]);
			add(nsz + i, t, cap[i]);
		}
			
		for (int i = 1; i <= n; ++i) 
			for (int j = 1; j <= m; ++j) 
				if (num[i][j] != -1) { 
					int from = num[i][j];
					for (int k = 0, to; k < 4; ++k) {
						to = num[i + four[k][0]][j + four[k][1]];
						if (to == -1) continue;
						add(from, nsz + to, 1);						
					}
				}
				
		int flow = 0;		
		d = new int[2 * nsz + 2];
		ptr = new int[2 * nsz + 2];
		q = new int[2 * nsz + 2];
		
		while (true) {
			if (!bfs()) break;
			Arrays.fill(ptr, 0);
			while (true) {
				int push = dfs(s, inf);
				if (push == 0) break;
				flow += push;
			}
		}
		
		out.println(flow == sum ? "Valid" : "Invalid");
		out.close();
	}
	
	static int dfs(int v, int flow) {
		if (flow == 0) return 0;
		if (v == t) return flow;
		int id, to, push;
		for (; ptr[v] < g[v].size(); ++ptr[v]) {
			id = g[v].get(ptr[v]);
			to = elist[id].b;
			if (d[to] != d[v] + 1) continue;
			push = dfs(to, min(flow, elist[id].c - elist[id].f));
			if (push > 0) {
				elist[id].f += push;
				elist[id ^ 1].f -= push;
				return push;
			}
		}
		return 0;
	}
	
	static boolean bfs() {
		Arrays.fill(d, -1);
		d[s] = 0;
		q[0] = s;
		int lt = 0, rt = 1;
		int v, id, to;
		while (lt < rt) {
			v = q[lt++];
			for (int i = 0; i < g[v].size(); ++i) {
				id = g[v].get(i);
				to = elist[id].b;
				if (d[to] == -1 && elist[id].f < elist[id].c) {
					q[rt++] = to;
					d[to] = d[v] + 1;
				}
			}				
		}
		return d[t] != -1;
	}
	
	static int[] q, ptr, d;
	
	static int inf = (int)1e8;
	static int s, t;
	
	static void add(int a, int b, int cap) {
		g[a].add(esz);
		elist[esz++] = new edge(a, b, cap);
		g[b].add(esz);
		elist[esz++] = new edge(b, a, 0);
	}
	
	static int esz, nsz;
	static edge[] elist;
	static ArrayList<Integer> g[];
	
	static char[][] map;
	static int n, m;
	
	static int[][] four = {
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1}
	};
}

class edge {
	int a, b, f, c;
	edge (int fst, int scd, int cap) {
		a = fst;
		b = scd;
		f = 0;
		c = cap;
	}
}