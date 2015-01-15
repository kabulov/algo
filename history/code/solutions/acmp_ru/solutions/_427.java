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
		long[] v = new long[n];
		for (int i = 0; i < n; i++)
			v[i] = nextInt();
		
		Arrays.sort(v);
		
		if (v[0] > 1)
			out.println(1);
		else {
			long sum = 1;
			boolean found = false;
			for (int i = 1; i < n; i++) {
				if (sum + 1 < v[i]) {
					found = true;
					out.println(sum + 1);
					break;
				} else
					sum += v[i];
			}
			if (!found)
				out.println(sum + 1);
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}