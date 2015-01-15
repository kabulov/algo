import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		m = in.nextInt();
		
		bi = in.nextInt() - 1;
		bj = in.nextInt() - 1;
		
		inp = new int[n][m];
		way = new int[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				inp[i][j] = in.nextInt();
				way[i][j] = -1;
			}
				
		hypn = in.nextInt();
		hypi = new int[hypn];
		hypj = new int[hypn];
		
		int ti, tj;//, a, b;
		for (int i = 0; i < hypn; ++i) {
			ti = in.nextInt() - 1;
			tj = in.nextInt() - 1;
			hypi[i] = in.nextInt() - 1;
			hypj[i] = in.nextInt() - 1;
			way[ti][tj] = i;
		}
		
		exitn = in.nextInt();
		for (int i = 0; i < exitn; ++i) {
			ti = in.nextInt() - 1;
			tj = in.nextInt() - 1;
			way[ti][tj] = exit;
		}
			
		map = new int[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				map[i][j] = inf; 	//inp[i][j] == 1 ? -1 : inf
		
		map[bi][bj] = 1;
		vi = new int[n * m];
		vj = new int[n * m];
		vi[0] = bi;
		vj[0] = bj;
		add = 1;
		rem = 0;
		
		stop = false;
		while (rem < add) {
			ti = vi[rem];
			tj = vj[rem];
			offer(ti - 1, tj, map[ti][tj]);
			if (stop) break;
			offer(ti + 1, tj, map[ti][tj]);
			if (stop) break;
			offer(ti, tj - 1, map[ti][tj]);
			if (stop) break;
			offer(ti, tj + 1, map[ti][tj]);
			if (stop) break;
			if (way[ti][tj] >= 0) {
				int pos = way[ti][tj];
				int len = map[ti][tj];
				ti = hypi[pos];
				tj = hypj[pos];
				offer(ti, tj, len);
				if (stop) break;
			}
			++rem;
		}
		
		out.println((!stop) ? "Impossible" : map[vi[add]][vj[add]]);
		out.close();
	}
	
	static boolean stop;
	static void offer(int i, int j, int len) {
		if (!good(i, j)) return;
		map[i][j] = len + 1;
		vi[add] = i;
		vj[add] = j;
		if (way[i][j] == exit) {
			stop = true;
			return;
		}
		++add;
	}

	static boolean good(int i, int j) {
		return 0 <= i && i < n && 0 <= j && j < m && inp[i][j] == 0 && map[i][j] == inf;			//
	}
	
	static int[][] map;
	static int[] vi, vj;
	static int add, rem;
	static int inf = (int)1e7;
	
	static int exit = (int)1e6;
	static int bi, bj;
	static int n, m;
	static int[][] inp;
	static int[][] way;
	static int hypi[], hypj[];
	static int hypn, exitn;
	
}