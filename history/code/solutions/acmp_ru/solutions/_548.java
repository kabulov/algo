import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");
		
		fst = in.next().toCharArray();
		scd = in.next().toCharArray();
		
		map = new boolean[scd.length][fst.length];		
		
		for (int i = scd.length - 1; i >= 0; --i) {
			for (int j = fst.length - 1; j >= 0; --j) {
				if (fst[j] < scd[i]) {
					map[i][j] = true;
				} else
				if (fst[j] > scd[i]) {
					map[i][j] = false;
				} else {
					if (i + 1 == scd.length) {
						map[i][j] = true;
					} else
					if (j + 1 == fst.length) {
						map[i][j] = false;
					} else
					if (compareTo(i, j + 1, i + 1, j) == -1)
						map[i][j] = true;
					else
						map[i][j] = false;
				}
			}
		}
		
		int i = 0, j = 0;
		while (i < scd.length || j < fst.length)
			if (i == scd.length) {
				out.print(fst[j]);
				++j;
			} else
			if (j == fst.length) {
				out.print(scd[i]);
				++i;
			} else
			if (map[i][j]) {
				out.print(fst[j]);
				++j;
			} else {
				out.print(scd[i]);
				++i;
			}
		
		out.close();
	}
	
	static char[] fst, scd;
	static boolean[][] map;
	
	static int compareTo(int i1, int j1, int i2, int j2) {
		char c1, c2;
		while (i1 < scd.length || j1 < fst.length) {
			if (i1 == scd.length) {
				c1 = fst[j1];
				++j1;
			} else
			if (j1 == fst.length) {
				c1 = scd[i1];
				++i1;
			} else {
				if (map[i1][j1]) {
					c1 = fst[j1];
					++j1;
				} else {
					c1 = scd[i1];
					++i1;
				}
			}
			
			if (i2 == scd.length) {
				c2 = fst[j2];
				++j2;
			} else
			if (j2 == fst.length) {
				c2 = scd[i2];
				++i2;
			} else {
				if (map[i2][j2]) {
					c2 = fst[j2];
					++j2;
				} else {
					c2 = scd[i2];
					++i2;
				}
			}
			
			if (c1 > c2) return 1;
			if (c1 < c2) return -1;
		}
		
		return 0;
	}
}