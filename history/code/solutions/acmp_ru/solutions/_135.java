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
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = in.nextInt();
		
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (map[i][k] + map[k][j] < map[i][j])
						map[i][j] = map[i][k] + map[k][j];
					
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				out.print(map[i][j]);
				out.print(" ");
			}
			out.println();
		}
		
		out.close();
	}
}