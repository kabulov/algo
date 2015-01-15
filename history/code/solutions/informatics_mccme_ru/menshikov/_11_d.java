import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		double x1, y1, r1, x2, y2, r2;
		
		x1 = in.nextDouble();
		y1 = in.nextDouble();
		r1 = in.nextDouble();
		
		x2 = in.nextDouble();
		y2 = in.nextDouble();
		r2 = in.nextDouble();
		
		double eps = 1e-12;
		
		if (Math.abs(r1) < eps || Math.abs(r2) < eps) {
			out.printf("%.2f", 0);
			out.close();
			return;
		}

		double area = 0;
		double dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		if (dist >= (r1 + r2) * (r1 + r2)) {
			area = 0;
		} else 
		if (dist <= (r1 - r2) * (r1 - r2)) {	
			r1 = Math.min(r2, r1);
			area = Math.PI * r1 * r1;
		} else {
			if (r1 < r2) {
				double tmp = r1;
				r1 = r2;
				r2 = tmp;
				
				tmp = x1;
				x1 = x2;
				x2 =  tmp;
				
				tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			
			dist = Math.sqrt(dist);
			if ((r2 * r2 + dist * dist - r1 * r1) / (2 * r2 * dist) >= 0) {
				double x = (r2 * r2 + dist * dist - r1 * r1) / (2 * dist);
				double x3, y3, vx, vy;
				
				vx = x1 - x2;
				vy = y1 - y2;
				vx /= dist;
				vy /= dist;
				x3 = x2 + vx * x;
				y3 = x2 + vy * x;
				x = Math.sqrt(r2 * r2 - x * x);
				vx *= x;
				vy *= x;
				
				double tmp = vx;
				vx = -vy;
				vy = tmp;
				
				double x4, y4, x5, y5;
				
				x4 = x3 + vx;
				y4 = y3 + vy;
				x5 = x3 - vx;
				y5 = y3 - vy;
				
				area = alpha(x2, y2, x4, y4, x5, y5) * r2 * r2 - Math.abs(vect(x2, y2, x4, y4, x5, y5));
				area += alpha(x1, y1, x4, y4, x5, y5) * r1 * r1 - Math.abs(vect(x1, y1, x4, y4, x5, y5));
				area /= 2;
			} else {
				double x = (r1 * r1 - dist * dist - r2 * r2) / (2 * dist);
				double vx = x2 - x1;
				double vy = y2 - y1;
				
				vx /= dist;
				vy /= dist;
				
				double x3, y3;
				x3 = x1 + vx * (dist + x);
				y3 = y1 + vy * (dist + x);

				x = Math.sqrt(r2 * r2 - x * x);
				vx *= x;
				vy *= x;
				
				double tmp = vx;
				vx = -vy;
				vy = tmp;
				
				double x4, y4, x5, y5;
				x4 = x3 + vx;
				y4 = y3 + vy;
				x5 = x3 - vx;
				y5 = y3 - vy;
				
				area = alpha(x2, y2, x4, y4, x5, y5) * r2 * r2 - Math.abs(vect(x2, y2, x4, y4, x5, y5));
				area -= alpha(x1, y1, x4, y4, x5, y5) * r1 * r1 - Math.abs(vect(x1, y1, x4, y4, x5, y5));
				area = Math.PI * r2 * r2 - area / 2;
			}
		}
		
		out.printf("%.2f", area);
		out.close();
	}
	
	static void prn(Object o) {
		System.out.println(o);
	}
	
	static double alpha(double x, double y, double x1, double y1, double x2, double y2) {
		return Math.abs(Math.atan2(vect(x, y, x1, y1, x2, y2), scal(x, y, x1, y1, x2, y2)));
	}
	
	static double scal(double x, double y, double x1, double y1, double x2, double y2) {
		return (x1 - x) * (x2 - x) + (y1 - y) * (y2 - y);
	}
	
	static double vect(double x, double y, double x1, double y1, double x2, double y2) {
		return (x1 - x) * (y2 - y) - (x2 - x) * (y1 - y);
	}
}