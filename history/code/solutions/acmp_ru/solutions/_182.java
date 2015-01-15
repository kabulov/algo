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
		int x3 = next();
		int y3 = next();
		
		if (mul(x2 - x1, y2 - y1, x3 - x1, y3 - y1) == 0) { // x1, y1
			out.println((x3 + (x2 - x1)) + " " + (y3 + (y2 -  y1)));
		} else
		if (mul(x1 - x2, y1 - y2, x3 - x2, y3 - y2) == 0) { // x2, y2
			out.println((x3 + (x1 - x2)) + " " + (y3 + (y1 - y2)));
		} else { // x3, y3
			out.println((x2 + (x1 - x3)) + " " + (y2 + (y1 - y3)));
		}

		out.close();
	}
	
	static int mul(int x1, int y1, int x2, int y2) {
		return x1 * x2 + y1 * y2;
	}
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
