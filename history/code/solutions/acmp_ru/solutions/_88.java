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
		int nsqr = n * n;
		int[][] matrix = new int[nsqr][nsqr];
		boolean correct = true;
		
		int tmp;
outer1:	for (int i = 0; i < nsqr; i++)
			for (int j = 0; j < nsqr; j++) {
				tmp = in.nextInt();
				if (tmp < 1 || tmp > nsqr) {
					correct = false;
					break outer1;
				} else {
					matrix[i][j] = tmp - 1;
				}
			}
		
		boolean[] vect = new boolean[nsqr];
		boolean bool;
		
		for (int i = 0; i < nsqr; i++) {
			Arrays.fill(vect, false);
			for (int j = 0; j < nsqr; j++)
				vect[matrix[i][j]] = true;
			
			bool = true;
			for (int j = 0; j < nsqr; j++)
				bool &= vect[j];
			
			if (!bool) {
				correct = false;
				break;
			}
		}
		
		if (correct)
		for (int j = 0; j < nsqr; j++) {
			Arrays.fill(vect, false);
			for (int i = 0; i < nsqr; i++)
				vect[matrix[i][j]] = true;
			
			bool = true;
			for (int i = 0; i < nsqr; i++)
				bool &= vect[i];
			
			if (!bool) {
				correct = false;
				break;
			}
		}
		
		if (correct) 
outer2:	for (int i = 0; i < nsqr; i+= n) {
			for (int j = 0; j < nsqr; j += n) {
				Arrays.fill(vect, false);
				for (int ii = i; ii < i + n; ii++)
					for (int jj = j; jj < j + n; jj++)
						vect[matrix[ii][jj]] = true;
				
				bool = true;
				for (int ii = 0; ii < nsqr; ii++)
					bool &= vect[ii];
				
				if (!bool) {
					correct = false;
					break outer2;
				}
			}
		}
		
		if (correct)
			out.println("Correct");
		else 
			out.println("Incorrect");
		
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
