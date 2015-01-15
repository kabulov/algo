import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		init();
		
		while (true) {
			long k = in.nextLong();
			
			if (k == 0) break;
			
			if (k == 1) {
				out.println("0/1");
				continue;
			}
			
			long s = 1;
			int t = 0, p;
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

	static int[] lp, pr;
	static int prsize;
	
	static void init() {
		lp = new int[1000001];
		pr = new int[100001];
		Arrays.fill(lp, -1);
		prsize = 0;
		
		for (int i = 2; i < 1000001; ++i) {
			if (lp[i] == -1) {
				lp[i] = i;
				pr[prsize++] = i;
			}
			for (int j = 0; j < prsize && pr[j] <= lp[i] && pr[j] * i < 1000001; ++j)
				lp[pr[j] * i] = pr[j];
		}
	}
	
	static int phi(int n) {
		if (n == 1) return 1;
		int ans = n, t = n;		
		while (t > 1) {
			int p = lp[t];
			while (lp[t] == p) t /= p;			
			ans -= ans / p;
		}		
		return ans;
	}
}
