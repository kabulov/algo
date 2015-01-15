import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		BigInteger m = in.nextBigInteger();
		BigInteger n = in.nextBigInteger();
		
		BigInteger answer = BigInteger.ONE;
		BigInteger k = BigInteger.ONE;
		BigInteger kfact = BigInteger.ONE;
		BigInteger km = BigInteger.ZERO;
		
		BigInteger buf;
		BigInteger tmp;
		while ((km = km.add(m)).compareTo(n) <= 0) {
			kfact = kfact.multiply(k);
			tmp = BigInteger.ONE;
			buf = BigInteger.ONE;
			
			while (buf.compareTo(k) <= 0) {
				tmp = tmp.multiply(n.add(buf).subtract(km)); 
				buf = buf.add(BigInteger.ONE);
			}
			
			answer = answer.add(tmp.divide(kfact));
			k = k.add(BigInteger.ONE);
		}
		
		out.println(answer);
		out.close();
	}
}
