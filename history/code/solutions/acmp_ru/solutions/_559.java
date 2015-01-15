import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		double l = in.nextDouble();
		double r = in.nextDouble();
		int n = in.nextInt();
		
		double len = l + 2 * r;
		double s = (pi * r * r + 2 * r * l) / n;
		
		double lt, rt, mid, tmp;
		for (int i = 0; i < n - 1; i++) {
			lt = 0;
			rt = len;

			while (Math.abs(rt - lt) >= eps) {
				mid = (lt + rt) / 2;
				tmp = area(r, l, mid);
				if (tmp - i * s > s)
					rt = mid;
				else
					lt = mid;
			}
			
			out.printf("%.6f\n", (lt + rt) / 2);
		}
		
		out.close();
	}
	
	static double area(double rad, double len, double offer) {
		if (offer <= rad) {
			double a = rad - offer;
			double s = rad * rad * (Math.asin(a / rad) + a / rad * Math.sqrt(1 - (a * a) / (rad * rad)));
			return pi * rad * rad / 2 - s;
		}
		
		if (offer <= rad + len) 
			return pi * rad * rad / 2 + (offer - rad) * 2 * rad;

		//offer > rad + len
		double a = offer - rad - len;
		double s = rad * rad * (Math.asin(a / rad) + a / rad * Math.sqrt(1 - (a * a) / (rad * rad)));
		return pi * rad * rad / 2 + len * 2 * rad + s;
	}
	
	static double eps = 1e-10;
	static double pi = Math.PI;
}
