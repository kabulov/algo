import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int src = in.nextInt() - 1, dst = in.nextInt() - 1;
		int m = in.nextInt();
		
		class edge {
			int b, e, tb, te;
			edge(int f, int tf, int s, int ts) {
				b = f;
				e = s;
				tb = tf;
				te = ts;
			}
		}
		
		edge[] v = new edge[m];
		for (int i = 0; i < m; ++i) 
			v[i] = new edge(in.nextInt() - 1, in.nextInt(), in.nextInt() - 1, in.nextInt());
		
		int[] d = new int[n];
		Arrays.fill(d, inf);
		d[src] = 0;
		
		int tmp;
		for (int i = 1; i < n; ++i)
			for (int j = 0; j < m; ++j)
				if (d[v[j].b] <= v[j].tb && v[j].te < d[v[j].e]) 
					d[v[j].e] = v[j].te;
				
		out.println(d[dst] == inf ? -1 : d[dst]);		
		out.close();
	}
	
	static int inf = (int)1e6 + 1;
}