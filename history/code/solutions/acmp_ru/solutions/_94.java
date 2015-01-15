import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int m = nextInt();
		int k = nextInt();
		
		if (n >= m)
			out.println(1);
		else
		if (k >= n) 
			out.println("NO");
		else {
			int tmp = n - k;
			int p = (m - n) / tmp;
			if (m - p * tmp == n)
				out.println(p + 1);
			else
				out.println(p + 2);
		}
		
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
