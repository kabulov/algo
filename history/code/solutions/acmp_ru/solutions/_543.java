import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextLong();
		long w = in.nextLong();
		long d = in.nextLong();
		long s = in.nextLong();
		
		long answer = (w * n * (n - 1) / 2 - s) / d;
		if (answer == 0)
			answer = n;

		out.println(answer);
		out.close();
	}
}