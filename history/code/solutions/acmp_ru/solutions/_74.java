import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int p = in.nextInt();
		
		out.println(solve(n, p));
		out.close();
	}
	
	static int solve(int n, int p) {
		if (n == 1)
			return 1;

		if (p % 2 == 0) 
			return p / 2;

		return n / 2 + solve(n - n / 2, p - p / 2);
	}
}