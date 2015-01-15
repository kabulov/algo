import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		a = in.nextDouble();
		
		double l = 0, r = Integer.MAX_VALUE;
		while (r - l >= eps) {
			double mid = (l + r) / 2;
			
			good = true;
			offer(mid);
			
			if (good)
				r = mid;
			else
				l = mid;
		}
		
		out.printf("%.2f", l);
		out.close();
	}
	
	static boolean good;
	
	static void offer(double m) {
		double cb = m / 2 - 1;
		double ca = 1.0 / 2;
		double x = ca * find(ca, cb, n - 2) + cb;
		if (x <= 0)
			good = false;
	}
	
	static double find(double ca, double cb, int p) {
		if (p == 1)
			return a;
		
		cb = cb / 2 - 1;
		ca = 1 - ca / 2;
		cb /= ca;
		ca = 1 / (2 * ca);
		double x = ca * find(ca, cb, p - 1) + cb;
		if (x <= 0)
			good = false;
		return x;
	}
	
	static int n;
	static double a;
	static double eps = 1e-3;
}