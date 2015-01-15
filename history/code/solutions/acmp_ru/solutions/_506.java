

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
import java.math.BigDecimal;
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
	Scanner in;
//	StreamTokenizer in;
	PrintWriter out;
	
	public void run() {
		try {
//			in = new StreamTokenizer(new FileReader("input.txt"));
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
	
//	int next() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
//	
	void solve() throws IOException {
		int n = in.nextInt();
		int k1 = in.nextInt(), k2 = in.nextInt();
		BigInteger sum = in.nextBigInteger();
		
		BigInteger[][] map = new BigInteger[n + 10][n + 10];
		for (int i = 0; i <= n; ++i)
			for (int j = 0; j <= n; ++j)
				map[i][j] = BigInteger.ZERO;		
		map[k1][k2] = BigInteger.ONE;
		
		for (int i = k1; i <= n; ++i)
			for (int j = k2; j <= n; ++j) {
				//if (i == k1 && j == k2) continue;				
				if (i > k1 && j < n) map[i][j] = map[i][j].add(map[i - 1][j]);	//!!
				if (j > k2 && i < n) map[i][j] = map[i][j].add(map[i][j - 1]);	//!!
			}
		
		BigInteger ans = map[n][k2];
		for (int j = k2 + 1; j < n; ++j) {
			ans = ans.add(ans).add(map[n][j]);			
		}
		
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE).pow(n + n - 1 - k1 - k2);
		{
			BigInteger g = ans.gcd(two);
			ans = ans.divide(g);
			two = two.divide(g);		
		}
		ans = ans.multiply(sum).divide(two);
//		BigDecimal fst = new BigDecimal(ans.multiply(sum)).divide(new BigDecimal(two)); 
		out.println(ans + " " + sum.subtract(ans));
//		out.println(fst + " " + new BigDecimal(sum).subtract(fst));
	}
}