import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long x1, y1, x2, y2;
		
		x1 = in.nextInt();
		y1 = in.nextInt();
		x2 = in.nextInt();
		y2 = in.nextInt();
		
		if (x1 * x1 + y1 * y1 < x2 * x2 + y2 * y2) {
			long tmp = x1;
			x1 = x2;
			x2 = tmp;
			
			tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		
		long ans = 0;
		if (x1 * y2 == x2 * y1) {
			if (x1 * x2 + y1 * y2 <= 0) {
				//sqrt must return proper value otherwise WA
				ans = calc(x1, y1);
				if (x1 * x2 + y1 * y2 < 0)
					ans += calc(x2, y2); ///
			} else {
				ans = calc(x1, y1) - calc(x2, y2);
				if (integer(x2, y2))
					++ans;
			}
		} else {
			long x, y, xn, yn;
			x = -y1 + y2;
			y = x1 - x2;
			
			long a, b;
			a = x * y1 - x1 * y;
			b = x * y2 - x2 * y;
			
			if (a * b >= 0L) {
//				if (x * x1 + y * y1 < 0) {
//					x = -x;
//					y = -y;
//				}

//				double xd = x;
//				double yd = y;

//				double dist = Math.abs(x1 * y2 - x2 * y1);
//				double len =  Math.sqrt(xd * xd + yd * yd);

//				xd = xd * dist / len;
//				yd = yd * dist / len;
				
				ans = calc(x1, y1) - calc(x2, y2);
				if (integer(x2, y2))
					++ans;
			} else {
				if (x * x1 + y * y1 < 0) {
					x = -x;
					y = -y;
				}

				double xd = x;
				double yd = y;

				double dist = Math.abs(x1 * y2 - x2 * y1) / Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
				double len =  Math.sqrt(x * x + y * y);

				xd = xd * dist / len ;
				yd = yd * dist / len;
				
				ans = calc(x1, y1) - calcul(xd, yd) + calc(x2, y2) - calcul(xd, yd);
				if (intgr(xd, yd))
					++ans;
			} //else {//a * b == 0
				//b == 0
//				ans = calc(x1, y1) - calc(x2, y2);
//				if (integer(x2, y2))
//					++ans;
//			}
		}
		
		out.println(ans);
		out.close();
	}
	
	static boolean intgr(double a, double b) {
		double len = a * a + b * b;
		long tmp = calcul(a, b);
		
		if (Math.abs(tmp * tmp - len) < 1e-14) {
			//here is the problem!!!!!!!!!!!!!!!!!!!
			return true;
		}
		
		return false;
	}
	
	static long calcul(double x, double y) {
		long tmp = (int)Math.sqrt(x * x + y * y);
		if ((tmp + 1) * (tmp + 1) <= x * x + y * y)
			++tmp;
		if (tmp * tmp > x * x + y * y)
			--tmp;
		return tmp;
	}
	
	static boolean integer(long a, long b) {
		long len = a * a + b * b;
		long tmp = calc(a, b);

		if (tmp * tmp == len)
			return true;
		
		return false;
	}
	
	static long calc(long a, long b) {
		long tmp = (long)Math.sqrt(a * a + b * b);
		if ((tmp + 1) * (tmp + 1) <= a * a + b * b)
			++tmp;
		if (tmp * tmp > a * a + b * b)
			--tmp;
		return tmp;
	}
}