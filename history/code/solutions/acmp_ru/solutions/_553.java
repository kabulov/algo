import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] argv) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		inp = new elem[n];
		for (int i = 0; i < n; i++)
			inp[i] = new elem(nextInt(), nextInt());
		
		map = new int[n][n];
		for (int i = 0; i < n; i++)		
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++)
			map[i][i] = 0;
		
		if (n > 1)
			solve(0, n - 1);
		
		out.println(map[0][n - 1]);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static class elem {
		int m, k;
		elem(int mm, int kk) {
			m = mm;
			k = kk;
		}
	}
	
	static elem[] inp; 
	static int[][] map;
	static int tmp;
	
	static void solve(int l, int r) {
		int buf = inp[l].m * inp[r].k;
		for (int i = l; i < r; i++) {
			if (map[l][i] == Integer.MAX_VALUE)
				solve(l, i);
			if (map[i + 1][r] == Integer.MAX_VALUE)
				solve(i + 1, r);
			
			tmp = map[l][i] + map[i + 1][r] + buf;
			if (tmp < map[l][r])
				map[l][r] = tmp;
		}
	}
}
