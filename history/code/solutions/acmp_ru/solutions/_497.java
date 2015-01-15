import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt(), k = in.nextInt(), tmpk = k;
		
		int[] val = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
		
		if (k < 2 * n || k > 7 * n) {
			out.println("NO SOLUTION");
			out.close();
			return;
		}
		
		if (n == 1) {	//
			for (n = 0; n < 10; ++n)	
				if (val[n] == k)
					break;
			
			out.println(n + "\n" + n);
			out.close();
			return;
		}
		
		int[][] dp = new int[n][7 * n + 1];
		for (int i = 0; i < n; ++i)
			Arrays.fill(dp[i], 0);
		
		for (int i = 2; i < 8 && i <= k; ++i)
			dp[n - 1][i] = 1;
		
		for (int i = n - 2; i >= 0; --i)
			for (int j = 2 * (n - i); j <= 7 * (n - i); ++j)
				for (int t = 2; t <= 7 && j - t > 1; ++t)
					if (dp[i + 1][j - t] == 1) {
						dp[i][j] = 1;
						break;
					}
		
		int i;
		
		for (i = 1; i < 10; ++i)
			if (k - val[i] > 1 && dp[1][k - val[i]] == 1)
				break;
		
		out.print(i);
		k -= val[i];
		
		for (int j = 1; j + 1 < n; ++j) {
			for (i = 0; i < 10; ++i)
				if (k - val[i] > 1 && dp[j + 1][k - val[i]] == 1)
					break;
			
			out.print(i);
			k -= val[i];
		}
		
		for (i = 0; i < 10; ++i)
			if (val[i] == k)
				break;
		
		out.println(i);
		
		k = tmpk;
		for (i = 9; i > 0; --i)
			if (k - val[i] > 1 && dp[1][k - val[i]] == 1)
				break;
		
		out.print(i);
		k -= val[i];
		
		for (int j = 1; j + 1 < n; ++j) {
			for (i = 9; i >= 0; --i)
				if (k - val[i] > 1 && dp[j + 1][k - val[i]] == 1)
					break;
			
			out.print(i);
			k -= val[i];
		}
			
		for (i = 9; i >= 0; --i)
			if (val[i] == k)
				break;
		
		out.println(i);
		
		out.close();
	}
}