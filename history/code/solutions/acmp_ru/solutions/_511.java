//import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
//import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int k = nextInt();
		
		int m = 0;
		
		while (true) {
			if (k == 1) {
				break;
			}
			if (m / 60 == 12) {
				m = -1;
				break;
			}
			m += 5;
			k--;
		}
		
		if (m == -1)
			out.println("NO");
		else
			out.println((m / 60) + " " + (m % 60));
		
		out.close();
	}
	
	static StreamTokenizer in;

	public static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	public static long nextLong() throws IOException {
		in.nextToken();
		return (long)in.nval;
	}
	
//	public static boolean hasNext() throws IOException {
//		in.nextToken();
//		if (in.ttype == StreamTokenizer.TT_EOL || in.ttype == StreamTokenizer.TT_EOF)
//			return false;
//		return true;
//	}
}
