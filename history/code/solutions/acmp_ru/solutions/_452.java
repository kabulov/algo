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
		
		String fst = in.next();
		String scd = in.next();
		
		BigInteger[] fib = new BigInteger[256];
		fib[0] = fib[1] = BigInteger.ONE;
		
		for (int i = 2; i < 256; i++)
			fib[i] = fib[i - 1].add(fib[i - 2]);
		
		BigInteger f = BigInteger.ZERO;
		BigInteger s = BigInteger.ZERO;
		
		int len = fst.length();
		for (int i = 1; i <= len; i++)
			if (fst.charAt(len - i) == '1')
				f = f.add(fib[i]);
		
		len = scd.length();
		for (int i = 1; i <= len; i++)
			if (scd.charAt(len - i) == '1')
				s = s.add(fib[i]);
		
		BigInteger sum = f.add(s);
		
		if (sum.equals(BigInteger.ZERO)) 
			out.println(0);
		else {
			StringBuffer answer = new StringBuffer();
			for (int i = 0; i < 256; i++)
				answer.append('0');
			
			BigInteger ss = BigInteger.ZERO;
			BigInteger tmp;
			for (int i = 255; i > 0; i--) {
				tmp = ss.add(fib[i]);
				if (tmp.compareTo(sum) <= 0) {
					answer.setCharAt(256 - i, '1');
					ss = tmp;
				}
			}
			
			int i = 1;
			while (answer.charAt(i) != '1')
				i++;
			
			for (; i < 256; i++)
				out.print(answer.charAt(i));
		}
		
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
