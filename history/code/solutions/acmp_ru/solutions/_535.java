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
		
		String f = in.next();
		String s = in.next();
		String t = in.next();
		
		String fst = add(add(f, s), t);
		String scd = add(add(f, t), s);
		String thd = add(add(s, t), f);
		
		if (fst.compareTo(scd) == 0 && scd.compareTo(thd) == 0) {
			out.println("NO");
			out.println(fst);
		} else {
			out.println("YES");
			BigInteger ff = new BigInteger(fst);
			BigInteger ss;
			if (fst.compareTo(scd) == 0) {
				ss = new BigInteger(thd);
				if (ff.compareTo(ss) <= 0) {
					out.println(ff);
					out.println(ss);
				} else {
					out.println(ss);
					out.println(ff);
				}
			} else
			if (fst.compareTo(thd) == 0) {
				ss = new BigInteger(scd);
				if (ff.compareTo(ss) <= 0) {
					out.println(ff);
					out.println(ss);
				} else {
					out.println(ss);
					out.println(ff);
				}
			} else
			if (scd.compareTo(thd) == 0) {
				ss = new BigInteger(scd);
				if (ff.compareTo(ss) <= 0) {
					out.println(ff);
					out.println(ss);
				} else {
					out.println(ss);
					out.println(ff);
				}
			} else {
				ss = new BigInteger(scd);
				BigInteger tt = new BigInteger(thd);
				BigInteger tmp;
				if (ff.compareTo(ss) > 0) {
					tmp = ss;
					ss = ff;
					ff = tmp;
				}
				if (ff.compareTo(tt) > 0) {
					tmp = tt;
					tt = ff;
					ff = tmp;
				}
				if (ss.compareTo(tt) > 0) {
					tmp = ss;
					ss = tt;
					tt = tmp;
				}
				out.println(ff);
				out.println(ss);
				out.println(tt);
			}
		}
		out.close();
	} 
	  
	public static String add(String f, String s) {
		String result = "";
		int len = Math.max(f.length(), s.length());

		while (f.length() < len)
			f = '0' + f;

		while (s.length() < len)
			s = '0' + s;
		
		for (int i = 0; i < len; i++) {
			int fst = (int)f.charAt(i) - (int)'0';
			int scd = (int)s.charAt(i) - (int)'0';
			result = result + (new Integer(fst + scd)).toString();
		}
		
		return result;
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
