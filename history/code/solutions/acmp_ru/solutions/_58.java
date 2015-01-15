import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.io.StreamTokenizer;
//import java.util.Formatter;
//import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int k = in.nextInt();
		int[][] matrix = new int[100][100];
		
		int n, m;
		for (int loop = 0; loop < k; loop++) {
			n = in.nextInt();
			m = in.nextInt();
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					matrix[i][j] = in.nextInt();
			
			boolean bool = true;
			if (m == 1 || n == 1) {
				out.println("YES");
			} else {
				int tmp;
outer:		  	for (int i = 0; i < n - 1; i++)
					for (int j = 0; j < m - 1; j++) {
						tmp = matrix[i][j] + matrix[i + 1][j] + matrix[i][j + 1] + matrix[i + 1][j + 1];
						if (tmp == 0 || tmp == 4) {
							bool = false;
							break outer;
						}
					}
						
				if (bool)
					out.println("YES");
				else
					out.println("NO");
			}
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
