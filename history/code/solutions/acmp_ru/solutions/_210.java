
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	Scanner in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new Scanner(new File("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	void solve() {		
		long n = in.nextLong();
		if (n == 1) {
			out.println(1);
			return;
		}
		
		long[] prime = new long[100], amt = new long[100];
		int len = 0;
		
		{			
			long tmp = n, d = 2;
			while (tmp > 1) {
				if (tmp % d == 0) {
					amt[len] = 0;
					while (tmp % d == 0) {
						amt[len]++;
						tmp /= d;
					}
					prime[len] = d;
					++len;
				} else {
					++d;
					if (d * d > tmp) break; // !!long 
				}
			}
			if (tmp > 1) {
				prime[len] = tmp;
				amt[len] = 1;
				++len;
			}
		}
		
		long answer = 1;
		for (int i = 0; i < len; ++i) answer *= prime[i];
		
		cnt = new long[100];
		long k = 1;

		while (true) {
			if (offer(prime, amt, len, k, answer)) break;
			++k;
		}
		
		out.println(k * answer);
	}
	
	long[] cnt;
	boolean offer(long[] prime, long[] amt, int len, long k, long number) {
		number *= k;
		for (int i = 0; i < len; ++i) {
			cnt[i] = 1;
		}
		
		long d = 2;
		while (k > 1) {
			if (k % d == 0) {
				long amount = 0;
				while (k % d == 0) {
					++amount;
					k /= d;
				}
				for (int i = 0; i < len; ++i) 
					if (d == prime[i]) 
						cnt[i] += amount;
			} else {
				++d;
				if (d * d > k) break;
			}
		}
		if (k > 1) {
			for (int i = 0; i < len; ++i) 
				if (k == prime[i]) {
					++cnt[i];
					//break;
				}
		}
		
		for (int i = 0; i < len; ++i) 
			if (cnt[i] * number < amt[i])
				return false;
		
		return true;
	}
}