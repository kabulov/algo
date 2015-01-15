import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int c = in.nextInt();
		int k = in.nextInt();
		
		ten = new int[9];
		ten[0] = 1;
		for (int i = 1; i < 9; i++)
			ten[i] = ten[i - 1] * 10;
		
		mod = 1;
		for (int i = 1; i <= k; i++)
			mod *= 10L;
		
		k = 0;
		int tmp = c;
		while (tmp > 0) {
			++k;
			tmp /= 10;
		}
		
		char[] v = in.next().toCharArray();
		long[] map = new long[n + 1];
		map[0] = 1;
		
		for (int i = 1; i <= n; i++) {
			tmp = v[i - 1] - '0';
			if (tmp <= c)
				map[i] = map[i - 1];
			else {
				out.println(0);
				out.close();
				return;
			}
			
			for (int j = 1; j < k && i > j; j++) {
				tmp = (v[i - j - 1] - '0') * ten[j] + tmp;
				if (v[i - j - 1] == '0')
					continue;
			
				if (tmp > c)
					break;
				
				map[i] = (map[i] + map[i - j - 1]) % mod;
			}
		}
		
		out.println(map[n]);
		out.close();
	}
	
	static long mod;
	static int[] ten;
}