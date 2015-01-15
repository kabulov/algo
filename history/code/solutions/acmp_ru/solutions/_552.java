import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = in.nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = in.nextInt();
		
		if (n < 3)
			out.println(0);
		else {
			long[][] map = new long[3][n];
			
			map[0][n - 1] = v[n - 1];
			for (int i = n - 2; i > 1; --i) map[0][i] = v[i] + map[0][i + 1];
			
			map[1][n - 1] = 0;
			for (int i = n - 2; i > 0; --i) map[1][i] = map[1][i + 1] + v[i] * map[0][i + 1];
			
			map[2][n - 2] = 0;
			for (int i = n - 3; i >= 0; --i) map[2][i] = map[2][i + 1] + v[i] * map[1][i + 1];
			
			out.println(map[2][0]);
		}
		
		out.close();
	}
}
