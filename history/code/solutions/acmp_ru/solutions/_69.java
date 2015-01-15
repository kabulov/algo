import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = next();
		int a = next();
		
		if (a < a * Math.cos(pi / n) + 2 * Math.sin(pi / n))
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
	
	static double pi = Math.PI;
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
// R = a / (2 * sin(pi / n))
// r = a / (2 * tan(pi / n))