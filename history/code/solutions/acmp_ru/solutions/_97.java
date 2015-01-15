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
		rect[] v = new rect[n];
		for (int i = 0; i < n; ++i)
			v[i] = new rect(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		
		map = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			map[i][i] = true;
			for (int j = i + 1; j < n; ++j)
				map[i][j] = map[j][i] = isect(v[i], v[j]);
		}
		
		was = new boolean[n];
		Arrays.fill(was, false);
		
		int ans = 0;
		for (int i = 0; i < n; ++i)
			if (!was[i]) {
				bfs(i);
				++ans;
			}
			
		out.println(ans);
		out.close();
	}
	
	static boolean[][] map;
	static boolean[] was;
	static int n;
	
	static void bfs(int v) {
		was[v] = true;
		for (int i = 0; i < n; ++i)
			if (map[v][i] && !was[i])
				bfs(i);
	}
	
	static boolean isect(rect fst, rect scd) {
		if (scd.rux < fst.ldx) return false;
		if (fst.rux < scd.ldx) return false;
		if (fst.ruy < scd.ldy) return false;
		if (scd.ruy < fst.ldy) return false;
		
		return true;
	}
}

class rect {
	int ldx, ldy, rux, ruy;
	
	rect(int a, int b, int c, int d, int r) {
		ldx = a;
		ldy = b;
		rux = c;
		ruy = d;
		
		if (ldx > rux) {
			int tmp = ldx;
			ldx = rux;
			rux = tmp;
		}
		
		if (ldy > ruy) {
			int tmp = ldy;
			ldy = ruy;
			ruy = tmp;
		}
		
		ldx -= r;
		ldy -= r;
		rux += r;
		ruy += r;
	}
}
