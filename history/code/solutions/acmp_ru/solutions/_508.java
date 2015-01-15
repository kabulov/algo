import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 
		
		int n = in.nextInt();
		
		long s = 0;
		
		for (int i = 0; i < n; ++i) {
			char c = in.next().charAt(0);
			long mid = s;
			
			long buf = in.nextInt();
			s -= buf;
			buf = in.nextInt();
			s -= buf;				
			mid -= buf;
			
			buf = in.nextInt();
			s += buf;
			mid += buf;
			buf = in.nextInt();
			s += buf;

			if (c == 'L') {
				out.print("-1");
			} else {
				out.print(mid);
			}
			out.print(" ");
		}
		
		out.close();
	}
}