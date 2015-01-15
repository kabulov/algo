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
		
		int[] v = new int[n + 1];
		for (int i = 0; i < n; ++i) v[i] = nextInt();
		v[n] = 1000000 + 30000 + 1;
		
		Arrays.sort(v);
		
		int ans = v[0];
		
		for (int i = 1, len = 1; 0 < m && i <= n; ++i, ++len) 
			if (v[i - 1] != v[i]) { 
				ans += (m / len < v[i] - v[i - 1]) ? m / len : v[i] - v[i - 1];
				m -= (v[i] - v[i - 1]) * len;
			}
		
		out.println(ans);
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}