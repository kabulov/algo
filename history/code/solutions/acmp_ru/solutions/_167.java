import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		long n = in.nextLong();
		long s = 0;
		
		for (long i = 1; i <= n; ++i) {
			s += sum(i);
			s += (i & 1) == 1 ? sum(i / 2) * 2 : sum(i / 2) * 2 - i / 2 ;
		}
		
		out.println(s);
		out.close();
	}	
	
	static long sum(long n) {
		return n * (n + 1) / 2;
	}
}
