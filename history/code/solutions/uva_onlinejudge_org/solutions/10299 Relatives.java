import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, i, t;
		while (true) {
			t = n = in.nextInt();
			if (n == 0) break;
			if (n == 1) {
				out.println(0);
				continue;
			}
			for (i = 2; i * i <= n; ++i)
				if (n % i == 0) {
					while (n % i == 0) n /= i;
					t -= t / i;
				}
			if (n > 1) t -= t / n;
			out.println(t);
		}
		
		out.close();
	}
	
}
