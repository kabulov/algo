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
		
		int a1 = in.nextInt();
		int b1 = in.nextInt();
		int c1 = in.nextInt();
		int a2 = in.nextInt();
		int b2 = in.nextInt();
		int c2 = in.nextInt();
			
		int loss = 0;
		loss += a1 / 100 * b1 * c1;
		loss += a2 / 100 * b2 * c2;
		
		int d1 = a1 - a1 / 100 * b1;
		int d2 = a2 - a2 / 100 * b2;
		
		if (d1 < d2) {
			loss += (d2 - d1) * c2;
		} else 
		if (d1 > d2) {
			loss += (d1 - d2) * c1;
		}
		
		out.println(loss);
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
