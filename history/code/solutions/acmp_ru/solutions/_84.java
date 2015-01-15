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
		int m = in.nextInt();
		
		char[][] map = new char[n][m];
		String tmp;
		for (int i = 0; i < n; i++) {
			tmp = in.next();
			for (int j = 0; j < m; j++)
				map[i][j] = tmp.charAt(j);
		}
		
		int hmin = m, hmax = -1;
		int vmin = n, vmax = -1;
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[i][j] == '*') {
					if (i > vmax)
						vmax = i;
					
					if (i < vmin)
						vmin = i;
					
					if (j < hmin)
						hmin = j;
					
					if (j > hmax)
						hmax = j;
				}
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (vmin <= i && i <= vmax && hmin <= j && j <= hmax)
					map[i][j] = '*';
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				out.print(map[i][j]);
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
