import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();
		int len = str.length();
		int code, p, x;
		char c;
		
		for (int i = 0; i < len ; i++) {
			c = str.charAt(i);
			code = Character.isDigit(c) ? c - 48 : c - 55;
			p = (i + 1) % 27;
			x = code - p;
			if (x < 0)
				x += 27;
			
			if (x == 0)
				out.print(' ');
			else
				out.print((char)('a' + x - 1));
		}
		
		out.close();
	}
}
