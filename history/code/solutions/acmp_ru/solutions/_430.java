import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		double rad = in.nextDouble();
		
		//w - wirota, d - dolgota
		double w1, d1, w2, d2;
		
		w1 = Math.toRadians(in.nextDouble());
		d1 = Math.toRadians(in.nextDouble());
		
		w2 = Math.toRadians(in.nextDouble());
		d2 = Math.toRadians(in.nextDouble());
		
		double x1, y1, z1, x2, y2, z2;
		
		z1 = rad * Math.sin(w1);
		x1 = rad * Math.cos(w1) * Math.sin(d1);
		y1 = rad * Math.cos(w1) * Math.cos(d1);
		
		z2 = rad * Math.sin(w2);
		x2 = rad * Math.cos(w2) * Math.sin(d2);
		y2 = rad * Math.cos(w2) * Math.cos(d2);
		
		double a = y1 * z2 - z1 * y2;
		double b = z1 * x2 - x1 * z2;
		double c = x1 * y2 - x2 * y1;
		
		double v = Math.sqrt(a * a + b * b + c * c);
		double s = x1 * x2 + y1 * y2 + z1 * z2;
		out.printf("%.2f", rad * Math.atan2(v, s));
		
		out.close();
	}
}
