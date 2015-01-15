
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		amt = new int[n];
		map = new int[n][n];
		Arrays.fill(amt, 0);
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) 
				amt[j] += map[i][j] = in.nextInt();
		}
		
		v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = i;
		
		min = Integer.MAX_VALUE;
		best = new int[n];
		
		gen(0);
		
		String table = "ABCDEFGH";
		for (int i = 0; i < n; ++i) out.print(table.charAt(best[i]));
		out.println("\n" + min);		
		
		out.close();
	}	
	
	static int[] best;
	static int min;
	
	static int offer() {
		int ans = 0;
		for (int i = 0; i < n; ++i) ans += amt[v[i]] - map[i][v[i]];
		return ans;
	}
	
	static void gen(int p) {
		if (p == n) {
			int cnt = offer();
			if (cnt < min) {
				min = cnt;
				for (int i = 0; i < n; ++i) best[i] = v[i];
			}
			return;
		}
		
		gen(p + 1);
		for (int i = p + 1, t; i < n; ++i) {
			t = v[p];
			v[p] = v[i];
			v[i] = t;
			gen(p + 1);
			t = v[p];
			v[p] = v[i];
			v[i] = t;
		}
	}
	
	static int[] v;
	
	static int[][] map;
	static int[] amt;
	static int n;
}
