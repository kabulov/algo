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
		int k = in.nextInt();
		
		
		BigInteger kk = new BigInteger(new Integer(k).toString());
		BigInteger nn = new BigInteger(new Integer(n).toString());
		
		out.println(nn.pow(n).subtract(kk.multiply(nn.subtract(BigInteger.ONE))));		
		out.close();
	}
}