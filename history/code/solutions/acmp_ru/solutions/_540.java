import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int n, m;
		n = in.nextInt();
		m = in.nextInt();
		
		long r = in.nextLong();
		
		long[] pprv, prv, cur, buf;
		
		pprv = new long[m + 2];
		prv = new long[m + 2];
		cur = new long[m + 2];
		
		Arrays.fill(pprv, 0);
		Arrays.fill(prv, 0);
		Arrays.fill(cur, 0);
		
		for (int i = 1; i <= m; ++i)
			prv[i] = in.nextLong() % r;
		
		for (int i = 1; i <= m; ++i)
			cur[i] = (prv[i - 1] + prv[i] + prv[i + 1]) % r;
		
		if (n >= 3) {
			buf = pprv;
			pprv = prv;
			prv = cur;
			cur = buf;
			
			cur[1] = (prv[1] + 2 * prv[2]) % r;
			cur[m] = (prv[m] + 2 * prv[m - 1]) % r;
			
			for (int j = 2; j < m; ++j) {
				cur[j] = (2 * prv[j - 1] + prv[j] + 2 * prv[j + 1] - pprv[j] + r) % r;
			}
		}
		
		for (int i = 4; i <= n; ++i) {
			buf = pprv;
			pprv = prv;
			prv = cur;
			cur = buf;
			
			cur[1] = (prv[1] + 2 * prv[2]) % r;
			cur[m] = (prv[m] + 2 * prv[m - 1]) % r;
			
			for (int j = 2; j < m; ++j) {
				cur[j] = (2 * prv[j - 1] + prv[j] + 2 * prv[j + 1] - 2 * pprv[j] + 2 * r) % r;
			}			
		}
		
		for (int i = 1; i <= m; ++i) 			
			out.print(cur[i] + " ");
				
		out.close();
	}	
}