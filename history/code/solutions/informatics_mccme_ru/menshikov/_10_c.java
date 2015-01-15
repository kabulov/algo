import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new  PrintWriter("output.txt");

		n = in.nextInt();
		k = in.nextInt();
		
		char[] inp = new char[n + 1];
		System.arraycopy(in.next().toCharArray(), 0, inp, 0, n);
		inp[n] = 'X';
		
		amt = new int[41];
		Arrays.fill(amt, 0);
		
		int len = max = 0;
		for (int i = 0; i <= n; i++) 
			if (inp[i] == 'X') {
				if (len >= k) {
					++amt[len];
					if (len > max) max = len;
				}
				len = 0;
			} else
				++len;
		
		if (max < k) {
			out.println(0);
			out.close();
			return;
		}

		if (k == 1) {
			int s = 0;
			for (int i = 1; i <= max; i++) s += amt[i] * i;
			out.println(2 - (s % 2));
			out.close();
			return;
		}

		map = new HashMap<Long, Boolean>();
		map.put(0L, false);
		
		out.println((solve() ? 1 : 2));
		out.close();
	}
	
	static boolean solve() {
		boolean ans = false;
		long hs;
		
		for (int p = k; p <= max; p++) 
			if (amt[p] != 0) {
				--amt[p];
				for (int lt = 0, rt = p - k; lt <= rt; ++lt, --rt) {
					if (lt >= k) ++amt[lt];
					if (rt >= k) ++amt[rt];
					if (!map.containsKey(hs = hash())) map.put(hs, solve());
					if (lt >= k) --amt[lt];
					if (rt >= k) --amt[rt];
					if (ans |= !map.get(hs)) break;
				}
				++amt[p];
			}
		
		return ans;
	}

	static long hash() {
		long h = 0;
		for (int i = k; i <= max; i++)
			if (amt[i] != 0)
				h = (h * 10000L + i * 100L + amt[i]);
		return h;
	}
	
	static HashMap<Long, Boolean> map;
	
	static int max;
	static int n, k;
	static int[] amt;
}