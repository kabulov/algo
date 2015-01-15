import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
		BigInteger three = BigInteger.ONE.add(two);
		
		BigInteger answer = new BigInteger(two.toString());
		for (int i = 1; i <= n; i++)
			answer = answer.multiply(three).subtract(two);
		
		out.println(answer);
		out.close();
	}
}