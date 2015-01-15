
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
	
	void solve() throws IOException {		
		int n = Integer.parseInt(in.readLine());
		char[] v = in.readLine().toCharArray();
		
		int aamt, bamt;
		aamt = bamt = 0;
		
		int[] bbig = new int[n + 1], abig = new int[n + 1];
		Arrays.fill(bbig, 0);
		Arrays.fill(abig, 0); 
		
		long answer = 0;
		
		for (int i = 0; i < n; ++i) {
			if (v[i] == 'a') {
				++aamt;
			} else {
				++bamt;
			}
			
			if (aamt == bamt) {
				answer += abig[0] + 1; //bbig[0]
				++abig[0];
			} else
			if (aamt > bamt) {
				answer += abig[aamt - bamt];
				++abig[aamt - bamt];
			} else {//aamt < bamt
				answer += bbig[bamt - aamt];
				++bbig[bamt - aamt];
			}			
		}
	
		out.println(answer);
	}
}