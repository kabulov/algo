import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int k = in.nextInt();
		int n = in.nextInt();
		
		if (k < 3) {
			out.println(n <= 3 ? k : 0);
		} else
		if (n == 1) {
			out.println(k);
		} else {
			if (n == 2) {
				out.println(k + scd(k));
			} else {
				int a, b, c, d;
				b = 0;
				c = k;
				d = scd(k);
				
				for (int i = 3; i <= n; ++i) {
					a = b;
					b = c;
					c = d;
					d = a + b + c + 2 * (2 * (a + b + c) / 5);
				}
				
				out.println(b + c + d);
			}
		}
		
		out.close();
	}
	
	static int scd(int k) {
		switch(k) {
		case 3: case 4:
			return 5;
		case 5:
			return 9;
		case 6: case 7:
			return 10;
		default:
			return k + 2 * ((2 * k) / 5);
		}
	}
}