
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
		g = new int[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j) 
				g[i][j] = in.nextInt();
		
		flag = new int[n];
		Arrays.fill(flag, 0);
		
		int up = Math.min(n, 5);
		set = new int[up];
		top = 0;
		
		for (int i = up; i > 0; --i) {
			if (cnk(0, i)) break;
		}

		Arrays.fill(flag, 0);
		for (int j = 0; j < top; ++j) flag[set[j]] = 1;
		for (int j = 0, t = 1; j < n; ++j) 
			if (flag[j] == 0)
				flag[j] = ++t;
		
		out.println(n - top + 1);
		for (int i = 0; i < n; ++i) out.print(flag[i] + " ");
		out.close();
	}	
	
	static boolean offer() {
		for (int i = 0; i + 1 < top; ++i)
			for (int j = i + 1; j < top; ++j)
				if (g[set[i]][set[j]] == 0)
					return false;
		
		return true;
	}
	
	static boolean cnk(int p, int k) {
		if (k == 0) 
			return offer();
		
		for (int i = p; i <= n - k; ++i) {
			flag[i] = 1;
			set[top++] = i;
			if (cnk(i + 1, k - 1)) return true;
			flag[i] = 0;
			--top;
		}
		
		return false;
	}
	
	static int[] flag, set;
	static int[][] g;
	static int n, top;
}
