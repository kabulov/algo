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
		
		int[] x = new int[n];
		int[] y = new int[n];
		
		for (int i = 0; i < n; i++) {
			x[i] = nextInt();
			y[i] = nextInt();
		}
			
		int len = 0;
		int[] dist = new int[n * (n - 1) / 2];		
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				dist[len++] = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
		
		Arrays.sort(dist);
		
		int nlen = 1;
		for (int i = 1; i < len; i++)
			if (dist[i] != dist[i - 1])
				dist[nlen++] = dist[i];
		
		out.println(nlen);
		for (int i = 0; i < nlen; i++)
			out.println(Math.sqrt(dist[i]));
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
