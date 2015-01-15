

import static java.lang.Math.atan2;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.abs;
import static java.lang.Math.cbrt;
import static java.lang.Math.min;
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
//	static int next() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
//	static StreamTokenizer in;
//	static Scanner in;
	static BufferedReader in;
	static PrintWriter out;
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		
		String fst = in.readLine().trim();
		String scd = in.readLine().trim();
		
		out.println((offer(fst.replaceAll("[?]", "[A-Z]").replaceAll("[*]", "[A-Z]*"), scd) || offer(scd.replaceAll("[?]", "[A-Z]").replaceAll("[*]", "[A-Z]*"), fst)) ? "YES" : "NO");		
		out.close();
	}
	
	static boolean offer(String a, String b) {
		Pattern pat = Pattern.compile(a);
		Matcher mat = pat.matcher(b);
		return mat.find();
	}
}