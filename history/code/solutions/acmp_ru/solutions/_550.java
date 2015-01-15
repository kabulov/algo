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
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		if ((n % 400 == 0) || ((n % 4 == 0) && (n % 100 != 0)))
			out.print(12);
		else
			out.print(13);
		
		out.print("/09/");
		
		if (n < 1000)
			out.print("0");
		if (n < 100)
			out.print("0");
		if (n < 10)
			out.print("0");
		
		out.println(n);
		
		out.close();
	}
	
//	static StreamTokenizer in;

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
