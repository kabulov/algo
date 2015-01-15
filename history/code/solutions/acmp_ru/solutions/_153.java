import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		m = in.nextInt();
		
		v = new int[m];
		for (int i = 0; i < m; ++i) v[i] = in.nextInt();
		
		amt = -1;
				
		offer(0, 0, 0);
		
		out.println(amt);
		out.close();
	}	
	
	static void offer(int p, long sum, int cnt) {
		if (sum > n) {
			if (amt == -1)
				amt = 0;
			return;
		}

		if (p == m) {
			if (sum == n) {
				if (amt <= 0 || cnt < amt) 
					amt = cnt;
			}
			return;
		}
		
		offer(p + 1, sum, cnt);
		offer(p + 1, sum + v[p], cnt + 1);
		offer(p + 1, sum + v[p] + v[p], cnt + 2);
	}
	
	static int amt; 
	static int m;
	static long n;
	static int[] v;
}
