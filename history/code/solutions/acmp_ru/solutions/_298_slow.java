import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

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
		
		int m;
		double tmp;
		for (int i = 0; i < n; i++) {
			m = i;
			for (int j = i + 1; j < n; j++)
				if (v[j] < v[m])
					m = j;
			
			tmp = v[m];
			v[m] = v[i];
			v[i] = tmp;
		}
		
		int ans = 1;
		
		for (int i = 1; i < n; i++)
			if (v[i] - v[i - 1] >= eps)
				ans++;
		
		out.println(ans);
		out.close();
	}
	
	static double eps = 1e-15;
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
