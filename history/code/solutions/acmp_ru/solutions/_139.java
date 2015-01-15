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
		
		class edge {
			int bgn, end, cst;
			edge(int a, int b, int c) {
				bgn = a;
				end = b;
				cst = c;
			}
		}
		
		edge[] v = new edge[m];
		for (int i = 0; i < m; ++i) {
			v[i] = new edge(nextInt() - 1, nextInt() - 1, -nextInt());
		}
		
		int[] d = new int[n];
		Arrays.fill(d, inf);
		d[0] = 0;
		
		for (int i = 1; i < n; ++i)
			for (int j = 0; j < m; ++j)
				if (d[v[j].bgn] != inf && d[v[j].bgn] + v[j].cst < d[v[j].end]) {
					d[v[j].end] = d[v[j].bgn] + v[j].cst;
					if (d[v[j].end] < -inf) d[v[j].end] = -inf;
				}
		
		boolean relax = false;
		for (int j = 0; j < m; ++j)
			if (d[v[j].bgn] != inf && d[v[j].bgn] + v[j].cst < d[v[j].end]) {
				d[v[j].end] = -inf;
				relax = true;
			}
		
		if (relax) {
			for (int i = 1; i < n; ++i)
				for (int j = 0; j < m; ++j)
					if (d[v[j].bgn] == -inf) {
						d[v[j].end] = -inf;
					}
		}
		
		out.println(d[n - 1] == inf ? ":(" : d[n - 1] == -inf ? ":)" : -d[n - 1]);
		out.close();
	}
	
	static int inf = (int)1e9 + 1;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
