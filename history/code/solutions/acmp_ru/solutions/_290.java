import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.io.StreamTokenizer;
//import java.util.Formatter;
//import java.util.Locale;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int nb = in.nextInt();
		int mb = in.nextInt();
		char[][] base = new char[nb][mb];
		
		String str;
		for (int i = 0; i < nb; i++) {
			str = in.next();
			for (int j = 0; j < mb; j++)
				base[i][j] = str.charAt(j);
		}
				
		int nd = in.nextInt();
		int md = in.nextInt();
		char[][] desert = new char[nd][md];
		
		for (int i = 0; i < nd; i++) {
			str = in.next();
			for (int j = 0; j < md; j++)
				desert[i][j] = str.charAt(j);
		}
		
		boolean bool;
		int answer = 0;
		for (int i = 0; i <= nd - nb; i++)
			for (int j = 0; j <= md - mb; j++) {
				bool = true;
outer:			for (int ii = 0; ii < nb; ii++)
					for (int jj = 0; jj < mb; jj++)
						if (base[ii][jj] == '#') {
							if (desert[i + ii][j + jj] != '#') {
								bool = false;
								break outer;
							}
						}
				
				if (bool) 
					answer++;
			}
		
		out.println(answer);
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
