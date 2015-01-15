import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt() - 1;
		int x = in.nextInt();
		int y = in.nextInt();
		
		if (n == 0) {
			out.println(Math.min(x, y));
			out.close();
			return;
		}

		int k = (n * y - 1) / (x + y) + 1;
		int p = (n * x - 1) / (x + y) + 1;
		
		if (k * x / y < n - k) //maybe while // not important
			++k;
		if (p * y / x < n - p) //maybe while // not important
			++p;
		
		out.println(Math.min(x, y) + Math.min(k * x, p * y));
		out.close();
	}
}