import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n, a, b, mx;
		
		n = in.nextInt() - 1;
		a = in.nextInt();
		b = in.nextInt();
		
		mx = Math.max(a, b);
		
		BigInteger[] map = new BigInteger[mx + 1];
		BigInteger num = new BigInteger(Integer.toString(n));
		BigInteger den = BigInteger.ZERO;
		
		map[0] = BigInteger.ONE;
		for (int i = 1; i <= mx; i++) {
			num = num.add(BigInteger.ONE);
			den = den.add(BigInteger.ONE);
			map[i] = map[i - 1].multiply(num).divide(den);
		}
		
		BigInteger ans = BigInteger.ZERO;
		for (int i = 0; i <= a; i++)
			for (int j = 0; j <= b; j++)
				ans = ans.add(map[i].multiply(map[j]));
		
		out.println(ans);
		out.close();
	}
}