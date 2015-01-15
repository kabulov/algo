import java.awt.Point;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		double h, b, m;
		h = next();
		b = next();
		m = next();
		
		double tmp;
		if (h > b) {
			tmp = h;
			h = b;
			b = tmp;
		}
		if (h > m) {
			tmp = h;
			h = m;
			m = tmp;
		}
		if (b > m) {
			tmp = b;
			b = m;
			m = tmp;
		}
		
		if (equal(h, b) && equal(b, m)) {
			out.println(0);
		} else
		if (equal(h, b) || equal(b, m)) {
			out.println(-1);
		} else {
			double x1, x2, x3, x;
			
			x1 = Math.sqrt(b * b - h * h);
			x3 = Math.sqrt(m * m - h * h);
			x2 = x3 - x1;
			
			x = Math.sqrt(x2 * x3 + h * h * x2 / x1);
			
			out.println(x * h);
		}
		
		out.close();
	}
	
	static boolean equal(double a, double b) {
		return Math.abs(a - b) < eps;
	}
	
	static double eps = 1e-10;
	
	static StreamTokenizer in;
	static double next() throws IOException {
		in.nextToken();
		return in.nval;
	}
}