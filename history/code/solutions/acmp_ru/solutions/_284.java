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
		int[] vect = new int[n];
		
		for (int i = 0; i < n; i++)
			vect[i] = in.nextInt();
		
		int i, j;
		int k = in.nextInt();		
		for (int loop = 0; loop < k; loop++) {
			i = in.nextInt();
			j = in.nextInt();
			for (int lp = i - 1; lp < j; lp++)
				out.print(vect[lp] + " ");
			
			out.println();
		}
			
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
