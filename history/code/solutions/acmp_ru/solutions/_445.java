

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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
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
			Locale.setDefault(Locale.US);
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
	int n, k1, k2;
	double d;
	
	double[] amt;
	
	void solve() throws IOException {
		String parser = in.readLine();
		String[] buffer = parser.split(" ");
		
		n = Integer.parseInt(buffer[0]);
		d = Double.parseDouble(buffer[1]);
		k1 = Integer.parseInt(buffer[2]);
		k2 = Integer.parseInt(buffer[3]);
		
		amt = new double[n + 10];
		Arrays.fill(amt, 0);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0, t = 0; i < n; ++i) {
			parser = in.readLine();
			buffer = parser.split(" ");
			String str = buffer[0].toLowerCase();
			if (!map.containsKey(str)) {
				map.put(str, t);
				++t;				
			}
			amt[map.get(str)] += Double.parseDouble(buffer[1]);
		}
		
		double inf = 1e6 + 1;

		double[] old = new double[n + 10];
		Arrays.fill(old, inf);
		
		in.readLine();
		for (int i = 0, t; i < k1; ++i) {
			parser = in.readLine();
			buffer = parser.split(" ");
			String str = buffer[0].toLowerCase();
			if (!map.containsKey(str)) {
				continue;
			}
			t = map.get(str);
			old[t] = min(old[t], Double.parseDouble(buffer[1]));
		}
		
		double[] nw = new double[n + 10];
		Arrays.fill(nw, inf);
		
		double zero = 1e-10;
		
		in.readLine();
		for (int i = 0, t; i < k2; ++i) {
			parser = in.readLine();
			buffer = parser.split(" ");
			String str = buffer[0].toLowerCase();
			if (!map.containsKey(str)) {
				continue;
			}
			t = map.get(str);
			double p = Double.parseDouble(buffer[1]);
			if (old[t] <= p + zero) continue; //!!
			nw[t] = min(nw[t], p);
		}
		
		double[] ans = new double[n + 10];
		Arrays.fill(ans, 0);
			
		while (d > zero) {
			int pos = -1;
			for (int i = 0; i < n; ++i) {
				if (amt[i] > zero && (pos == -1 || old[i] > old[pos])) 
					pos = i;				
			}
			if (pos == -1) break;
			if (nw[pos] == inf) {
				amt[pos] = 0.0;
				continue;
			}
			double slice = min(amt[pos], d / old[pos]);
			ans[pos] = slice;
			amt[pos] = 0.0; //!!
			d -= slice * old[pos];
		}
		
		for (int i = 0; i < n; ++i) {
			out.printf("%.2f\n", ans[i]);
		}
	}
}