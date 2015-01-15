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
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = -1;
		
		for (int i = 0; i < n; i++)
			map[i][i] = 1;
		
		for (int i = 0; i < n - 1; i++)
			if (inp[i] == inp[i + 1])
				map[i][i + 1] = 3;
			else
				map[i][i + 1] = 2;
		
		solve(0, n - 1);
		
		out.println(map[0][n - 1]);		
		out.close();
	}
	
	static int n;
	static char[] inp;
	static int[][] map;
	
	static void solve(int l, int r) {
		if (map[l][r - 1] == -1)
			solve(l, r - 1);
		
		if (map[l + 1][r] == -1)
			solve(l + 1, r);
		
		map[l][r] = map[l + 1][r] + map[l][r - 1];
		if (inp[l] == inp[r])
			++map[l][r];
		else
			map[l][r] -= map[l + 1][r - 1];
	}
}
