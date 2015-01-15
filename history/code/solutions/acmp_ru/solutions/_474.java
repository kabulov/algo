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

		BigInteger pos = in.nextBigInteger();
		BigInteger three = BigInteger.ONE.add(BigInteger.ONE.add(BigInteger.ONE));
		
		BigInteger nearest = BigInteger.ONE;
		while (nearest.compareTo(pos) < 0)
			nearest = nearest.multiply(three);
		
		nearest = nearest.divide(three);
		boolean bool = false;
		BigInteger tmp;
		BigInteger one = BigInteger.ONE;

		if (pos.compareTo(one) > 0)
		while (true) {
			tmp = nearest.add(nearest);
			if (pos.compareTo(tmp) <= 0) {
				pos = pos.subtract(nearest);
			} else {
				bool = !bool;
				pos = pos.subtract(tmp);				
			}
			
			if (pos.equals(one))
				break;

			nearest = nearest.divide(three);
			while (nearest.compareTo(pos) >= 0)
				nearest = nearest.divide(three);
		}
		
		if (bool) 
			out.println(1);
		else 
			out.println(0);
		
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
