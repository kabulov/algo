import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		boolean[] prime = new boolean[1000];
		Arrays.fill(prime, true);
		for (int i = 2; i < 998; ++i) {
			if (prime[i])
				for (int j = i; j + i < 1000; j += i)
					prime[j + i] = false;
		}
		
		int[] fst = new int[100];
		int[] scd = new int[100];
		int[] tmp;
		
		Arrays.fill(fst, 0);
		for (int i = 101; i < 1000; ++i)
			if (prime[i])
				++fst[i % 100];
			
		int n = in.nextInt();
		final int cst = (int)1e9 + 9;
		
		int i, j, k, t, b, c;
		for (i = 4; i <= n; ++i) {
			tmp = fst;
			fst = scd;
			scd = tmp;
			
			for (j = 0; j < 100; ++j)
				fst[j] = 0;
			
			for (j = 10; j < 100; ++j) {
				t = (j % 10) * 10;
				c = j * 10;
				for (k = 1; k < 10; ++k) {
					if (prime[c + k]) {
						fst[b = t + k] = (fst[b] + scd[j]) % cst;
					}
				}
			}
		}
		
		int answer = 0;
		for (i = 0; i < 100; ++i)
			answer = (answer + fst[i]) % cst;
		
		out.println(answer);
		out.close();
	}
}
