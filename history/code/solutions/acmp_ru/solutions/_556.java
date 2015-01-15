import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		double p = 1, pi;
		int n = (int)next();
		for (int i = 0; i < n; i++) {
			pi = next();
			p = p * pi + (1 - p) * (1 - pi);
		}
		
		out.println(p);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static double next() throws IOException {
		in.nextToken();
		return in.nval;
	}
}
