import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.StreamTokenizer;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		int n = in.nextInt();		

		int area = in.nextInt();		
		for (int i = 2; i < n; i++) {
			area += 2 * in.nextInt();
		}		
		area += in.nextInt();
		
		Locale.setDefault(Locale.US);
		Formatter fmt = new Formatter();
		out.println(fmt.format("%.10g", (double)area / (2 * (n - 1))));
		
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
