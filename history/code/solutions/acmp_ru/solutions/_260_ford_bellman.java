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
		k = nextInt();
		c = nextInt() - 1;
		
		dst = new int[k];
		for (int i = 0; i < k; ++i)
			dst[i] = nextInt() - 1;
		
		from = new int[2 * m];
		to = new int[2 * m];
		cst = new int[2 * m];
		
		for (int i = 0; i < m; ++i) {
			from[2 * i] = nextInt() - 1;
			to[2 * i] = nextInt() - 1;
			cst[2 * i] = nextInt();
			
			from[2 * i + 1] = to[2 * i];
			to[2 * i + 1] = from[2 * i];
			cst[2 * i + 1] = cst[2 * i];
		}
		
		d = new int[n];
		Arrays.fill(d, inf);
		d[c] = 0;
		 
		int tmp;
		for (int i = 1; i < n; ++i)
			for (int j = 0; j < 2 * m; ++j)
				if (d[from[j]] < inf) {
					tmp = d[from[j]] + cst[j];
					if (tmp < d[to[j]])
						d[to[j]] = tmp;
				}
					
		int[] time = new int[k];
		for (int i = 0; i < k; ++i)
			time[i] = d[dst[i]];
		
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
	static int[] dst;
	static int n, m, k, c;

	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static int inf = (int)1e9 + 1;
}