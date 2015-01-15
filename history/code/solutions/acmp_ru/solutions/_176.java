import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		int N, K;
		N = in.nextInt();
		K = in.nextInt();
		
		BigInteger[][] dp = new BigInteger[100][100];

		for (int n = 1; n <= N; ++n) {
			for (int k = 1; k <= n; ++k) {//<=K
				if (k == 1 || k == n) {
					dp[n][k] = BigInteger.ONE;
					continue;
				} 

				dp[n][k] = BigInteger.ZERO;

				for (int p = 1; p <= n; ++p) {
					if (p == 1) {
						dp[n][k] = dp[n][k].add(dp[n - 1][k]);
						continue;
					}	

					if (p == n) {
						dp[n][k] = dp[n][k].add(dp[n - 1][k - 1]);
						continue;
					}
					
					if (p < k && n - p < k) continue;

					if (p >= k && n - p < k) {
						for (int t = 1; t <= n - p; ++t) {
							dp[n][k] = dp[n][k].add(dp[p - 1][k - 1].multiply(dp[n - p][t]));//[p][k]
						}
						continue;
					}

					if (p < k && n - p >= k) {
						for (int t = 1; t <= p - 1; ++t) {
							dp[n][k] = dp[n][k].add(dp[p - 1][t].multiply(dp[n - p][k]));
						}
						continue;
					}

					for (int t = 1; t <= p - 1 && t <= k - 1; ++t) {//p-1
						dp[n][k] = dp[n][k].add(dp[p - 1][t].multiply(dp[n - p][k]));//p-1
					}
					for (int t = 1; t <= n - p && t <= k; ++t) {//<k
						if (t == k) continue;//no continue
						dp[n][k] = dp[n][k].add(dp[p - 1][k - 1].multiply(dp[n - p][t]));
					}
				}
			}
		}

		out.println(dp[N][K]);

		out.close();
	}
	
	static Scanner in;
	static PrintWriter out;
}