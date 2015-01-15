import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] inp = new int[n];
		for (int i = 0; i < n; i++)
			inp[i] = nextInt();
		
		int[] vect = new int[201];
		for (int i = 0; i < n; i++)
			vect[100 + inp[i]]++;
		
		int mxpos = 0;
		for (int i = 1; i < 201; i++)
			if (vect[i] > vect[mxpos])
				mxpos = i;
		
		int mxmin = mxpos - 100;
		for (int i = 0; i < n; i++)
			if (inp[i] != mxmin)
				out.print(inp[i] + " ");
		
		for (int i = 0; i < vect[mxpos]; i++)
			out.print(mxmin + " ");
		
		out.close();
	}

	static PrintWriter out;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}