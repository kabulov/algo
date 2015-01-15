import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 
		
		int n = in.nextInt();
		
		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; ++i) map[0][i] = 0;
		map[0][0] = 1;
		
		for (int i = 1; i <= n; ++i) 
			for (int j = 1; j <= i; ++j) {
				map[i][j] = 0;
				for (int k = 0; k <= i - j && k < j; ++k)
					map[i][j] += map[i - j][k];
			}
		
		for (int i = 2; i <= n; ++i) map[n][i] += map[n][i - 1];
		
		out.println(map[n][n]);
		out.close();
	}
}
