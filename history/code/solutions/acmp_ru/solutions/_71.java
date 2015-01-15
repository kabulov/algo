import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = nextInt();
		v = new int[n];
		sum = 0;
		for (int i = 0; i < n; i++) {
			v[i] = nextInt();
			sum += v[i];
		}
			
		min = sum;
		solve(0, 0);
		
		out.println(min);
		out.close();
	}
	
	static void solve(int pos, int s) {
		if (pos == n) {
			if (Math.abs(2 * s - sum) < min)
				min = Math.abs(2 * s - sum);
			return;
		}
		
		solve(pos + 1, s + v[pos]);
		solve(pos + 1, s);
	}
	
	static int[] v;
	static int min, sum, n;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
