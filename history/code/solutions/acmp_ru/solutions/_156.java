import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = in.nextInt();
		int k = in.nextInt();

		out.println(n < k ? 0 : fact(k) * sqr(cnk(n, k)));
		out.close();
	}
	
	static int sqr(int n) {
		return n * n;
	}
	
	static int fact(int n) {
		int f = 1;
		for (int i = 2; i <= n; ++i) f *= i;
		return f;
	}
	
	static int cnk(int n, int k) {
		return fact(n) / fact(k) / fact(n - k);
	}
}