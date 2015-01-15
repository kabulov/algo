import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int x = in.nextInt();
		int m = in.nextInt();
		int l = in.nextInt();
		int v = in.nextInt();
		
		int[] rem = new int[l];
		rem[0] = 1 % m;
		for (int i = 1; i < l; ++i) rem[i] = (rem[i - 1] * x) % m;
		
		int[][] vect = new int[l][10];
		for (int i = 0; i < l; ++i)
			for (int j = 0; j < 10; ++j)
				vect[i][j] = (rem[i] * j) % m;
		
		boolean[][] map = new boolean[l][m];
		for (int i = 0; i < l; ++i)
			Arrays.fill(map[i], false);
			
		for (int i = 0; i < 10; ++i) 
			map[l - 1][vect[l - 1][i]] = true;
		
		for (int i = l - 2; i >= 0; --i) {
			for (int j = 0; j < m; ++j)
				if (map[i + 1][j]) {
					for (int k = 0; k < 10; ++k)
						map[i][(j + vect[i][k]) % m] = true;
				}
		}
		
		if (!map[0][v])
			out.println("NO SOLUTION");
		else {
			for (int i = 0; i < l - 1; ++i) 
outer:			for (int k = 0; k < 10; ++k)
					for (int j = 0; j < m; ++j)
						if (map[i + 1][j] && (j + vect[i][k]) % m == v ) {
							out.print(k);
							v = j;
							break outer;
						}
				
			for (int i = 0; i < 10; ++i)
				if (vect[l - 1][i] == v) {
					out.print(i);
					break;
				}
		}
		
		out.close();
	}
}
