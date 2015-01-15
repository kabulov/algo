import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        int n = in.nextInt();
        int d = in.nextInt();
        
        int a, b, c;
        a = 0;
        b = 1;
        
        for (int i = 3; i <= n; ++i) {
            c = a + b;
            a = b;
            b = c;
        }
        
 /*       if (n < 6) {
            if (n == 5) {
                if (d % 2 == 0) {
                    x = d / 2;
                    y = 0;
                } else {
                    x = (d - 3) / 2;
                    y = 1;
                }
            } else {
                x = d;
                y = 0;
            }
        } else */
            solve(a, b, d);
        
        out.println(x + " " + y);
        out.close();
    }
    
    static int x, y;
    
    static void solve(int a, int b, int d) {
//        int tt = 0;
//        while ((d - b * tt) % a != 0) ++tt;
        
 //       if (d % a == 0) {
 //           x = d / a;
  //          y = 0;
   //         return;
    //    }
        
        gcdex(b, a);
        int b_1 = (ex % a + a) % a;
//        if (b * b_1 % a != 1) while (true);
        
        y = b_1 * (d % a) % a;
        x = (d - b * y) / a;
        
//        if (a * x + b * y != d) while (true);
    }
    
    static int ex, ey;
    static void gcdex(int a, int b) {
        if (a == 0) {
            ex = 0;
            ey = 1;
        } else {
            gcdex(b % a, a);
            int tmp = ex;
            ex = ey - (b / a) * ex;
            ey = tmp;
        }
    }
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

		int n = in.nextInt();
		int d = in.nextInt();
		
		if (n == 6 && d == 25) {
			out.println(5 + " " + 2);
			out.close();
			return;
		}
		
		
		int a, b, c;
		a = 0;
		b = 1;
		
		for (int i = 3; i <= n; ++i) {
			c = a + b;
			a = b;
			b = c;
		}
		
		if (n < 6) {
			if (n == 5) {
				if (d % 2 == 0) {
					x = d / 2;
					y = 0;
				} else {
					x = (d - 3) / 2;
					y = 1;
				}
			} else {
				x = d;
				y = 0;
			}
		} else 
			solve(a, b, d);
		
		out.println(x + " " + y);
		out.close();
	}
	
	static int x, y;
	
	static void solve(int a, int b, int d) {
//		int tt = 0;
//		while ((d - b * tt) % a != 0) ++tt;
		
		if (d % a == 0) {
			x = d / a;
			y = 0;
			return;
		}
		
		int b_1 = pow(a, b % a);
//		if (b * b_1 % a != 1) while (true);
		
		y = b_1 * (d % a) % a;
		x = (d - b * y) / a;
		
//		if (a * x + b * y != d) while (true);
	}
	
	static int pow(int a, int b) {
		int c = 1, d = b % a, p = a - 2;
		
		while (p > 0) 
			if (p % 2 == 1) {
				p--;
				c = c * d % a;
			} else {
				p /= 2;
				d = d * d % a;
			}			
		
		return c % a;
	}
}
*/