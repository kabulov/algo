import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		long mul = 1, sum = 0;
		while (n > 0) {
			mul *= n % k;
			sum += n % k;
			n /= k;
		}
		
		out.println(mul - sum);
		out.close();
	}
}
