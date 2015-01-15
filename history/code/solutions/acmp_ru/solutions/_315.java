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
		
		String str = in.next();		
		int base = 2;
		
		char c;
		int len = str.length();

		int i = 0;		
		boolean bool = false;
		if (str.charAt(0) == '-') {
			i = 1;
			if (len == 1)
				bool = true;
		}
		
		if (in.hasNext()) {
			bool = true;
		}
		
		if (!bool)
		for (; i < len; i++) {
			c = str.charAt(i);
			if ('0' <= c && c <= '9') {
				base = Math.max(base, (int)c - (int)'0' + (int)1);
			} else 
			if ('A' <= c && c <= 'Z') {
				base = Math.max(base, (int)c - (int)'A' + (int)11);
			} else {
				base = -1;
				break;
			}
		}
		
		if (len == 0 || bool)
			base = -1;
		
		if (str.charAt(0) == '0' && len > 1)
			base = -1;
		
		out.println(base);
		out.close();
	}
	
//	static StreamTokenizer in;

//	public static long nextLong() {
//		return (long)in.nval;
//	}
	
//	public static boolean hasNext() throws IOException {
//		in.nextToken();
//		if (in.ttype == StreamTokenizer.TT_EOL || in.ttype == StreamTokenizer.TT_EOF)
//			return false;
//		return true;
//	}
}
