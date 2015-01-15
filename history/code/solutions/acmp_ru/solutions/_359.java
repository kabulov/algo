import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextLong();
/*		
		long s;
		if (n % 2 == 0) {
			s = 0;
		} else {
			s = n;
			--n;
		}
		
		if (n != 0) {
			n /= 2;
			s += 2 * n * n;
		}
*/	
		long s = 2 * (n / 2) * (n / 2) + (n % 2) * n;
		out.println(10 * s / 9 - (s % 9 == 0 ? 1 : 0));
		//(10s - x%10) = 9x ,pri (x%10 != 0)
		
		out.close();
	}
}