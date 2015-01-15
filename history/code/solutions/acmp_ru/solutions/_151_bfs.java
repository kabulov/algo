import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int n = nextInt();
		int m = nextInt();
		
		boolean[][] map = new boolean[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				map[i][j] = false;
		
		for (int k = 0; k < m; ++k) {
			int i = nextInt() - 1;
			int j = nextInt() - 1;
			map[i][j] = map[j][i] = true;
		}
			
		int[] col = new int[n];
		Arrays.fill(col, 0);
		
		int[] que = new int[n];
		int add, rem;
		
		boolean good = true;
outer:	for (int i = 0; i < n; ++i) 
			if (col[i] == 0) {
				col[i] = 1;
				que[0] = i;
				add = 1;
				rem = 0;
				int p;
				while (rem < add) {
					p = que[rem];
					for (int j = 0; j < n; ++j)
						if (map[p][j] && col[j] != 3 - col[p]) 
							if (col[j] == 0) {
								col[j] = 3 - col[p];
								que[add] = j;
								++add;
							} else {
								good = false;
								break outer;
							}
					++rem;
				}
			}
		
		out.println(good ? "YES" : "NO");
		out.close();
	}

	
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}