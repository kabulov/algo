
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
		BigInteger num = in.nextBigInteger().divide(new BigInteger(new Integer(5).toString()));
		int k = in.nextInt();
		
		if (num.equals(BigInteger.ZERO)) {
			out.println(1); //!!
			return;
		}
		
		if (k == 0) {
			out.println(1);
			return;
		}
		
		BigInteger den = BigInteger.ZERO, a = BigInteger.ONE , b = BigInteger.ONE;
		for (int i = 1; i <= k; ++i) {
			num = num.add(BigInteger.ONE);
			den = den.add(BigInteger.ONE);
			a = a.multiply(num);
			b = b.multiply(den);
		}
		
		out.println(a.divide(b));
	}
}