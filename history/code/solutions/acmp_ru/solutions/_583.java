
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
	
	int x, y, state;
	
	int[][] aut = {
			{3, 1},
			{0, 2},
			{1, 3},
			{2, 0}
	};
	
	pair[] horiz, vert;
	int holen, velen;
	
	void solve() throws IOException {		
		int n = Integer.parseInt(in.readLine());
		x = y = state = 0;
		
		horiz = new pair[n + 10];
		vert = new pair[n + 10];		
		holen = velen = 0;
		
		Pattern pat = Pattern.compile(" ");
		String buf;

		for (int iter = 0, step; iter < n; ++iter) {
			buf = in.readLine();
			if (buf.charAt(0) == 'f') {
				step = Integer.parseInt(pat.split(buf)[1]);
				if (state == 0) {
					add(state, new pair(y, 0));					
					add(state, new pair(y + step, 1));
					y += step;
				}
				if (state == 1) {
					add(state, new pair(x, 0));
					add(state, new pair(x + step, 1));
					x += step;
				}
				if (state == 2) {
					add(state, new pair(y - step, 0));
					add(state, new pair(y, 1));
					y -= step;
				}
				if (state == 3) {
					add(state, new pair(x - step, 0));
					add(state, new pair(x, 1));
					x -= step;
				}
			} else {
				state = aut[state][buf.charAt(0) == 'r' ? 1 : 0];
			}
		}
		
		Arrays.sort(horiz, 0, holen);
		Arrays.sort(vert, 0, velen);
		
		out.println(offer(horiz, holen) && offer(vert, velen) ? "TRUE" : "FALSE");
	}
	
	boolean offer(pair[] v, int len) {
		int open = 0;
		for (int i = 0; i < len; ++i) {
			if (v[i].open == 0) {
				++open;
				if (open > 2) return false;
			} else {				
				--open;
			}
		}
		return true;
	}
	
	void add(int state, pair seg) {//addsline
		if (state == 0 || state == 2) {
			vert[velen++] = seg;
		} else {
			horiz[holen++] = seg;
		}
	}
}

class pair implements Comparable<pair>{
	int crd, open;
	pair (int a, int b) {
		crd = a;
		open = b;
	}
	public int compareTo(pair arg) {
		//0 - open, 1 - close
		if (crd == arg.crd) return arg.open - open;		
		return crd - arg.crd;
	}	
}