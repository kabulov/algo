import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while (true) {
			long k = in.nextLong();
			
			if (k == 0) break;
			
			if (k == 1) {
				out.println("0/1");
				continue;
			}
			
			long s = 1, t = 0, p;
			while (s + (p = phi(t + 1)) < k) {
				s += p;
				++t;
			}
			
			++t;
			for (p = 1; s < k; ++p)
				if (gcd(p, t) == 1)
					++s;
			
			out.println(--p + "/" + t);
		}
		
		out.close();
	}
	
	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	static long phi(long n) {
		long ans = n, i;
		for (i = 2; i * i <= n; ++i)
			if (n % i == 0) {
				while (n % i == 0) n /= i;
				ans -= ans / i;
			}
		if (n > 1) ans -= ans / n;
		return ans;
	}
}
