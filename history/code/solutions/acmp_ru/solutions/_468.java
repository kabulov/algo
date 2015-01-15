
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
		long n;
		n = in.nextLong();
		
		int pos = 2;
		long sum = 0, cur;
		while (true) {
			cur = pos * (pos - 1) / 2;
			if (sum + cur < n) {
				sum += cur;
				pos++;
				continue;
			}
			break;
		}
		
		BigInteger answer = BigInteger.ONE.add(BigInteger.ONE).pow(pos);
		
		n -= sum;
		sum = 0;
		pos = 2;
		while (true) {
			cur = pos - 1;
			if (sum + cur < n) {
				sum += cur;
				pos++;
				continue;
			}
			break;
		}
		
		answer = answer.add(BigInteger.ONE.add(BigInteger.ONE).pow(pos - 1));
				
		n -= sum;
		pos = 1;
		while (pos < n) ++pos;
			
		answer = answer.add(BigInteger.ONE.add(BigInteger.ONE).pow(pos - 1));
		
		out.println(answer);
	}
}