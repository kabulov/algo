

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

	BufferedReader in;
//	Scanner in;
//	StreamTokenizer in;
	PrintWriter out;
	
	public void run() {
		try {
//			in = new StreamTokenizer(new FileReader("input.txt"));
//			in = new Scanner(new File("input.txt"));
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
	
//	int next() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
//	
	void solve() throws IOException {
		int n = Integer.parseInt(in.readLine());
		char[] v = in.readLine().toCharArray();
		int[] b = new int[n + 10];
		Arrays.fill(b, 0);
		
		int s = 0;
		for (int i = 0; i < n; ++i) s += v[i] == '1' ? 1 : 0;
		for (int i = 0; i < n; ++i) {
			if (v[i] == '1') --s;
			if (i > 0 && v[i - 1] == '1') ++s;
			b[i] = s;
		}
		
		for (int i = n - 1; i > 0; --i) {
			b[i - 1] += b[i] / 2;
			b[i] &= 1;
		}
		b[0] &= 1;
		
		s = 0;
		for (int i = n - 1; i >= 0; --i) {
			if (v[i] == '1') ++s;
			s += b[i];
			b[i] = s & 1;
			s >>= 1;
		}
		
		for (int i = 0; i < n; ++i) b[i] ^= 1;
		
		int i = n - 1;
		while (i >= 0 && b[i] == 1) {
			b[i] = 0;
			--i;
		}		
		if (i >= 0) b[i] = 1;
		
		i = 0;
		while (i + 1 < n && b[i] == 0) ++i;
		for (; i < n; ++i) out.print(b[i]);
	}
}