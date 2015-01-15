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

		int n = nextInt();
		int[] vect = new int[n];
		
		for (int i = 0; i < n; i++)
			vect[i] = nextInt();
		
		int k = nextInt();
		for (int i = 0; i < k; i++) 
			vect[nextInt() - 1]--;
		
		for (int i = 0; i < n; i++)
			if (vect[i] < 0) {
				out.println("yes");
			} else {
				out.println("no");
			}				
		
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
