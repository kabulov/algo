import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextLong();
		
		out.println(solve(n));
		
		out.close();
	}
	
	static long solve(long n) {
		if (prime(n))
			return n;
		if (n < 10)
			return 0;
		return solve(sum(n));
	}
	
	static boolean prime(long num) {
		if (num == 1)
			return false;
		
		for (long div = 2; div * div <= num; ++div)
			if (num % div == 0)
				return false;
		
		return true;
	}
	
	static long sum(long num) {
		int ans = 0;
		while (num > 0) {
			ans += num % 10;
			num /= 10;
		}
		return ans;
	}
}
