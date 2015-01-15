import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		if (n % 2 == 1)
			out.println(n / 2 + " " + (n / 2 + 1));
		else {
			if ((n / 2) % 2 == 0)
				out.println((n / 2 - 1) + " " + (n / 2 + 1));
			else
				out.println((n / 2 - 2) + " " + (n / 2 + 2));
		}
		
		out.close();
	}
}