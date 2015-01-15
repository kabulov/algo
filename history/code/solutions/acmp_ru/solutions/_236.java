import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		expr = in.next().toCharArray();
		pos = 0;
		
		int x = in.nextInt();
		
		koef = new int[5];		
		Arrays.fill(koef, 0);
		
		boolean neg = expr[0] == '-' ? true : false;
		if (expr[0] == '-') ++pos;
		
		getToken(neg);
		while (pos < expr.length) {
			neg = expr[pos] == '-' ? true : false;
			++pos;
			getToken(neg);
		}
		
		long ans = 0, xpow = 1;		
		
		for (int i = 0; i < 5; ++i) { 
			ans += koef[i] * xpow;
			xpow *=  x;
		}
		
		out.println(ans);
		out.close();
	}
	
	static char[] expr;
	static int pos;
	
	static int[] koef;
	
	static void getToken(boolean neg) {
		int kf = 1;
		
		if (expr[pos] != 'x') {
			kf = 0;
			while (pos < expr.length && ('0' <= expr[pos] && expr[pos] <= '9')) {
				kf = kf * 10 + expr[pos] - '0';
				++pos;
			}
			
			if (pos < expr.length && expr[pos] == '*') ++pos;
		}
		
		if (neg) kf = -kf;
		
		if (pos == expr.length || expr[pos] != 'x') {
			koef[0] += kf;
			return;
		}
		
		++pos;
		if (pos == expr.length || expr[pos] != '^') {
			koef[1] += kf;
			return;
		}
		
		++pos;
		koef[expr[pos] - '0'] += kf;
		
		++pos;
	}
}