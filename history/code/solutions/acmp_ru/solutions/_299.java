
import static java.lang.Math.abs;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String[] buf = in.nextLine().split("[:]");
		int a = Integer.parseInt(buf[0]), b = Integer.parseInt(buf[1]);

		dp = new long[41][41];
		for (int i = 0; i < 41; ++i) Arrays.fill(dp[i], -1);
		dp[0][0] = 1;
		
		out.println(get(a, b));		
		out.close();
	}
	
	static long get(int i, int j) {
		if (dp[i][j] == -1) {
			dp[i][j] = 0;
			if (good(i - 1, j)) dp[i][j] += get(i - 1, j);
			if (good(i, j - 1)) dp[i][j] += get(i, j - 1);
		}
		return dp[i][j];
	}
	
	static boolean good(int i, int j) {
		if (i < 0 || j < 0) return false;
		return !(abs(i - j) > 1 && (i > 24 || j > 24)); 
	}
	
	static long[][] dp;
}
