import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		n = nextInt();
		m = nextInt();
		k = nextInt();
		c = nextInt() - 1;
		
		dst = new int[k];
		for (int i = 0; i < k; ++i)
			dst[i] = nextInt() - 1;
		
		byte[][] map = new byte[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				map[i][j] = inf;
		
		int a, b;
		for (int i = 0; i < m; ++i) {
			a = nextInt() - 1;
			b = nextInt() - 1;
			map[a][b] = map[b][a] = (byte)nextInt();
		}
		
		d = new int[n];
		v = new int[n];
		
		for (int i = 0; i < n; ++i) {
			d[i] = (int)1e6 + 1;
			v[i] = i;
		}
			
		d[0] = 0;
		v[0] = c;
		v[c] = 0;
		
		int tmp;
		for (int i = 0; i < n - 1; ++i) {
			int p = i + 1;
			for (int j = i + 1; j < n; ++j) {
				if (map[v[i]][v[j]] < inf && d[i] + map[v[i]][v[j]] < d[j])
					d[j] = d[i] + map[v[i]][v[j]];
				
				if (d[j] < d[p]) p = j;
			}
			
			tmp = d[i + 1];
			d[i + 1] = d[p];
			d[p] = tmp;
			
			tmp = v[i + 1];
			v[i + 1] = v[p];
			v[p] = tmp;

		}
		
		int[] time = new int[k];
		for (int i = 0; i < k; ++i) {
			int p = dst[i];
			for (int j = 0; j < n; ++j)
				if (v[j] == p) {
					time[i] = d[j];
					break;
				}
		}
		
		for (int i = 0; i < k - 1; ++i) {
			int p = i;
			for (int j = i + 1; j < k; ++j)
				if (time[j] < time[p] || (time[j] == time[p] && dst[j] < dst[p]))
					p = j;
			
			tmp = time[i];
			time[i] = time[p];
			time[p] = tmp;
			
			tmp = dst[i];
			dst[i] = dst[p];
			dst[p] = tmp;
		}
		
		for (int i = 0; i < k; ++i) {
			out.println(++dst[i] + " " + time[i]);
		}
		
		out.close();
	}
	
	static int[] from;
	static int[] to;
	static int[] cst;
	
	static int[] d;
	static int[] v;
	
	static int[] dst;
	static int n, m, k, c;

	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static byte inf = (byte)101;
}