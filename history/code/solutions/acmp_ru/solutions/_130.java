import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int i1, j1, i2, j2;
		
		String buf = in.next();
		i1 = buf.charAt(0) - 'a';
		j1 = buf.charAt(1) - '1';
		
		buf = in.next();
		i2 = buf.charAt(0) - 'a';
		j2 = buf.charAt(1) - '1';
		
		if ((i1 + j1 + i2 + j2) % 2 == 1)
			out.println(-1);
		else {
			map = new int[8][8];
			for (int i = 0; i < 8; ++i)
				for (int j = 0; j < 8; ++j)
					map[i][j] = -1;
			
			map[i1][j1] = 0;
			iv = new int[64];
			jv = new int[64];
			iv[0] = i1;
			jv[0] = j1;
			add = 1;
			rem = 0;
			int i, j;
outer:		while (rem < add) {
				for (int k = 0; k < 8; ++k) {
					i = iv[rem] + way[k][0];
					j = jv[rem] + way[k][1];
					if (good(i, j) && map[i][j] == -1) {
						map[i][j] = map[iv[rem]][jv[rem]] + 1;
						iv[add] = i;
						jv[add] = j;
						if (i == i2 && j == j2) {
							break outer;
						}
						++add;
					}
				}
				++rem;
			}
			out.println(map[iv[add]][jv[add]] / 2);
		}
		
		out.close();
	}
	
	static int[] iv, jv;
	static int add, rem;
	static int[][] map;
	
	static boolean good(int i, int j) {
		return 0 <= i && i < 8 && 0 <= j && j < 8;
	}
	
	static int[][] way = {
		{1, 2},
		{1, -2},
		{-1, 2},
		{-1, -2},
		{2, 1},
		{2, -1},
		{-2, 1},
		{-2, -1}
	};
}