
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt(), nn = 2 * n;
		
		int[] lp = new int[nn + 1];
		Arrays.fill(lp, 0);

		int[] pr = new int[100000];
		int prsize = 0;
		
		for (int i = 2; i <= nn; ++i) {
			if (lp[i] == 0) {
				lp[i] = i;
				pr[prsize++] = i;
			}
			for (int j = 0, t; j < prsize && pr[j] <= lp[i] && (t = i * pr[j]) <= nn; ++j)
				lp[t] = pr[j];
		}
		
		if (n % 2 == 1) --n;
		for (int r; n > 0;) {
			for (r = n + 1; lp[r] != r; ++r);
			for (int i = r - n; i <= r / 2; ++i)
				out.println(i + " " + (r - i));
			n = r - n - 1;
		}
		
		out.close();
	}
}

/*

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		if (n % 2 == 1) --n;
		for (int r; n > 0;) {
			r = nearestPrime(n + 1);
			for (int i = r - n; i <= r / 2; ++i)
				out.println(i + " " + (r - i));
			n = r - n - 1;
		}
		
		out.close();
	}
	
	static int nearestPrime(int n) {
		while (!isPrime(n)) ++n;
		return n;
	}
	
	static boolean isPrime(int n) {
		for (int d = 2; d * d <= n; ++d)
			if (n % d == 0)
				return false;
		
		return true;
	}
}
*/