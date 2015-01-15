import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		Scanner in = new Scanner(new File("input.txt"));
		
		BigInteger fst = BigInteger.ONE;
		BigInteger scd = BigInteger.ONE.add(BigInteger.ONE);
		BigInteger tmp;
		
		int n = in.nextInt();
		for (int i = 2; i <= n; i++) {
			tmp = scd.add(fst);
			fst = scd;
			scd = tmp;
		}
		
		out.println(scd);
		out.close();
	}
}