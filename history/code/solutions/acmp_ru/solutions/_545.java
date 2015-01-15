
import static java.lang.Math.sqrt;
import static java.lang.Math.max;
import static java.lang.Math.abs;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		double[] v = new double[3];
		for (int i = 0; i < 3; ++i) v[i] = in.nextDouble();
		Arrays.sort(v);
		
		if (abs(sqr(v[2]) - sqr(v[1]) - sqr(v[0])) < eps)
			out.println(v[0] * v[1] / 2.0);
		else 
		if (sqr(v[2]) + eps < sqr(v[0]) + sqr(v[1])) {//ostrougolniy
			double a, b, c;
			a = acute(v[0], v[1], v[2]);	//!may v[2], v[1] also
			b = acute(v[1], v[0], v[2]);	//!
			c = acute(v[2], v[0], v[1]);	//!
			out.println(max(a, max(b, c)));
		} else {
			double a, b;
			a = obtuse(v[0], v[1], v[2]);
			b = obtuse(v[1], v[0], v[2]);
			out.println(max(a, b));
		}
				
		out.close();
	}	
	
	static double obtuse(double a, double b, double c) {
		double cosa = (sqr(a) + sqr(c) - sqr(b)) / (2 * a * c);
		double d = a * cosa;
		double sina = sqrt(1 - sqr(cosa));
		double h = a * sina; //sqrt(sqr(a) - sqr(d)); 
		double ans = max(d, c - d) * h / 2;
		d = sqr(a) * sina / (2 * cosa);
		return max(ans, d);
	}
	
	static double acute(double a, double b, double c) {
		double y1, y2;
		double cosa = (sqr(a) + sqr(b) - sqr(c)) / (2 * a * b);
		y1 = a * cosa * cosa;
		double cosb = (sqr(a) + sqr(c) - sqr(b)) / (2 * a * c);
		y2 = a * cosb * cosb;
		
		if (y1 > a / 2) {
			double sina = sqrt(1 - sqr(cosa));
			double tga = sina / cosa;
			double h = y1 * tga;
			return a * h / 2;
		}
		
		if (y2 > a / 2) {
			double sinb = sqrt(1 - sqr(cosb));
			double tgb = sinb / cosb;
			double h = y2 * tgb;
			return a * h / 2;
		}
		
		return sqr(a) / 4;	//!precision
	}
	
	static double sqr(double a) {
		return a * a;
	}
	
	static double eps = 1e-14; //!!
}