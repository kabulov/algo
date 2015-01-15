import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int k = in.nextInt();
		
		long n, m;
		for (int i = 0; i < k; i++) {
			n = in.nextLong();
			m = in.nextLong();
			
			out.println(19 * m + (n + 239) * (n + 366) / 2);
		}
		
		out.close();
	}
}
