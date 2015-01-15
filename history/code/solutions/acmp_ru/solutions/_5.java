import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		
		int[] odd = new int[n];
		int[] even = new int[n];
		
		int olen = 0, elen = 0, buf;
		for (int i = 0; i < n; i++) {
			buf = nextInt();
			if (buf % 2 == 0)
				even[elen++] = buf;
			else
				odd[olen++] = buf;
		}
		
		for (int i = 0; i < olen; i++)
			out.print(odd[i] + " ");
		
		out.println();
		for (int i = 0; i < elen; i++)
			out.print(even[i] + " ");
		
		out.println();
		if (olen > elen)
			out.println("NO");
		else
			out.println("YES");
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
