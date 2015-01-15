import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		int e = nextInt();
		
		double inf = Long.MAX_VALUE / 2;
		
		double[][] v = new double[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j) 
				v[i][j] = i == j ? 0 : inf;
			
		boolean good = true;
		double d;
		
		for (int i = 0, a, b, t; i < e; ++i) {
			a = nextInt() - 1;
			b = nextInt() - 1;
			
			if (a > b) {
				t = a;
				a = b;
				b = t;
			}
			
			d = nextDouble();
			if (equal(v[a][b], inf)) {
				v[a][b] = d;
				v[b][a] = -d;
			} else
				good &= equal(v[a][b], d);
		}
		
		for (int k = 0; k < n; ++k)
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j)
					if (v[i][k] < inf && v[k][j] < inf)
						if (equal(v[i][j], inf)) {
							v[i][j] = v[i][k] + v[k][j];
							v[j][i] = -v[i][j];
						} else {
							good &= equal(v[i][j], v[i][k] + v[k][j]);
						}
		
		for (int i = 0; i < n - 1; ++i)
			good &= v[i][i + 1] < inf;
		
		if (!good)
			out.println("NO");
		else {
			out.println("YES");
			for (int i = 0; i < n - 1; ++i) 
				out.printf("%.3f ", v[i][i + 1]);
		}
		
		out.close();
	}	
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
	
	static boolean equal(double a, double b) {
		return Math.abs(a - b) < eps;
	}
	
	static double eps = 1e-5;
	
}
