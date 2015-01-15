import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String fst = in.readLine();
		String sub = in.readLine();
		
		int len = fst.length();
		for (int i = 0; i < 26; i++) {
			StringBuilder tmp = new StringBuilder(fst);
			for (int j = 0; j < len; j++)
				tmp.setCharAt(j, (char)(65 + (26 + tmp.charAt(j) - 65 - i) % 26));
			if (tmp.indexOf(sub) >= 0) {
				out.println(tmp);
				out.close();
				return;
			}
		}
		
		out.println("IMPOSSIBLE");
		out.close();
	}
}
