import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt(), m = in.nextInt();
		
		int[][] inp = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				inp[i][j] = in.nextInt();
		
		int[][] map = new int[n + 2][m + 2];

		for (int i = 0; i < n + 2; i++)
			for (int j = 0; j < m + 2; j++)
				map[i][j] = 1000;
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				map[i][j] = inp[i - 1][j - 1] == 1 ? 0 : Math.min(map[i - 1][j], map[i][j - 1]) + 1;
		
		for (int i = 1; i <= n; i++)
			for (int j = m; j > 0; --j)
				map[i][j] = Math.min(map[i][j], Math.min(map[i - 1][j], map[i][j + 1]) + 1);
		
		for (int i = n; i > 0; --i)
			for (int j = 1; j <= m; j++)
				map[i][j] = Math.min(map[i][j], Math.min(map[i + 1][j], map[i][j - 1]) + 1);
		
		for (int i = n; i > 0; --i)
			for (int j = m; j > 0; --j)
				map[i][j] = Math.min(map[i][j], Math.min(map[i + 1][j], map[i][j + 1]) + 1);
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) out.print(map[i][j] + " ");
			out.println();
		}
		
		out.close();
	}
}