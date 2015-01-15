import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		long a = in.nextInt();
		long b = in.nextInt();
		
		int ans = 0;
		for (long i = a; i <= b; ++i) {
			long n = i;
			while (n > 2) {
				if ((n & 1L) == 0L)
					n >>>= 1;
				else
					n = 3 * n + 1;
						
				++ans;
			}
		}
		
		out.println(ans);
		out.close();
	}
}

