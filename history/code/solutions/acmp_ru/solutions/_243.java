

import static java.lang.Math.cbrt;
import static java.lang.Math.min;
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

//	BufferedReader in;
//	Scanner in;
	StreamTokenizer in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new StreamTokenizer(new FileReader("input.txt"));
//			in = new Scanner(new File("input.txt"));
//			in = new BufferedReader(new FileReader("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	int n, L;
	int[] p, q, f, r;
	int[][] dp;
	
	void solve() throws IOException {
		n = next();
		L = next();
		L += L;	//!!
		
		p = new int[n + 10];
		q = new int[n + 10];
		r = new int[n + 10];
		f = new int[n + 10];
		
		for (int i = 1; i <= n; ++i) {
			p[i] = next();
			r[i] = next();
			q[i] = next();
			f[i] = next();
		}
		
		dp = new int[n + 10][L + 10];
		
		//init
		for (int i = 0; i <= n; ++i) dp[i][0] = 0;
		for (int j = 1; j <= L; ++j) dp[0][j] = -1;
		
		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= L; ++j) {
				dp[i][j] = -1;
				for (int k = 0, t; k <= f[i] && k <= j; ++k) {
					t = k * (k < r[i] ? p[i] : q[i]);
					if (dp[i - 1][j - k] != -1) {
						t += dp[i - 1][j - k];
						if (dp[i][j] == -1) {
							dp[i][j] = t;
						} else {
							if (dp[i][j] > t) dp[i][j] = t;
						}
					}
				}
			}
					
		//out
		int ans = -1;
		for (int j = L / 2; j <= L; ++j)
			if (dp[n][j] != -1) {
				if (ans == -1 || dp[n][j] < ans) ans = dp[n][j];
			}
		out.println(ans);
	}
}