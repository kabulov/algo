import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		long n = nextInt();
		long tw = n / 2, th = n / 3, f = n / 5, twth = n / 6, twf = n / 10, thf = n / 15, twthf = n / 30;
		
		out.println(n - (tw + th + f -(twth + twf + thf) + twthf));
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}