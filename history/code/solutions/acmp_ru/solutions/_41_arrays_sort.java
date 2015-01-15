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
		int[] v = new int[n];
		for (int i = 0; i < n; ++i)
			v[i] = nextInt();
		
		Arrays.sort(v);
		for (int i = 0; i < n; ++i)
			out.print(v[i] + " ");
				
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
