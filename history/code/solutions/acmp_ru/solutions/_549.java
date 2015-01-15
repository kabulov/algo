import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int p = in.nextInt();
		int q = in.nextInt();
		
		BigInteger den = BigInteger.ONE;
		
		{
			BigInteger tmp = BigInteger.ONE;
			for (int i = 2; i < q; ++i) {
				tmp = tmp.add(BigInteger.ONE);
				den = den.multiply(tmp);
			}
		}
		
		BigInteger num = den.multiply(new BigInteger(new Integer(p).toString()));
		den = den.multiply(new BigInteger(new Integer(q).toString()));
		
		int len = 0;
		int[] v = new int[q];
		
		{
			BigInteger tmp = BigInteger.ONE;
			for (; !num.equals(BigInteger.ZERO); ) {
				tmp = tmp.add(BigInteger.ONE);
				den = den.divide(tmp);
				v[len++] = Integer.parseInt(num.divide(den).toString()); //either for cycle 
				num = num.mod(den);			
			}
		}
		
		out.println(len + 1);
		for (int i = 0; i < len; ++i) 
			out.println(v[i]);
		
		out.close();
	}
}