import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));	
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] v = new int[n];
		
		int minpos = 0;
		for (int i = 0; i < n; i++) {
			v[i] = nextInt();
			if (v[i] == 1)
				minpos = i;
		}
		
		out.print(1);
		for (int pos = (minpos + 1) % n; pos != minpos; pos = (pos + 1) % n)
			out.print(" " + v[pos]);
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
