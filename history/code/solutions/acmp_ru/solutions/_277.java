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
		
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		
		if (a == 0 && b == 0 && c == 0)
			out.println(0);
		else {
			if (a == 0) {
				if (b == 0) {
					if (c < 0) 
						out.print("-");
					
					if (Math.abs(c) != 1)
						out.print(Math.abs(c));
					
					out.print("y");
				} else {
					if (b < 0) 
						out.print("-");
					
					if (Math.abs(b) != 1)
						out.print(Math.abs(b));
					
					out.print("x");
					
					if (c != 0) {
						if (c < 0) 
							out.print("-");
						else 
							out.print("+");
						
						if (Math.abs(c) != 1) 
							out.print(Math.abs(c));
						
						out.print("y");
					}
				}
			} else {
				out.print(a);
				
				if (b == 0) {
					if (c != 0) {
						if (c < 0)
							out.print("-");
						else 
							out.print("+");
						
						if (Math.abs(c) != 1)
							out.print(Math.abs(c));
						
						out.print("y");
					}
				} else {
					if (b < 0)
						out.print("-");
					else
						out.print("+");
					
					if (Math.abs(b) != 1)
						out.print(Math.abs(b));
					
					out.print("x");
					
					if (c != 0) {
						if (c < 0)
							out.print("-");
						else 
							out.print("+");
						
						if (Math.abs(c) != 1)
							out.print(Math.abs(c));
						
						out.print("y");
					}
				}
			}
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
