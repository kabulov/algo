import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int l = nextInt();
		int n = nextInt();
		
		int[] inp = new int[n];
		for (int i = 0; i < n; ++i) 
			inp[i] = nextInt();
		
		Arrays.sort(inp);
		
		int[] v = new int[n + 1];
		v[n] = 0;

		for (int i = n - 1, r = n - 1; i >= 0; --i) {
			v[i] = v[i + 1];
			for (int j = i + 1; j < n; ++j) {
				if (inp[i] + l < inp[j] - l) break;
				if (v[j + 1] < v[i]) v[i] = v[j + 1];
			}
			++v[i];
		}
		
		out.println(v[0]);
		out.close();
	}
	
	static StreamTokenizer in;

	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
