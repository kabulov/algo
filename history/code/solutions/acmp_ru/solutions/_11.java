import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int k = in.nextInt();
		int n = in.nextInt();
		
		BigInteger[] vect = new BigInteger[n + 1];
		vect[0] = BigInteger.ONE;
		
		for (int i = 1; i <= n; i++) {
			vect[i] = BigInteger.ZERO;
			for (int j = 1; j <= k && i >= j; j++)
				vect[i] = vect[i].add(vect[i - j]);
		}
		
		out.println(vect[n]);
		out.close();
	}
}
