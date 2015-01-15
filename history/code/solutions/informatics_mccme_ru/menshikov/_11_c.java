import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		v = in.readLine().toCharArray();
		n = v.length;
		
		map = new long[n][n];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = -1;
		
		for (int i = 0; i < n; i++)
			map[i][i] = 1;
		
		if (n > 1)
			solve(0, n - 1);
		
		out.println(map[0][n - 1]);
		out.close();
	}
	
	static int n;
	static char[] v;
	
	static long[][] map;
	static void solve(int l, int r) {
		if (l + 1 == r) {
			if (v[l] == v[r])
				map[l][r] = 3;
			else
				map[l][r] = 2;
			return;
		}
		
		if (map[l][r - 1] == -1)
			solve(l, r - 1);
		if (map[l + 1][r] == -1)
			solve(l + 1, r);
		
		map[l][r] = map[l + 1][r] + map[l][r - 1];
		if (v[l] != v[r])
			map[l][r] -= map[l + 1][r - 1];
		else
			++map[l][r];
	}
}