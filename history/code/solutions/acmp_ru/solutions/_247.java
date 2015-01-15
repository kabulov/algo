import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		if (n == 0) {
			out.println("0");
			out.println("0 0");
			out.close();
			return;
		}
		
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = in.nextInt();
		
		int[][] dp = new int[n][n + 1], used = new int[n][n + 1];
		
		for (int i = 0; i < n; ++i) {
			Arrays.fill(dp[i], -1);
			Arrays.fill(used[i], 0);
		}
		
		int inp = v[0];
		if (inp <= 100) {
			dp[0][0] = inp;
		} else {
			dp[0][1] = inp;
		}
			
		for (int i = 0; i + 1 < n; ++i) {
			inp = v[i + 1];
			for (int j = 0; j <= i + 1; ++j) {
				if (dp[i][j] != -1) {
					if (j == 0) {
						if (inp <= 100) {
							dp[i + 1][0] = dp[i][0] + inp;
							used[i + 1][0] = used[i][0];
						} else {
							dp[i + 1][1] = dp[i][0] + inp;
							used[i + 1][1] = used[i][0];
						}
					} else {//j > 0
						if (inp <= 100) {
							if (dp[i + 1][j] == -1) {
								dp[i + 1][j] = dp[i][j] + inp;
								used[i + 1][j] = used[i][j];
							} else {
								if (dp[i][j] + inp < dp[i + 1][j]) {
									dp[i + 1][j] = dp[i][j] + inp;
									used[i + 1][j] = used[i][j];
								}
							}
							
							if (dp[i + 1][j - 1] == -1) {
								dp[i + 1][j - 1] = dp[i][j];
								used[i + 1][j - 1] = used[i][j] + 1;
							} else {
								if (dp[i][j] < dp[i + 1][j - 1]) {
									dp[i + 1][j - 1] = dp[i][j];
									used[i + 1][j - 1] = used[i][j] + 1;
								}
							}
						} else {
							if (dp[i + 1][j + 1] == -1) {
								dp[i + 1][j + 1] = dp[i][j] + inp;
								used[i + 1][j + 1] = used[i][j];
							} else {
								if (dp[i][j] + inp < dp[i + 1][j + 1]) {
									dp[i + 1][j + 1] = dp[i][j] + inp;
									used[i + 1][j + 1] = used[i][j];
								}
							}
							
							if (dp[i + 1][j - 1] == -1) {
								dp[i + 1][j - 1] = dp[i][j];
								used[i + 1][j - 1] = used[i][j] + 1;
							} else {
								if (dp[i][j] < dp[i + 1][j - 1]) {
									dp[i + 1][j - 1] = dp[i][j];
									used[i + 1][j - 1] = used[i][j] + 1;
								}
							}
						}
					}
				}
			}
		}
		
		int mn = -1;
		
		for (int i = 0; i <= n; ++i) 
			if (dp[n - 1][i] != -1) {
				if (mn == -1)
					mn = i;
				else {
					if (dp[n - 1][i] <= dp[n - 1][mn]) {
						mn = i;
					} 
				}
			}
		
		out.println(dp[n - 1][mn]);
		out.println(mn + " " + used[n - 1][mn]);
		out.close();
	}	
}
