import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		n = nextInt();
		m = nextInt();
		
		map = new boolean[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				map[i][j] = false;
		
		for (int k = 0; k < m; ++k) {
			int i = nextInt() - 1;
			int j = nextInt() - 1;
			map[i][j] = map[j][i] = true;
		}
			
		col = new int[n];
		Arrays.fill(col, 0);
		
		good = true;
		for (int i = 0; i < n && good; ++i)
			if (col[i] == 0) 
				bfs(i, 1);
		
		out.println(good ? "YES" : "NO");
		out.close();
	}

	static void bfs(int p, int c) {
		col[p] = c;
		for (int i = 0; i < n && good; ++i)
			if (map[p][i] && col[i] + c != 3)
				if (col[i] == 0) {
					bfs(i, 3 - c);
				} else {
					good = false;
				}
	}
	
	static int n, m;
	static int[] col;
	static boolean[][] map;
	static boolean good;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}