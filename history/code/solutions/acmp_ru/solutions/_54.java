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

		int n = in.nextInt();
		int m = in.nextInt();
		
		int[][] matrix = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				matrix[i][j] = in.nextInt();
		
		int lower = matrix[0][0];
		for (int j = 1; j < m; j++)
			if (matrix[0][j] < lower)
				lower = matrix[0][j];
		
		for (int i = 1; i < n; i++) {
			int min = matrix[i][0];
			for (int j = 1; j < m; j++)
				if (matrix[i][j] < min)
					min = matrix[i][j];
			
			if (min > lower) 
				lower = min;
		}
				
		int upper = matrix[0][0];
		for (int i = 1; i < n; i++)
			if (matrix[i][0] > upper)
				upper = matrix[i][0];
		
		for (int j = 1; j < m; j++) {
			int max = matrix[0][j];
			for (int i = 1; i < n; i++)
				if (matrix[i][j] > max)
					max = matrix[i][j];
			
			if (max < upper)
				upper = max;
		}
		
		out.println(lower + " " + upper);
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
