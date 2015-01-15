import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		long p = 2 * in.nextInt();
		long sol = 1;

		for (long n = 2; n * n <= p; ++n)
			if (p % n == 0 && (p / n - n - 1) % 2 == 0) 
				sol = n;
		
		out.println(sol);
		out.close();
	}
}
