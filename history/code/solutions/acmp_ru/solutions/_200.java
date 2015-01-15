
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
		int n = in.nextInt(), k = in.nextInt();
		int[] prime = new int[1000], amt = new int[1000];
		int len = 0;
		
		{
			int tmp = k, d = 2;
			while (tmp > 1) {
				if (tmp % d == 0) {
					prime[len] = d;
					amt[len] = 0;
					while (tmp % d == 0) {
						++amt[len];
						tmp /= d;
					}
					++len;
				} else {
					++d;
					if (d * d > tmp) break;
				}
			}
			if (tmp > 1) {
				prime[len] = tmp;
				amt[len] = 1;
				++len;
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < len; ++i) {
			answer = Math.min(answer, count(n, prime[i]) / amt[i]);
		}
		out.println(answer);	
	}
	
	int count(int n, int p) {
		int ans = 0;
		while (n > 0) {
			ans += n /= p; 
		}
		return ans;
	}
}