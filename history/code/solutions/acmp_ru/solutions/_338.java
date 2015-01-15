import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int m = in.nextInt();
		
		int[][] map = new int[n + 2][m + 2];
		for (int i = 0; i < n + 2; ++i)
			map[i][0] = map[i][m + 1] = 1;
		for (int j = 0; j < m + 2; ++j)
			map[0][j] = map[n + 1][j] = 1;
		
		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= m; ++j)
				map[i][j] = in.nextInt();
		
		int[] iv = new int[n * m];
		int[] jv = new int[n * m];
		int add, rem;
		
		int ans = 0;
		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= m; ++j)
				if (map[i][j] == 0) {
					++ans;
					map[i][j] = 1;
					rem = 0;
					add = 1;
					iv[0] = i;
					jv[0] = j;
					while (rem < add) {
						for (int p = 0; p < 4; ++p) {
							int ic = iv[rem] + way[p][0];
							int jc = jv[rem] + way[p][1];
							if (map[ic][jc] == 0) {
								map[ic][jc] = 1;
								iv[add] = ic;
								jv[add] = jc;
								++add;
							}
						}
						++rem;
					}
				}
		
		out.println(ans);
		out.close();
	}
	
	static int[][] way = {
		{0, 1},
		{0, -1},
		{1, 0},
		{-1, 0}
	};
}