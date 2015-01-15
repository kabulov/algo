import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		w = in.nextInt();
		e = in.nextInt();
		
		a = w - e;
		b = 100 * n;
		c = -100 * n * w;
		
		int ans = 0;
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (haveCommon(100 * i - 100, 100 * j - 100, 100 * i, 100 * j))
					++ans;
		
		out.println(ans);
		out.close();
	}
	
	static boolean haveCommon(long xd, long yd, long xu, long yu) {
		long d1 = a * xd + b * yd + c;
		long d2 = a * xu + b * yu + c;
		long d3 = a * xd + b * yu + c;
		long d4 = a * xu + b * yd + c;
		
		if ((d1 > 0 && d2 > 0 && d3 > 0 && d4 > 0) || (d1 < 0 && d2 < 0 && d3 < 0 && d4 < 0))
			return false;
			
		return true;
	}
	
	static long n, w, e, a, b, c;
}
