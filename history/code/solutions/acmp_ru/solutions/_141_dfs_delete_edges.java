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
			for (int j = 0; j < n; ++j)
				map[i][j] = in.nextInt() == 1 ? true : false;
		
		col = new int[n];
		Arrays.fill(col, -1);
		
		cyc = false;
		dfs(0);
		
		out.println((one() && !cyc ? "YES" : "NO"));
		out.close();
	}
	
	static boolean one() {
		for (int i = 0; i < n; i++) 
			if (col[i] < 1) 
				return false;
		return true;
	}
	
	static int n;
	static int[] col;
	static boolean cyc;
	static boolean[][] map;
	
	static void dfs(int p) {
		col[p] = 0;
		for (int i = 0; i < n; ++i)
			if (map[p][i]) 
				if (col[i] == -1) {
					map[p][i] = false;
					map[i][p] = false;
					dfs(i);
				} else
				if (col[i] == 0)
					cyc = true;
		col[p] = 1;
	}
}