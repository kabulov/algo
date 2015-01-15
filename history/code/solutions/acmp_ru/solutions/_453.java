import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.math.BigDecimal;
import java.math.BigInteger;
//import java.io.StreamTokenizer;
//import java.util.Formatter;
//import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		String solution = "2";
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
		BigInteger divisor = new BigInteger(two.toString());
		
		int i = 1;
		BigInteger big;
		while (i < n) {
			big = new BigInteger(solution);
			if (big.divide(divisor).mod(two).equals(BigInteger.ONE)) {
				solution = "1" + solution;
			} else {
				solution = "2" + solution;
			}
			divisor = divisor.multiply(two);
			i++;
		}
				
		out.println(solution);
		out.close();
	} 
	  
	static Scanner in;
	static PrintWriter out;
//	static StreamTokenizer in;

//	public static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	public static long nextLong() throws IOException {
//		in.nextToken();
//		return (long)in.nval;
//	}
	
//	public static boolean hasNext() throws IOException {
//		in.nextToken();
//		if (in.ttype == StreamTokenizer.TT_EOL || in.ttype == StreamTokenizer.TT_EOF)
//			return false;
//		return true;
//	}
}
