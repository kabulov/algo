import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		long n = in.nextLong();
		
		long fp = 1;
		long f = 1;
		long pos = 2;
		
		long tmp;
		while (f + fp < n) {
			tmp = f + fp;
			fp = f;
			f = tmp;
			pos++;
		}
		
		if (f + fp == n) {
			out.println(1);
			out.println(pos + 1);
		} else
			out.println(0);
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}