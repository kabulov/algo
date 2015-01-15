

import static java.lang.Math.min;
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new BufferedReader(new FileReader("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	char[] v;
	int[][] dp;
	int n;
	
	void solve() throws IOException {
		v = in.readLine().toCharArray();
		if (v.length == 0) {
			out.println(0);
			return;
		}
		
		n = v.length;
		dp = new int[n][n];
		for (int i = 0; i < n; ++i) {
			dp[i][i] = 1;		
		}
				
		for (int len = 2, lt, rt; len <= n; ++len) {
			for (lt = 0; lt <= n - len; ++lt) {
				rt = lt + len - 1;				
				offer(lt, rt);
			}
		}
		
		out.println(dp[0][n - 1]);
	}
	
	void offer(int lt, int rt) {
		if (rt - lt == 1) {
			if (proper(v[lt], v[rt])) 
				dp[lt][rt] = 0;
			else
				dp[lt][rt] = 2;
		}
		
		dp[lt][rt] = Integer.MAX_VALUE;
		for (int i = lt + 1; i <= rt; ++i) dp[lt][rt] = min(dp[lt][rt], dp[lt][i - 1] + dp[i][rt]);		
		
		if (opn(v[rt])) {
			dp[lt][rt] = min(dp[lt][rt], dp[lt][rt - 1] + 1);
		} else {
			for (int i = lt; i < rt; ++i) {
				if (proper(v[i], v[rt]))
					dp[lt][rt] = min(dp[lt][rt], get(lt, i - 1) + get(i + 1, rt - 1));
			}
			for (int i = lt; i <= rt; ++i) {
				dp[lt][rt] = min(dp[lt][rt], get(lt, i - 1) + get(i, rt - 1) + 1);
			}
		}
		
		if (clos(v[lt])) {
			dp[lt][rt] = min(dp[lt][rt], dp[lt + 1][rt] + 1);
		} else {
			for (int i = lt + 1; i <= rt; ++i) {
				if (proper(v[lt], v[i]))
					dp[lt][rt] = min(dp[lt][rt], get(lt + 1, i - 1) + get(i + 1, rt));					
			}
			for (int i = lt; i <= rt; ++i) {
				dp[lt][rt] = min(dp[lt][rt], get(lt + 1, i) + get(i + 1, rt) + 1);
			}
		}
	}
	
	int get(int lt, int rt) {
		if (lt > rt) return 0;
		return dp[lt][rt];
	}
	
	boolean clos(char c) {
		return c == ')' || c == ']' || c == '}';
	}
	
	boolean opn(char c) {
		return c == '(' || c == '[' || c == '{';
	}
	
	boolean proper(char open, char close) {
		return open == '(' && close == ')' || open == '[' && close == ']' || open == '{' && close == '}';
	}
}
