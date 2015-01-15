import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int k = in.nextInt();
		
		long a, b, c, d;
		for (int i = 0; i < k; ++i) {
			a = in.nextLong();
			b = in.nextLong();
			c = in.nextLong();
			d = in.nextLong();
			
			boolean ans = false;
	
			while (b > 0) {
				if (a == c && b == d) {
					ans = true;
					break;
				}
				
				if (a < b) {
					long tmp = a;
					a = b;
					b = tmp;
				} else {
					if (c >= d && b == d && a >= c && ((a - c) % b) == 0) {
						ans = true;
						break;
					}
					a %= b;
				}
			}
			
			out.println(ans ? "YES" : "NO");
		}

		out.close();
	}
}
/*			while (b > 0 && a > 0) 
if (a < b) {
	if (c < d && a == c && b == d) { 
		ans = true;
		break;
	}					
	long tmp = a;
	a = b;
	b = tmp;
} else 
if (c < d) {
	a %= b;
} else {//c >= d
	if (b == d && ((a - c) % b) == 0) {
		ans = true;
		break;
	}
	a %= b;
}				
*/			
