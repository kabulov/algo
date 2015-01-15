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
		
		int ic = in.nextInt() - 1;
		int jc = in.nextInt() - 1;
		
		boolean[][] map = new boolean[n][m];

		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) 
				map[i][j] = true;
		
		map[0][0] = false;
		
		int ans = 1;
		int vect = 0;
		
		int ci, cj;
		ci = cj = 0;
		
		while (!(ci == ic && cj == jc)) {
			switch(vect) {
			case 0:
				if (cj + 1 == m || !map[ci][cj + 1]) {
					vect = 1;
				} else {
					++cj;
					map[ci][cj] = false;
					++ans;
				}
				break;
			case 1:
				if (ci + 1 == n || !map[ci + 1][cj]) {
					vect = 2;
				} else {
					++ci;
					map[ci][cj] = false;
					++ans;
				}
				break;
			case 2:
				if (cj == 0 || !map[ci][cj - 1]) {
					vect = 3;
				} else {
					--cj;
					map[ci][cj] = false;
					++ans;
				}
				break;
			case 3:
				if (ci == 0 || !map[ci - 1][cj]) {
					vect = 0;
				} else {
					--ci;
					map[ci][cj] = false;
					++ans;
				}
				break;
			}			
		}
		
		out.println(ans);
		out.close();
	}
}