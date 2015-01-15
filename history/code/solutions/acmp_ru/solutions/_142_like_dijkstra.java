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
		
		int[][] map = new int[n][n];
		for (int i = 0; i < n; ++i) 
			for (int j = 0; j < n; ++j)
				map[i][j] = inf;
		
		int a, b, c;
		for (int i = 0; i < m; ++i) {
			a = nextInt() - 1;
			b = nextInt() - 1;
			c = nextInt();
			if (a != b && c < map[a][b]) {
				map[a][b] = map[b][a] = c;
			}
		}
						
		int[] d = new int[n];
		Arrays.fill(d, inf);
		d[0] = 0;
		
		int[] v = new int[n];
		for (int i = 0; i < n; ++i)
			v[i] = i;
		
		int cost = 0, tmp;
		for (int p = 0; p < n - 1; ++p) {
			int pos = p + 1;
			for (int i = p + 1; i < n; ++i) {
				if (map[v[p]][v[i]] < d[i])
					d[i] = map[v[p]][v[i]];
				if (d[i] < d[pos]) pos = i;
			}
			cost += d[pos];
			
			tmp = d[pos];
			d[pos] = d[p + 1];
			d[p + 1] = tmp;
			
			tmp = v[pos];
			v[pos] = v[p + 1];
			v[p + 1] = tmp;
		}
		
		out.println(cost);
		out.close();
	}
	
	static int inf = (int)1e6;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}