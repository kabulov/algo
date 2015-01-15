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

		int n = in.nextInt();		
		int[][] map = new int[n][n];
		
		int i, j;
		int counter = 0;
		for (int k = 0; k < 2 * n - 1; k++) {
			if (k % 2 == 0) {
				i = 0; 
				j = k;
				while (j >= 0) {
					if (0 <= i && i < n && 0 <= j && j < n)
						map[i][j] = ++counter;
					j--;
					i++;
				}
			} else {
				i = k;
				j = 0;
				while (i >= 0) {
					if (0 <= i && i < n && 0 <= j && j < n)
						map[i][j] = ++counter;
					j++;
					i--;
				}
			}
		}
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				out.print(map[i][j] + " ");
			
			out.println();
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
