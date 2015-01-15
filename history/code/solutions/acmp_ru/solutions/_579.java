import java.io.File;
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
				
		long n = nextLong();
		long sneg = 0;
		long spos = 0;
		
		long[] vect = new long[(int)n];
		
		for (int i = 0; i < n; i++) {
			vect[i] = nextLong();
			if (vect[i] < 0)
				sneg += -vect[i];
			else
				spos += vect[i];
		}
		
		int len = 0;		
		if (spos >= sneg) {
			for (int i = 0; i < n; i++)
				if (vect[i] >= 0)
					len++;
			
			out.println(len);
			for (int i = 0; i < n; i++)
				if (vect[i] >= 0)
					out.print(i + 1 + " ");
		} else {
			for (int i = 0; i < n; i++)
				if (vect[i] < 0)
					len++;
			
			out.println(len);
			for (int i = 0; i < n; i++)
				if (vect[i] < 0)
					out.print(i + 1 + " ");
		}
		
		out.close();
	}
	
	static StreamTokenizer in;

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
