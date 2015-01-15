import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));	
		PrintWriter out = new PrintWriter("output.txt");

		inp = in.readLine().toCharArray();
		n = inp.length;
		
		map = new int[n][n];
		
		if (n % 2 == 1) {
			out.println(0);
			out.close();
			return;
		} else
		if (n == 2) {
			if (inp[0] != ')' && inp[1] != '(')
				out.println(1);
			else
				out.println(0);
			
			out.close();
			return;
		}
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = -1;
		
		for (int i = 0; i < n - 1; i++)
			if (inp[i] != ')' && inp[i + 1] != '(')
				map[i][i + 1] = 1;
			else
 				map[i][i + 1] = 0;
		
		solve(0, n - 1);
		
		out.println(map[0][n - 1]);
		out.close();
	}
	
	static void solve(int l, int r) {
		if (inp[l] == ')' || inp[r] == '(') {
			map[l][r] = 0;
			return;
		}
		
		if (map[l + 1][r - 1] == -1)
			solve(l + 1, r - 1);
		map[l][r] = map[l + 1][r - 1];

		if (map[l][l + 1] > 0) {
			if (map[l + 2][r] == -1)
				solve(l + 2, r);
			map[l][r] += map[l + 2][r];
		}
			
		for (int i = l + 3; i < r; i += 2) {
			if (inp[i] == '(')
				continue;
			
			if (map[l + 1][i - 1] == -1)
				solve(l + 1, i - 1);
			
			if (map[i + 1][r] == -1)
				solve(i + 1, r);
			
			map[l][r] += map[l + 1][i - 1] * map[i + 1][r];
		}
	}
	
	static int n;
	static char[] inp;
	static int[][] map;
}
