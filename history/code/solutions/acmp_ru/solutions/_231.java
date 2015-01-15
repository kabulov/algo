import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		String str = in.next();
		
		int amt;
		char symb;
		int len = str.length();
		
		String answer = "";
		
		int i = -1;
		while (++i < len) {
			symb = str.charAt(i);
			if ('A' <= symb && symb <= 'Z') {
				amt = 1;
			} else {
				amt = symb - 48;
				symb = str.charAt(++i);
				if ('0' <= symb && symb <= '9') {
					amt = amt * 10 + symb - 48;
					symb = str.charAt(++i);
				}
			}
			for (int j = 0; j < amt; j++)
				answer = answer + symb;
		}
		
		out.print(answer.charAt(0));
		len = answer.length();
		for (i = 2; i <= len; i++) {
			out.print(answer.charAt(i - 1));
			if (i % 40 == 0)
				out.println();
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