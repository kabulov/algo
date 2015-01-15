import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new BufferedReader(new FileReader("input.txt")); 
		
		int n = Integer.parseInt(in.readLine());
		
		int len = 0;
		long divisor = 2;
		long[] ans = new long[32];
		while (n > 1) {
			if (n % divisor == 0) {
				ans[len++] = divisor;
				n /= divisor;
			} else {
				++divisor;
				if (divisor * divisor > n)
					break;
			}
		}
		
		if (n > 1) {
			ans[len++] = n;
		}
		
		out.print(ans[0]);
		for (int i = 1; i < len; i++) {
			out.print("*" + ans[i]);
		}
		
		out.close();
	}
	
	static BufferedReader in;
	static PrintWriter out;
}