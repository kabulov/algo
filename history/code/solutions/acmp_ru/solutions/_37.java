import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		double q = in.nextDouble();
		
		boolean yes = true;
		int a, b, d1, d2;
		for (int i = 0; i < n; i++) {
			a = in.nextInt();
			b = in.nextInt();
			d1 = a * a + b * b;

			a = in.nextInt();
			b = in.nextInt();
			d2 = a * a + b * b;
			
			if (d2 > q * d1) {
				yes = false;
				break;
			}
		}
		
		if (yes)
			out.println("Yes");
		else
			out.println("No");
		
		out.close();
	}
}