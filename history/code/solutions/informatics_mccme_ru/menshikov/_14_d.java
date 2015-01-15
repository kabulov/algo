import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		double a, b, c, d1, d2, d3;
		
		a = next();
		b = next();
		d1 = next();
		c = next();
		d2 = next();
		d3 = next();
		
		double cos = (d1 * d1 + a * a - d2 * d2) / (2 * d1 * a); 

		line fst = new line();
		fst.a = 1;
		fst.b = 0;
		fst.c = -d1 * cos;
		
		cos = (a * a + b * b - c * c) / (2 * a * b);
		
		double cx, cy;
		cx = b * cos;
		
		double sin = Math.sqrt(1 - cos * cos);
		cy = b * sin;
		
		double len = Math.sqrt(cx * cx + cy * cy);
		cx /= len;
		cy /= len;
		
		cos = (d1 * d1 + b * b - d3 * d3) / (2 * d1 * b);
		len = d1 * cos;
		
		double x0, y0;
		x0 = cx * len;
		y0 = cy * len;
		
		line scd = new line();
		scd.a = cx;
		scd.b = cy;
		scd.c = -(x0 * cx + y0 * cy);
		
		double delta, deltax, deltay;
		delta = -(fst.a * scd.b - scd.a * fst.b);
		deltax = fst.c * scd.b - fst.b * scd.c;
		deltay = fst.a * scd.c - fst.c * scd.a;
		
		double x, y;
		x = deltax / delta;
		y = deltay / delta;
		
		double z = Math.sqrt(d1 * d1 - x * x - y * y);
		double p = a + b + c;
		double area = Math.sqrt(p * (p - 2 * a) * (p - 2 * b) * (p - 2 * c) / 16);
		double volume = (z * area) / 3;
		
		out.printf("%.4f", volume);		
		out.close();
	}
	
	static boolean equal(double a, double b) {
		return Math.abs(a - b) < eps;
	}
	
	static double eps = 1e-10;
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class line {
	double a, b, c;
}