import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt(), k = in.nextInt();
		
		long[] prev, cur, buf;
		prev = new long[75];
		cur = new long[75];
		
		Arrays.fill(cur, 0);
		cur[n] = 1;
		
		for (int i = 1; i < k; i++) {
			buf = cur;
			cur = prev;
			prev = buf;
			
			Arrays.fill(cur, 0);
			for (int j = 1; j < n + i; j++) {
				cur[j - 1] += prev[j];
				cur[j + 1] += prev[j];
			}
		}
		
		out.println(cur[1]);
		out.close();
	}
}