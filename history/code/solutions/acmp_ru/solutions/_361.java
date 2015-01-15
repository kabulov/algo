import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		char[] v = in.nextLine().toCharArray();
		int n = v.length;
		
		int[][][] map = new int[n][n][26];
		
		int ans = 0, flag;
outer:	for (int j = n - 1; j > 0; --j)
			for (int i = 0; i < n - j + 1; ++i) {
				for (int k = i; k < i + j; ++k)
					++map[i][i + j - 1][v[k] - 'a'];
				
				for (int k = i - 1; k >= 0; --k) {
					flag = 1;
					for (int p = 0; p < 26; ++p)
						if (map[i][i + j - 1][p] != map[k][k + j - 1][p]) {
							flag = 0;
							break;
						}
					
					if (flag == 1) {
						ans = j;
						break outer;
					}
				}
			}
		
		out.println(ans);
		out.close();
	}
}