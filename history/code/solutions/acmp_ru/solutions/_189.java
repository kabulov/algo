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

		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = i + 1;
		
		int s = 1;
		for (int i = 2; i < n; ++i) s *= i;
		
		for (int i = 0, d = n - 1, j; i < n - 1; ++i, --d) {
			j = i;
			
//			j += k / s;
//			k %= s;
			while (k > s) {
				k -= s;
				++j;
			}

			if (j > i) {
				for (int t = j - 1; t >= i; --t) {
					int buf = v[t];
					v[t] = v[t + 1];
					v[t + 1] = buf;
				}
			}
			
			s /= d;
		}
		
		for (int i = 0; i < n; ++i) out.print(v[i] + " ");
		out.close();
	}
}