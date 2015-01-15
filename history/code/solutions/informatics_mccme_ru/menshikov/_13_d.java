import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		long x, y, area, piece;
		area = x = y = 0;
		
		int n = nextInt();
		long x1, y1, x2, y2, x3, y3;
		
		x1 = nextInt();
		y1 = nextInt();
		
		x3 = nextInt();
		y3 = nextInt();
		
		for (int i = 3; i <= n; i++) {
			x2 = x3;
			y2 = y3;
			x3 = nextInt();
			y3 = nextInt();
			
			piece = vect(x1, y1, x2, y2, x3, y3);
			x += piece * (x1 + x2 + x3);
			y += piece * (y1 + y2 + y3);
			area += piece;
		}
		
		out.printf("%.2f %.2f", (double)x / (double)(3 * area), (double)y / (double)(3 * area));
		out.close();
	}
	
	static long vect(long x, long y, long x1, long y1, long x2, long y2) {
		return (x1 - x) * (y2 - y) - (x2 - x) * (y1 - y);
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}