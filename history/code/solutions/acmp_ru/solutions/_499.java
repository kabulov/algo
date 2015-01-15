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

		boolean can = false;
		
		int k = nextInt();
		int w = nextInt();
		
		int a1 = nextInt();
		int b1 = nextInt();
		
		int a2 = nextInt();
		int b2 = nextInt();

		int a3 = nextInt();
		int b3 = nextInt();
		
		//a1, a2, a3, <= W
		//b1, b2, b3, >= K
		
		if (a1 <= w && b1 >= k)
			can = true;
		
		if (a2 <= w && b2 >= k)
			can = true;
		
		if (a3 <= w && b3 >= k)
			can = true;
		
		if (a1 + a2 <= w && b1 + b2 >= k)
			can = true;
		
		if (a1 + a3 <= w && b1 + b3 >= k)
			can = true;

		if (a2 + a3 <= w && b2 + b3 >= k)
			can = true;

		if (a1 + a2 + a3 <= w && b1 + b2 + b3 >= k)
			can = true;
		
		if (can)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
	
	static StreamTokenizer in;

	public static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
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
