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
		int k = in.nextInt();
		
		out.println(C(n, k) * subFactorial(n - k));		
		out.close();
	}
	
	static int C(int n, int k) {
		int cnk = 1;
		for (int i = 2; i <= n; ++i) cnk *= i;
		for (int i = 2; i <= k; ++i) cnk /= i;
		for (int i = 2; i <= n - k; ++i) cnk /= i;
		return cnk;
	}
	
	static int subFactorial(int n) {
		int[] f = new int[n + 1];
		f[0] = 1;
		if (n > 0) f[1] = 0;
		for (int i = 2; i <= n; ++i) f[i] = (i - 1) * (f[i - 1] + f[i - 2]);
		return f[n];
	}
}