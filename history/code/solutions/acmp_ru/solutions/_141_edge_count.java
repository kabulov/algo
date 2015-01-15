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
		map = new boolean[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = in.nextInt() == 1 ? true : false;
		
		was = new boolean[n];
		Arrays.fill(was, false);
		
		cnt = 0;
		dfs(0);		
		
		out.println((cnt + 1 == n && one()? "YES" : "NO"));
		out.close();
	}
	
	static boolean one() {
		for (int i = 0; i < n; i++)
			if (!was[i])
				return false;
		return true;
	}
	
	static boolean[] was;
	static boolean[][] map;
	static int cnt, n;
	
	static void dfs(int p) {
		was[p] = true;
		for (int i = 0; i < n; i++)
			if (map[p][i]) {
				++cnt;
				map[p][i] = map[i][p] = false;
				dfs(i);
			}
	}
}