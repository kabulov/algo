import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] konf = new int[n + 1];
		for (int i = 1; i <= n; i++)
			konf[i] = nextInt();
		
		int m = nextInt();
		int[] shaman = new int[200 + 1];
		
		for (int i = 1; i < 200 + 1; i++)
			shaman[i] = i;
		
		for (int i = 1; i <= m; i++)
			shaman[nextInt()] = nextInt();
		
		for (int i = 1; i <= n; i++) 
			out.print(shaman[konf[i]] + " ");
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
