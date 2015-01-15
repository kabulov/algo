import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		double a, b, c;
		
		a = in.nextDouble();
		b = in.nextDouble();
		c = in.nextDouble();
		
		double tmp;
		if (a > b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		
		if (a > c) {
			tmp = a;
			a = c;
			c = tmp;
		}
		
		if (b > c) {
			tmp = b;
			b = c;
			c = tmp;
		}
		
		double lt = 0;
		double rt = a;
		
		double fst, scd;
		while (Math.abs(rt - lt) >= eps) {
			fst = (2 * lt + rt) / 3;
			scd = (lt + 2 * rt) / 3;
			if (fst * thd(fst, b, c) < scd * thd(scd, b, c))
				lt = fst;
			else
				rt = scd;
		}
		
		out.println(lt * thd(lt, b, c) / 2);
		out.close();
	}
	
	static double thd(double a, double b, double c) {
		//b can be shortened, but must be maximum possible
		if (a * a + b * b <= c * c)
			return b;
		
		return Math.sqrt(c * c - a * a);
	}
	
	static double eps = 1e-10;
}

/*
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		double a, b, c;
		
		a = in.nextDouble();
		b = in.nextDouble();
		c = in.nextDouble();
		
		double tmp;
		if (a > b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		
		if (a > c) {
			tmp = a;
			a = c;
			c = tmp;
		}
		
		if (b > c) {
			tmp = b;
			b = c;
			c = tmp;
		}
		
		double answer = -1;
		if (Math.abs(a * a + b * b - c * c) < eps) {
			answer = a * b / 2;
		} else {
			double angle = (sqr(a) + sqr(b) - sqr(c)) / (2 * a * b);
			
			if (angle < 0) { //eps
				//obtuse
				angle = (sqr(a) + sqr(c) - sqr(b)) / (2 * a * c);
				double x = a * Math.tan(angle);
				answer = a * x / 2;
				
				angle = (sqr(b) + sqr(c) - sqr(a)) / (2 * b * c);
				x = b * Math.tan(angle);
				answer = Math.max(answer, b * x / 2);
			} else {
				//acute
				double min, max;
				
				angle = (sqr(a) + sqr(c) - sqr(b)) / (2 * a * c);
				min = Math.PI / 2 - angle;
				max = (sqr(b) + sqr(c) - sqr(a)) / (2 * b * c);
				
				double lt, rt;
				while (Math.abs(max - min) >= eps) {
					lt = (2 * min + max) / 3;
					rt = (min + 2 * max) / 3;
					
					if (area(c, lt) < area(c, rt))
						min = lt;
					else
						max = rt;
				}
				
				answer = area(c, (min + max) / 2);

				tmp = b;
				b = a;
				a = tmp;
				
				angle = (sqr(a) + sqr(c) - sqr(b)) / (2 * a * c);
				min = Math.PI / 2 - angle;
				max = (sqr(b) + sqr(c) - sqr(a)) / (2 * b * c);
				
				while (Math.abs(max - min) >= eps) {
					lt = (2 * min + max) / 3;
					rt = (min + 2 * max) / 3;
					
					if (area(c, lt) < area(c, rt))
						min = lt;
					else
						max = rt;
				}
				
				answer = Math.max(answer, area(c, (min + max) / 2));
			}
		}
		
		out.println(answer);
		out.close();
	}
	
	static double area(double side, double angle) {
		return side * side * Math.sin(2 * angle) / 4;
	}
	
	static double sqr(double arg) {
		return arg * arg;
	}
	
	static double eps = 1e-12;
}

*/
