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
		map = new boolean[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				map[i][j] = false;
		
		int m = nextInt();
		for (int k = 0; k < m; ++k) {
			int i = nextInt() - 1, j = nextInt() - 1;
			map[i][j] = true;
		}
		
		col = new int[n];
		Arrays.fill(col, 0);
		
		good = true;
		for (int i = 0; good && i < n; ++i) 
			if (col[i] == 0)
				bfs(i);
		
		out.println(good ? "Yes" : "No");
		out.close();
	}
	
	static int[] col;
	static boolean[][] map;
	static boolean good;
	static int n;
	
	static void bfs(int i) {
		col[i] = 1;
		for (int j = 0; good && j < n; ++j)
			if (map[i][j]) 
				if (col[j] == 1) {
					good = false;
					break;
				} else
				if (col[j] == 0)
					bfs(j);
		col[i] = -1;
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}