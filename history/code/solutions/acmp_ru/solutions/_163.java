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

		int x = 0;
		String str = in.readLine();
		if (str.charAt(1) == '-') {
			if (str.charAt(0) == 'x') {
				x = str.charAt(4) - 48 + (str.charAt(2) - 48);
			} else 
			if (str.charAt(2) == 'x') {
				x = str.charAt(0) - 48 - (str.charAt(4) - 48);
			} else {
				x = str.charAt(0) - 48 - (str.charAt(2) - 48);
			}
		} else {
			if (str.charAt(0) == 'x') {
				x = str.charAt(4) - 48 - (str.charAt(2) - 48);
			} else 
			if (str.charAt(2) == 'x'){
				x = str.charAt(4) - 48 - (str.charAt(0) - 48);
			} else {
				x = str.charAt(0) - 48 + (str.charAt(2) - 48);
			}
		}
		
		out.println(x);
		out.close();
	}
}
