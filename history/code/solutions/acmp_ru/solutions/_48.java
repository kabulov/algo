import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.readLine();
		int pow = 0, i = str.length();
		
		while (str.charAt(--i) == '0')
			++pow;
		
		out.print(1);
		while (pow-- > 0)
			out.print(0);
		
		out.close();
	}
}
