import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = next();
		
		double ans = 0;
		int x, y, xn = 0, yn = 0;
		for (int i = 0; i < n; i++) {
			x = xn;
			y = yn;
			
			xn = next();
			yn = next();
			
			ans += Math.hypot(x - xn, y - yn);
		}
			
		out.printf("%.3f", ans + Math.hypot(xn, yn));
		out.close();
	}
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
