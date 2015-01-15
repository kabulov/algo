import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		k = in.nextInt();
		n = in.nextInt();
		m = in.nextInt();
		
		inp = new int[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				inp[i][j] = in.nextInt();
				if (inp[i][j] == 2) {
					bi = i;
					bj = j;
				}
			}
		
		map = new int[n][m][k + 1][4];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				for (int p = 0; p <= k; ++p)
					for (int c = 0; c < 4; ++c)
						map[i][j][p][c] = inf;
		
		for (int c = 0; c < 4; ++c)			//important
			map[bi][bj][0][c] = 0;			//important
		
		if (good(bi - 1, bj)) 
			map[bi - 1][bj][0][2] = 1;
		
		if (good(bi + 1, bj)) 
			map[bi + 1][bj][0][0] = 1;
		
		if (good(bi, bj - 1)) 
			map[bi][bj - 1][0][1] = 1;
		
		if (good(bi, bj + 1)) 
			map[bi][bj + 1][0][3] = 1;
		
		change = true;
		while (change) {
			change = false;
			for (int p = 0; p <= k; ++p)
				for (int i = 0; i < n; ++i)
					for (int j = 0; j < m; ++j)
						if (inp[i][j] != 1) {
							if (good(i - 1, j)) {
								if (map[i - 1][j][p][0] != inf && map[i - 1][j][p][0] + 1 < map[i][j][p][0]) {
									map[i][j][p][0] = map[i - 1][j][p][0] + 1;
									change = true;
								}
								if (map[i - 1][j][p][1] != inf && map[i - 1][j][p][1] + 1 < map[i][j][p][0]) {
									map[i][j][p][0] = map[i - 1][j][p][1] + 1;
									change = true;
								}
								if (p > 0 && map[i - 1][j][p - 1][3] != inf && map[i - 1][j][p - 1][3] + 1 < map[i][j][p][0]) {
									map[i][j][p][0] = map[i - 1][j][p - 1][3] + 1;
									change = true;
								}
							}
							if (good(i + 1, j)) {
								if (map[i + 1][j][p][2] != inf && map[i + 1][j][p][2] + 1 < map[i][j][p][2]) {
									map[i][j][p][2] = map[i + 1][j][p][2] + 1;
									change = true;
								}
								if (map[i + 1][j][p][3] != inf && map[i + 1][j][p][3] + 1 < map[i][j][p][2]) {
									map[i][j][p][2] = map[i + 1][j][p][3] + 1;
									change = true;
								}
								if (p > 0 && map[i + 1][j][p - 1][1] != inf && map[i + 1][j][p - 1][1] + 1 < map[i][j][p][2]) {
									map[i][j][p][2] = map[i + 1][j][p - 1][1] + 1;
									change = true;
								}
							}
							if (good(i, j - 1)) {
								if (map[i][j - 1][p][3] != inf && map[i][j - 1][p][3] + 1 < map[i][j][p][3]) {
									map[i][j][p][3] = map[i][j - 1][p][3] + 1;
									change = true;
								}
								if (map[i][j - 1][p][0] != inf && map[i][j - 1][p][0] + 1 < map[i][j][p][3]) {
									map[i][j][p][3] = map[i][j - 1][p][0] + 1;
									change = true;
								}
								if (p > 0 && map[i][j - 1][p - 1][2] != inf && map[i][j - 1][p - 1][2] + 1 < map[i][j][p][3]) {
									map[i][j][p][3] = map[i][j - 1][p - 1][2] + 1;
									change = true;
								}
							}
							if (good(i, j + 1)) {
								if (map[i][j + 1][p][1] != inf && map[i][j + 1][p][1] + 1 < map[i][j][p][1]) {
									map[i][j][p][1] = map[i][j + 1][p][1] + 1;
									change = true;
								}
								if (map[i][j + 1][p][2] != inf && map[i][j + 1][p][2] + 1 < map[i][j][p][1]) {
									map[i][j][p][1] = map[i][j + 1][p][2] + 1;
									change = true;
								}
								if (p > 0 && map[i][j + 1][p - 1][0] != inf && map[i][j + 1][p - 1][0] + 1 < map[i][j][p][1]) {
									map[i][j][p][1] = map[i][j + 1][p - 1][0] + 1;
									change = true;
								}
							}
						}
		}
		
		int min = inf;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				if (inp[i][j] == 3)
					for (int p = 0; p <= k; ++p)
						for (int c = 0; c < 4; ++c)
							if (map[i][j][p][c] < min)
								min = map[i][j][p][c];
		
		out.println(min == inf ? -1 : min);
		out.close();
	}
	
	static boolean change;
	
	static int inf = (int)1e6 + 1;
	
	static int bi, bj;
	static int n, m, k;
	static int[][] inp;
	static int[][][][] map;
	
	static boolean good(int i, int j) {
		return 0 <= i && i < n && 0 <= j && j < m && inp[i][j] != 1;
	}
}