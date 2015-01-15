import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		char[] s = in.readLine().toCharArray();
		if (s.length == 6) {
			if (s[0] + s[1] + s[2] == s[3] + s[4] + s[5])
				out.println("YES");
			else
				out.println("NO");
		} else
			out.println("NO");
		
		out.close();
	}
}
