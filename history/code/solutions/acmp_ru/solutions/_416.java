import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.StreamTokenizer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		String str = in.next();

		int col = (int)str.charAt(0) - (int)'a' + 1;
		int row = (int)str.charAt(1) - (int)'0';
		
		Try(col + 2, row + 1);
		Try(col + 2, row - 1);
		Try(col - 2, row + 1);
		Try(col - 2, row - 1);
		Try(col - 1, row + 2);
		Try(col + 1, row + 2);
		Try(col - 1, row - 2);
		Try(col + 1, row - 2);
		
		out.close();
	}
	
	static PrintWriter out;
	
	public static void prn(int col, int row) {
		out.print((char)((int)'a' + col - 1));
		out.println(row);
	}
	
	public static void Try(int col, int row) {
		if (1 <= col && col <= 8 && 1 <= row && row <= 8)
			prn(col, row);
	}
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
