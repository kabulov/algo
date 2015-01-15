import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		long nok = n / gcd(n, m) * m;
		
		out.println(nok / m);
		out.close();
	}
	
	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
