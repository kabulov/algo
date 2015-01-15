
import static java.lang.Math.min;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	PrintWriter out;
	Scanner in;
	
	public void run() {
		try {
			in = new Scanner(System.in);	
			out = new PrintWriter(System.out);
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	void solve() throws IOException {
		int n = in.nextInt();
		String s = in.next();
		String t = in.next();
		char[] tss = (s + "\0" + t + t).toCharArray();		
		int[] z = new int[tss.length];
		
		for (int i = 1, l = 0, r = 0; i <= n + n; ++i) {
			z[i] = (i <= r) ? min(z[i - l], r - i + 1) : 0;
			while (i + z[i] < tss.length && tss[z[i]] == tss[i + z[i]]) ++z[i];
			if (i + z[i] - 1 > r) {
				l = i;
				r = i + z[i] - 1;
			}
		}
		
		for (int i = n + 1; i <= n + n; ++i)
			if (z[i] >= n) {
				out.println(i - n - 1);
				return;
			}
		
		out.println(-1);
	}
}
