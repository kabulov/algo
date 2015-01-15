import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = next();
		double[] v = new double[n];
		
		int x, y;
		for (int i = 0; i < n; i++) {
			x = next();
			y = next();
			v[i] = Math.atan2(y, x);
		}
		
		rand = new Random();
		sort(v, 0, n - 1);
		
		int ans = 1;
		
		for (int i = 1; i < n; i++)
			if (v[i] - v[i - 1] >= eps)
				ans++;
		
		out.println(ans);
		out.close();
	}
	
	static double eps = 1e-15;
	
	static Random rand;
	static double mid, buf;
	static void sort(double[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			for (; v[i] < mid; ++i);
			for (; mid < v[j]; --j);
			if (i <= j) {
				buf = v[i];
				v[i] = v[j];
				v[j] = buf;
				++i;
				--j;
			}
		}
		if (l < j) sort(v, l, j);
		if (i < r) sort(v, i, r);
	}
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
