
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		n = 1 << (N = next());
		src = new int[n + 1];		
		for (int i = 0; i < n; ++i) src[i] = next();
		src[n] = src[0];
		
		bad = 0;
		map = new int[n];
		for (int i = 0; i < n; ++i) 
			bad += map[i] = good(src[i], src[i + 1]) ? 0 : 1;		
		
		m = next();
		for (int lp = 0, i, j, t; lp < m; ++lp) {
			i = next(); 
			j = next();
			if (i > j) {
				t = i;
				i = j;
				j = t;
			}
			
			t = src[i];
			src[i] = src[j];
			src[j] = t;
			src[n] = src[0];
			
			if (i == 0) {
				t = good(src[n - 1], src[n]) ? 0 : 1;
				bad += t - map[n - 1];
				map[n - 1] = t;
			} else {
				t = good(src[i - 1], src[i]) ? 0 : 1;
				bad += t - map[i - 1];
				map[i - 1] = t;
			}
			
			t = good(src[i], src[i + 1]) ? 0 : 1;
			bad += t - map[i];
			map[i] = t;
			
			if (i + 1 < j) {
				t = good(src[j - 1], src[j]) ? 0 : 1;
				bad += t - map[j - 1];
				map[j - 1] = t;
			}
			
			if (j + 1 < n || i > 0) {
				t = good(src[j], src[j + 1]) ? 0 : 1;
				bad += t - map[j];
				map[j] = t;
			}
			
			out.println(bad > 0 ? "No" : "Yes");
		}
		
		out.close();
	}

	static int bad, n, m, N;
	static int[] src, map;
	
	static boolean good(int a, int b) {
		int ans = 0;
		for (int i = 0; i < N; ++i) {
			if ((a & 1) != (b & 1)) ++ans;
			a >>= 1;
			b >>= 1;
		}
		return ans == 1 ? true : false;
	}
	
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
	static PrintWriter out;
}
