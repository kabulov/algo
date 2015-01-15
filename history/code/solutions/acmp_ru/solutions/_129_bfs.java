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
		int m = in.nextInt();
		
		map = new int[n + 2][m + 2];
		for (int i = 0; i <= n + 1; i++) map[i][0] = map[i][m + 1] = 1000;
		for (int j = 0; j <= m + 1; j++) map[0][j] = map[n + 1][j] = 1000;
		
		iv = new int[n * m];
		jv = new int[n * m];
		add = rem = 0;

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++) {
				map[i][j] = in.nextInt() - 1;
				if (map[i][j] == 0) {
					iv[add] = i;
					jv[add] = j;
					++add;
				}
			}
			
		int i, j;
		while (rem < add) {
			i = iv[rem];
			j = jv[rem];
			++rem;
			
			len = map[i][j];
			
			offer(i + 1, j);
			offer(i - 1, j);
			offer(i, j + 1);
			offer(i, j - 1);
		}
		
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) out.print(map[i][j] + " ");
			out.println();
		}
		
		out.close();
	}
	
	static int[][] map;
	static int[] iv, jv;
	static int add, rem, len;
	
	static void offer(int i, int j) {
		if (map[i][j] != -1) return;
		map[i][j] = len + 1;
		iv[add] = i;
		jv[add] = j;
		++add;
	}
}