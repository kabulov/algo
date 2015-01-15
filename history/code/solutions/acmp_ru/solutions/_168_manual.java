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

		StringBuilder str = new StringBuilder();
		String key = in.readLine();
		for (int i = 1; i <= 10000; i++)
			str.append(Integer.toString(i));
		
		int len = str.length(), klen = key.length();
		for (int i = 0; i < len; i++) {
			boolean matches = true;
			for (int j = 0; j < klen; j++)
				if (str.charAt(i + j) != key.charAt(j)) {
					matches = false;
					break;
				}
			if (matches) {
				out.println(i + 1);
				break;
			}
		}
			
		out.close();
	}
}
