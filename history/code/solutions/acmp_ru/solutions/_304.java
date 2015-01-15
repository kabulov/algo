
import static java.lang.Math.abs;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String[] buf = in.nextLine().split(" ");
		int amt = Integer.parseInt(buf[0]);
		
		flag = new boolean[41][41];
		dp = new BigInteger[41][41];
		BigInteger answer = BigInteger.ONE;
		
		for (int iter = 1; iter <= amt; ++iter) {
			String[] str = buf[iter].split("[:]");
			int a = Integer.parseInt(str[0]), b = Integer.parseInt(str[1]);
	
			for (int i = 0; i < 41; ++i) 
				for (int j = 0; j < 41; ++j)
					flag[i][j] = false;
			
			flag[0][0] = true;
			dp[0][0] = answer;
			
			answer = iter < 5 ? getfst(a, b) : getscd(a, b);
		}
		
		out.println(answer);
		out.close();
	}
	
	static BigInteger getfst(int i, int j) {
		if (!flag[i][j]) {
			//dp[i][j] = 0; dp[i][j] = something like -1 -> means not filled
			dp[i][j] = BigInteger.ZERO;
			if (goodfst(i - 1, j)) dp[i][j] = dp[i][j].add(getfst(i - 1, j));
			if (goodfst(i, j - 1)) dp[i][j] = dp[i][j].add(getfst(i, j - 1));
			flag[i][j] = true;
		}
		return dp[i][j];
	}
	
	static BigInteger getscd(int i, int j) {
		if (!flag[i][j]) {
			//dp[i][j] = 0; dp[i][j] = something like -1 -> means not filled
			dp[i][j] = BigInteger.ZERO;
			if (goodscd(i - 1, j)) dp[i][j] = dp[i][j].add(getscd(i - 1, j));
			if (goodscd(i, j - 1)) dp[i][j] = dp[i][j].add(getscd(i, j - 1));
			flag[i][j] = true;
		}
		return dp[i][j];
	}

	static boolean goodfst(int i, int j) {
		if (i < 0 || j < 0) return false;
		return !(abs(i - j) > 1 && (i > 24 || j > 24)); 
	}

	static boolean goodscd(int i, int j) {
		if (i < 0 || j < 0) return false;
		return !(abs(i - j) > 1 && (i > 14 || j > 14)); 
	}

	static boolean[][] flag;
	static BigInteger[][] dp;
}
