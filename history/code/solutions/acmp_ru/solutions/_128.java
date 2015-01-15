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
		int i1, j1, i2, j2;
		j1 = in.nextInt() - 1;
		i1 = in.nextInt() - 1;
		j2 = in.nextInt() - 1;
		i2 = in.nextInt() - 1;
		
		if (i1 == i2 && j1 == j2) {
			out.println(0);
			out.close();
			return;
		}
		
		boolean[][] map = new boolean[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = false;
		
		map[i1][j1] = true;
		int[] iv = new int[n * n];
		int[] jv = new int[n * n];
		int[] len = new int[n * n];
		int add = 1, rem = 0;
		len[0] = 0;
		iv[0] = i1;
		jv[0] = j1;
		
		int ic, jc, i, j;
outer:	while (rem < add) {
			ic = iv[rem];
			jc = jv[rem];
			
			for (int k = 0; k < 8; k++) {
				i = ic + way[k][0];
				j = jc + way[k][1];
				if (in(i, j) && !map[i][j]) {
					len[add] = len[rem] + 1;
					if (i == i2 && j == j2) break outer;
					iv[add] = i;
					jv[add] = j;
					map[i][j] = true;
					++add;
				}
			}
			++rem;
		}
		
		out.println(len[add]);
		out.close();
	}
	
	static int[][] way = {
		{1, 2},
		{1, -2},
		{2, 1},
		{2, -1},
		{-1, 2},
		{-1, -2},
		{-2, 1},
		{-2, -1}
	};
	
	static boolean in(int i, int j) {
		return 0 <= i && i < n && 0 <= j && j < n;
	}
	
	static int n;
}