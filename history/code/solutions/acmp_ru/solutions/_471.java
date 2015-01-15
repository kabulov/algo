import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		Scanner in = new Scanner(new File("input.txt"));
		
		int n = in.nextInt();
		BigInteger[] fst, scd, tmp;
		scd = new BigInteger[10];
		fst = new BigInteger[10];
		fst[0] = BigInteger.ONE;
		fst[1] = BigInteger.ONE;
		fst[2] = BigInteger.ONE;
		fst[3] = BigInteger.ONE;
		fst[4] = BigInteger.ONE;
		fst[5] = BigInteger.ONE;
		fst[6] = BigInteger.ONE;
		fst[7] = BigInteger.ZERO;
		fst[8] = BigInteger.ONE;
		fst[9] = BigInteger.ZERO;
		
		for (int i = 2; i <= n; i++) {
			tmp = fst;
			fst = scd;
			scd = tmp;
			
			fst[0] = scd[7].add(scd[5]);
			fst[1] = scd[6].add(scd[8]);
			fst[2] = scd[3].add(scd[7]);
			fst[3] = scd[9].add(scd[8]).add(scd[2]);
			fst[4] = BigInteger.ZERO;
			fst[5] = scd[9].add(scd[6]).add(scd[0]);
			fst[6] = scd[5].add(scd[1]);
			fst[7] = scd[0].add(scd[2]);
			fst[8] = scd[3].add(scd[1]);
			fst[9] = scd[3].add(scd[5]);
		}
		
out.println(fst[0].add(fst[1]).add(fst[2]).add(fst[3]).add(fst[4]).add(fst[5]).add(fst[6]).add(fst[7]).add(fst[8]).add(fst[9]));
		
		out.close();
	}
}
