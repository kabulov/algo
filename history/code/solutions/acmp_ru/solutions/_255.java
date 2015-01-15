import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		int d = 2;
		while (d * d < n) {
			if (n % d == 0) {
				break;
			} else
				d++;
		}
		
		if (d * d > n) {
			out.println(1 + " " + (n - 1));
		} else {
			out.println((n / d) + " " + (n - (n / d)));
		}
		
		out.close();
	}
	
	static int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}