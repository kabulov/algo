import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		String str = in.next();
		int len = str.length();
		
		int fp = 0;
		int f = 1;
		
		int tmp;
		while (f + fp <= len) {
			tmp = f + fp;
			fp = f;
			f = tmp;
			
			out.print(str.charAt(f - 1));
		}
		
		out.close();
	}

	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}