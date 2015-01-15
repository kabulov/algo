import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] argv) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int s = in.nextInt();
		
		int ans = s == 0 ? 2 : 0;
		if (Math.abs(s) == 1)
			++ans;
		
		int tmp = 2 * s + 2, sqrt, buf;
		sqrt = tmp <= 0 ? -1 : (int)Math.sqrt(tmp);
		while ((sqrt + 1) * (sqrt + 1) <= tmp)
			++sqrt; 

		for (int k = 3; k <= n; k++) {
			++tmp;
			if ((sqrt + 1) * (sqrt + 1) == tmp) {
				++sqrt;
				if ((k + sqrt) % 2 == 0) {
					buf = (k + sqrt) / 2;
					if (2 <= buf && buf <= k - 2) {
						++ans;
						continue;
					}					
				}
			}

			if (k * (k - 1) / 2 == s || (k - 1) * (k - 2) / 2 - (k - 1) == s) 
				++ans;
		}
		
		out.println(ans);
		out.close();
	}
}
