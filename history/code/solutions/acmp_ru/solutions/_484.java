
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
	
	int[] sum, fib;
	
	void solve() throws IOException {		
		int n = Integer.parseInt(in.readLine());
		if (n < 4) {
			if (n > 1) out.print(n + " ");
			out.print("1");
			return;
		}
		
		sum = new int[100];
		fib = new int[100];
		fib[0] = 1;
		fib[1] = 1;
		fib[2] = 2;
		sum[1] = 1;
		sum[2] = 3;
		
		int i = 2;
		do {
			++i;
			fib[i] = fib[i - 1] + fib[i - 2];
			sum[i] = sum[i - 1] + fib[i];
		} while (sum[i] < n);
		
		while (i > 2) {
			out.print(n + " ");
			n = pred(n, i);
			--i;
		}
		
		out.print(n + " 1");
	}
	
	int pred(int n, int i) {
		int p = sum[i - 2];
		n -= sum[i - 1];
		while (i > 2) {//n > 3
			if (n > fib[i - 1]) {
				n -= fib[i - 1];
				p += fib[i - 2];
				i -= 2;
			} else {
				--i;
			}
		}
		return p + 1;
	}
}