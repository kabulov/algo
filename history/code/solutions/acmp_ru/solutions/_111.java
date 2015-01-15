import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int k = in.nextInt();
		int ans = 0;
		
		if (k >= 3) {
			if (k == 4)
				ans = 3;
			else
			if (k % 2 == 0) {
				k /= 2;
				int mid = div(k);
				if (mid == k)
					ans = k - 1;
				else {
					if (mid == 2) {
						ans = 3;
					} else
						ans = mid;
				}
			} else
				ans = div(k) - 1;
		}
		
		out.println(ans);
		out.close();
	}	
	
	static int div(int n) {
		int d = 2;
		while (d * d <= n) {
			if (n % d == 0) return d;
			++d;
		}
		return n;
	}
}
