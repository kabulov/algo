import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		int[] v = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = in.nextInt();
		
		int sol = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (map[i][j] == 1 && v[i] != v[j])
					++sol;					
		
		out.println(sol);
		out.close();
	}
}