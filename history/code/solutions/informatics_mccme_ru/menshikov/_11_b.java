import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = nextInt();
		k = nextInt();
		
		int r = Integer.MIN_VALUE;
		int sum = 0;
		
		v = new int[n];
		for (int i = 0; i < n; i++) {
			sum += v[i] = nextInt();
			if (v[i] > r)
				r = v[i];
		}
		
		int l = 0;
		++r;
		
		int mid;
		while (r - l > 1) {
			mid = (l + r) / 2;
			if (!can(mid))
				r = mid;
			else
				l = mid;
		}
		
		out.println(l);
		out.close();
	}
	
	static int[] v;
	static int k, n;
	static boolean can(int m) {
		int s = 0;
		for (int i = 0; i < n; i++)
			s += v[i] / m;
		
		return s >= k;
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}