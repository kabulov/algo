import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int x1, y1, x2, y2, x3, y3;
		x1 = in.nextInt();
		y1 = in.nextInt();
		x2 = in.nextInt();
		y2 = in.nextInt();
		x3 = in.nextInt();
		y3 = in.nextInt();
		
		long answer = gcd(Math.abs(x1 - x2), Math.abs(y1 - y2));
		answer += gcd(Math.abs(x1 - x3), Math.abs(y1 - y3));
		answer += gcd(Math.abs(x2 - x3), Math.abs(y2 - y3));
		
		out.println(answer);
		out.close();
	}
	
	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
