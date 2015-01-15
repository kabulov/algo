import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Locale;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "ISO-8859-1"));
		PrintWriter out = new PrintWriter("output.txt", "ISO-8859-1");

		String[] buf = in.readLine().split(" ");
		int k = Integer.parseInt(buf[0]);
		int n = Integer.parseInt(buf[1]);
		
		String[] v = new String[n];
		for (int i = 0; i < n; i++) {
			v[i] = in.readLine().trim();
			if (v[i].length() > k) {
				out.println("Impossible.");
				out.close();
				return;
			}
		}
		
		for (int i = 0; i < n; i++) {
			int amt = k - v[i].length();
			int fst = amt / 2;
			int lst = amt - fst;
			for (int j = 0; j < fst; j++)
				out.print(" ");
			out.print(v[i]);
			for (int j = 0; j < lst; j++)
				out.print(" ");
			out.println();
		}
		
		out.close();
	}
}
