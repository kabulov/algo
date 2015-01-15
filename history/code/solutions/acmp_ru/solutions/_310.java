import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int k = in.nextInt();
		int x, y, a;
		boolean can;
		for (int i = 0; i < k; i++) {
			x = in.nextInt();
			y = in.nextInt();
			a = in.nextInt();
			can = false;
			
			if (a == 1) {
				can = true;
			} else
			if (x % a == 0) {
				if ((y - 2) % a == 0)
					can = true;
				if ((x - 2) % a == 0 && y % a == 0)
					can = true;
			} else
			if (x % a == 1) {
				if (y % a == 1) {
					can = true;
				} else
				if (y % a == 0) {
					if ((y - 2) % a == 0)
						can = true;
				}
			} else
			if (x % a == 2 && y % a == 0) {
				can = true;
			}
			
			if (can)
				out.print(1);
			else
				out.print(0);
		}
		
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}