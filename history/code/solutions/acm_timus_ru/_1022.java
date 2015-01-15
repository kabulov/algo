
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		g = new ArrayList[n];
		for (int i = 0; i < n; ++i) g[i] = new ArrayList<Integer>();
		
		for (int i = 0, t; i < n; ++i) {
			while (true) {
				t = in.nextInt() - 1;
				if (t == -1) break;
				g[i].add(t);
			}
		}
		
		ans = new ArrayList<Integer>();
		used = new boolean[n];
		Arrays.fill(used, false);
		for (int i = 0; i < n; ++i)
			if (!used[i]) 
				dfs(i);
		
		for (int i = n - 1; i >= 0; --i)
			out.print(ans.get(i) + " ");
	}
	
	void dfs(int v) {
		used[v] = true;
		for (int i = 0, sz = g[v].size(), to; i < sz; ++i) {
			to = g[v].get(i);
			if (!used[to])
				dfs(to);
		}
		ans.add(v + 1);
	}
	
	boolean[] used;
	
	ArrayList<Integer> ans;
	ArrayList<Integer> g[];
	int n;
}
