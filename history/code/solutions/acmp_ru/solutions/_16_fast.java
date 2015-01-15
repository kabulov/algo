import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 
		
		int n = in.nextInt();
		
		int[][] map = new int[n + 1][n + 1];
		
		for (int i = 0; i <= n; ++i) map[i][0] = 1;
		
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				map[j][i] = map[j - 1][i];
				if (j <= i) map[j][i] += map[j - 1][i - j];				
			}
		}
		
		out.println(map[n][n]);
		out.close();
	}
}
