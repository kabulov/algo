

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

//	BufferedReader in;
	Scanner in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new Scanner(new File("input.txt"));
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
	
	int[] num;
	int pos;
	
	long[][] cnk;
	
	void solve() throws IOException {
		int n = in.nextInt(), k = in.nextInt();
		pos = 0;
		num = new int[100];
		while (n > 0) {
			++pos;
			num[pos] = n % 2;
			n /= 2;
		}
		
		cnk = new long[35][35];
		for (int i = 0; i <= 30; ++i)
			for (int j = 0; j <= 30; ++j) {
				if (j > i) 
					cnk[i][j] = 0;
				else
				if (j == 0 || i == j)
					cnk[i][j] = 1;
				else
					cnk[i][j] = cnk[i - 1][j] + cnk[i - 1][j - 1];
			}
		
		if (k >= 30) {//>
			out.println(0);
			return;
		}
		
		long answer = 0;
//		for (int i = 1; i < pos; ++i)
//			if (num[i] == 0)
//				++answer;
//		
//		if (answer == k)
//			answer = 1;
//		else
//			answer = 0;
		
		for (int i = k + 1; i < pos; ++i) answer += cnk[i - 1][k];
		if (pos - 1 == k) ++answer;
		
		--pos;
		for (; pos > 0 && k >= 0; --pos) {
			if (num[pos] == 0) {
				if (k == 0) break; //k == 0
				--k;
				continue;
			}			
			if (k > 0 && pos - 1 > k - 1) answer += cnk[pos - 1][k - 1];
			if (pos - 1 == k) ++answer;
		}
		
		out.println(answer);
	}
}
