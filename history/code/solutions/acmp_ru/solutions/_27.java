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

		int w = in.nextInt();
		int h = in.nextInt();
		
		int[][] matrix = new int[h][w];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				matrix[i][j] = 0;
		
		int n = in.nextInt();
		int x1, x2, y1, y2, len;
		for (int k = 0; k < n; k++) {
			x1 = in.nextInt();
			y1 = in.nextInt();
			x2 = in.nextInt();
			y2 = in.nextInt();
			len = x2 - x1;
			if (len > 0)
				for (int i = y1; i < y2; i++)
					matrix[i][x1] = Math.max(matrix[i][x1], len);
		}
		
		int area = w * h;
		for (int i = 0; i < h; i++) {
			len = 0;
			for (int j = 0; j < w; j++) {
				len = Math.max(matrix[i][j], len);
				if (len > 0) {
					area--;
					len--;
				}
			}
		}
		
		out.println(area);
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
