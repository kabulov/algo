import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		v = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = in.nextInt();
		
		map = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = -1;
		
		solve(0, n - 1);
		
		out.println(map[0][n - 1]);
		out.close();
	}
	
	static void solve(int l, int r) {
		if (r - l == 2) {
			map[l][r] = v[l] * v[l + 1] * v[r];
			return;
		}
		
		
		if (map[l + 1][r] == -1)
			solve(l + 1, r);
		if (map[l][r - 1] == -1)
			solve(l, r - 1);
		
		map[l][r] = Math.min(map[l + 1][r] + v[l] * v[l + 1] * v[r], map[l][r - 1] + v[l] * v[r - 1] * v[r]);
		
		for (int i = l + 2; i < r - 1; i++) {
			if (map[l][i] == -1)
				solve(l, i);
			if (map[i][r] == -1)
				solve(i, r);
			map[l][r] = Math.min(map[l][r], map[l][i] + map[i][r] + v[l] * v[i] * v[r]);
		}
	}
	
	static int[] v;
	static int[][] map;
	
}
