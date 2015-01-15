

import static java.lang.Math.min;
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

//	BufferedReader in;
//	Scanner in;
	StreamTokenizer in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new StreamTokenizer(new FileReader("input.txt"));
//			in = new Scanner(new File("input.txt"));
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
	
	int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	int n, au;
	trio[] vect;
	int velen;
	
	void solve() throws IOException {
		n = next();
		au = next();
		
		vect = new trio[n + 5];
		velen = 0;
		
		for (int i = 1; i <= n; ++i) {
			vect[velen++] = new trio(next(), next(), i);
		}
		
		ans = new int[n + 5];
		len = 0;
		
		while (velen > 0) {
			boolean flag = false;
			
			int i;
			for (i = 0; i < velen; ++i)
				if (vect[i].s >= 0 && vect[i].f <= au) {
					flag = true;
					break;
				}
			
			if (!flag) break;
			
			au += vect[i].s;
			ans[len++] = vect[i].p;
			
			--velen;
			vect[i] = vect[velen];
		}

		while (velen > 0) {
			boolean flag = false;
			
			int i;
			for (i = 0; i < velen; ++i) 
				if (vect[i].f > au) {
					flag = true;
					break;
				}
			
			if (!flag) break;
			
			--velen;
			vect[i] = vect[velen];
		}
		
		if (velen > 0) {
			offer();
		}
		
		out.println(len);
		for (int i = 0; i < len; ++i) out.print(ans[i] + " ");
	}
	
	void offer() {
		for (int i = 0; i < velen; ++i) {
			vect[i].s = -vect[i].s;
			vect[i].f = au - vect[i].f + vect[i].s;
		}
		
		Arrays.sort(vect, 0, velen);
		
		trio[] mid = new trio[n + 5];
		int size = 0, time = 0;		
		
		for (int i = 0; i < velen; ++i) {
			mid[size++] = vect[i];
			time += vect[i].s;
			if (time > vect[i].f) {
				int mpos = 0;
				for (int j = 1; j < size; ++j)
					if (mid[j].s > mid[mpos].s)
						mpos = j;
				time -= mid[mpos].s;
				--size;
				for (int j = mpos; j < size; ++j)
					mid[j] = mid[j + 1];
			}
		}
		
		for (int i = 0; i < size; ++i) {
			ans[len++] = mid[i].p;
		}
	}
	
	boolean cmp(trio f, trio s) {		
		if (f.s + s.s <= s.f) return true;
		return false;
	}
	
	int len;
	int[] ans;
}

class trio implements Comparable<trio>{
	int f, s, p;
	trio(int a, int b, int c) {
		f = a;
		s = b;
		p = c;
	}
	@Override
	public int compareTo(trio arg) {
		return f - arg.f;
	}
}