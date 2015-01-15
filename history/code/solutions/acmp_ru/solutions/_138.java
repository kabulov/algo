import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt(), m = in.nextInt();
		edge[] v = new edge[m];
		for (int i = 0; i < m; ++i)
			v[i] = new edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
		
		int[] d = new int[n];
		Arrays.fill(d, inf);
		d[0] = 0;
		
		int tmp;
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < m; ++j)
				if (d[v[j].a] < inf) {
					tmp = d[v[j].a] + v[j].c;
					if (tmp < d[v[j].b]) d[v[j].b] = tmp;
				}
		}
		
		for (int i = 0; i < n; i++) {
			out.print(d[i]);
			out.print(" ");
		}
		out.close();
	}
	
	static int inf = 30000;
}

class edge {
	int a, b, c;
	
	edge(int f, int s, int t) {
		a = f;
		b = s;
		c = t;
	}
}