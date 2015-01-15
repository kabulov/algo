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
		int[] vect = new int[n + 2];
		
		for (int i = 1; i <= n; i++)
			vect[i] = nextInt();
		
		vect[0] = vect[n];
		vect[n + 1] = vect[1];
		
		int max = 0;
		
		for (int i = 1; i <= n; i++)
			if (vect[i - 1] + vect[i] + vect[i + 1] > max)
				max = vect[i - 1] + vect[i] + vect[i + 1];
		
		out.println(max);
		
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
