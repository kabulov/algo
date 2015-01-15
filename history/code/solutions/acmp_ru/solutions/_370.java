import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = next();
		long fx, fy;
		
		fx = next();
		fy = next();
		
		long ox, oy, x, y;
		x = fx;
		y = fy;
		
		long s = 0;
		for (int i = 1; i < n; ++i) {
			ox = x;
			oy = y;
			x = next();
			y = next();
			s += ox * y - x * oy;
		}
		
		s += x * fy - fx * y;
		
		out.printf("%.1f", Math.abs((double)s / 2));
		out.close();
	}
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
