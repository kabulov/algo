import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long a = in.nextLong();
		long b = in.nextLong();
		long c = in.nextLong();
		
		if (a == 0 && b == 0 && c == 0) {
			out.println(-1);
			out.close();
			return;
		}
		
		if (a == 0 && b == 0 & c != 0) {
			out.println(0);
			out.close();
			return;
		}
		
		if (a == 0) {
			out.println(1);
			out.printf(Locale.US, "%.5f", (double)-c / (double)b);
			out.close();
			return;
		}
		
		long discriminant = b * b - 4 * a * c;
		if (discriminant < 0) {
			out.println(0);
		} else
		if (discriminant == 0) {
			out.println(1);
			out.printf(Locale.US, "%.5f", (double)-b / (double)(2 * a));
		} else {
			out.println(2);
			double discriminantsqrt = Math.sqrt(discriminant);
			out.printf(Locale.US, "%.5f\n", ((double)-b + discriminantsqrt) / (double)(2 * a));
			out.printf(Locale.US, "%.5f", ((double)-b - discriminantsqrt) / (double)(2 * a));
		}
		
		out.close();
	}
}