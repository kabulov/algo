import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		int max = 0;
		int pay = 0;
		
		while (n > 7) {
			n -= 7;
			pay += 7;
			if (n >= 100) {
				max += 100;
				n -= 100;
			} else {
				max += n;
				n = 0;
			}
		}
		
		out.println(max + " " + pay);
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}