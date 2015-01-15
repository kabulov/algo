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

		char[] fst = in.readLine().toCharArray();
		char[] scd = in.readLine().toCharArray();
		
		int f = 0, s = 0;
		while (f < fst.length) {
			if (s == scd.length)
				break;
			if (fst[f] == scd[s]) {
				f++;
			}
			s++;
		}

		if (f == fst.length)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
}
