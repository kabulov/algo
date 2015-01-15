import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new BufferedReader(new FileReader("input.txt")); 
		
		int n = Integer.parseInt(in.readLine());
		int[][] map = new int[n][n];
		
		int i, j;
		String str;
		for (i = 0; i < n; i++) {
			str = in.readLine();
			for (j = 0; j < n; j++) {
				map[i][j] = str.charAt(j) - 48;
				if (i == 0 && j == 0)
					continue;
				else
				if (i == 0) {
					map[i][j] += map[i][j - 1];
				} else
				if (j == 0) {
					map[i][j] += map[i - 1][j];
				} else {
					map[i][j] += Math.min(map[i][j - 1], map[i - 1][j]);
				}
			}
		}
		
		i = j = n - 1;
		while (i > 0 || j > 0) {
			map[i][j] = -1;
			if (i == 0) {
				--j;
			} else
			if (j == 0) {
				--i;
			} else
			if (map[i - 1][j] < map[i][j - 1]) {
				--i;
			} else {
				--j;
			}
		}
		
		map[0][0] = -1;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				if (map[i][j] == -1)
					out.print('#');
				else
					out.print('.');

			out.println();
		}
		
		out.close();
	}
	
	static BufferedReader in;
	static PrintWriter out;
}