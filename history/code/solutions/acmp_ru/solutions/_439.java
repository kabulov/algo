import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		double n = in.nextInt();
		double p = in.nextInt();
		
		out.printf(Locale.US, "%.10f", (100 * n) / (100 + p * (n - 1)));
		out.close();
	}
}