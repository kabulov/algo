
import static java.lang.Math.min;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	PrintWriter out;
	Scanner in;
	
	public void run() {
		try {
			in = new Scanner(System.in);	
			out = new PrintWriter(System.out);
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	void solve() throws IOException {
		n = in.nextInt();
		d = in.nextInt();
		a = in.nextInt();
		
		mat = new int[n][n];
		String str;
		
		for (int i = 0; i < n; ++i) {
			str = in.next();
			for (int j = 0; j < n; ++j)
				mat[i][j] = str.charAt(j) == '1' ? 1 : 0;
		}
		
		ans = new int[n][n];
		for (int i = 0; i < n; ++i)
			Arrays.fill(ans[i], 0);
		
		used = new boolean[n];
		Arrays.fill(used, false);
		
		sum = 0;
		for (int i = 0, pcr = -1; i < n; ++i) 
			if (!used[i]) {
				if (pcr != -1) {
					sum += a;
					ans[i][pcr] = ans[pcr][i] = 2;
				}
				pcr = i;
				dfs(i, -1);
			}
		
		out.println(sum);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) out.print(ans[i][j] == 0 ? '0' : (ans[i][j] == 1 ? 'd' : 'a'));
			out.println();
		}
	}
	
	void dfs(int v, int p) {
		used[v] = true;
		for (int j = 0; j < n; ++j)
			if (mat[v][j] == 1) {
				if (j == p) continue;
				if (used[j]) {
					sum += d;
					ans[v][j] = ans[j][v] = 1;
					mat[v][j] = mat[j][v] = 0;
				} else {
					dfs(j, v);
				}
			}				
	}
	
	long sum;
	boolean[] used;	
	int n, d, a;
	int[][] mat, ans;
}
