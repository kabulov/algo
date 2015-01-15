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

		String tmp = in.readLine();
		String pat = in.readLine();

		int pos;
		
		String str = tmp + tmp;
		if ((pos = str.indexOf(pat)) < 0)
			out.println(-1);
		else {
			if (pat.equals(tmp))
				out.println(0);
			else
				out.println(tmp.length() - pos);
		}
		out.close();
	}
}
