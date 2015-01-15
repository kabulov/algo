import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int x1 = next();
		int y1 = next();
		int x2 = next();
		int y2 = next();
		int r = next();
		
		double sone = next();
		double stwo;
		
		if (x1 == x2 && y1 == y2) {
			stwo = pi * r * r;
		} else {
			stwo = 2 * pi * r * r;
			if ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) < 4 * r * r) {
				double a = Math.hypot((x1 + x2) / 2.0 - x1, (y1 + y2) / 2.0 - y1);
				double b = Math.sqrt(r * r - a * a);
				stwo -= 2 * (r * r * Math.atan(b / a) - a * b);
			}
		}
		
		if (stwo < sone)
			out.println("NO");
		else
			out.println("YES");
		
		out.close();
	}
	
	static double pi = Math.PI;
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

