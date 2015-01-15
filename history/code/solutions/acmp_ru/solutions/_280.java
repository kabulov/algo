import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long x = in.nextLong();
		if (x == 1) {
			out.println(1);
			out.close();
			return;
		}
		
		int[] p = new int[1000];
		int len = 0;
		
		for (long d = 2; d < 1000; ++d)
			if (x % d == 0) {
				while (x % d == 0) {
					++p[len];
					x /= d;
				}
				++len;				
				if (x == 1) 
					break;
			}
		
		long ans = 1;
		for (int i = 0; i < len; ++i)
			ans *= p[i];
		
		out.println(ans);
		out.close();
	}	
}
