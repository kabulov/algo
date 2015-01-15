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

		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			String str = in.readLine();
			if (good(str))
				out.println("YES");
			else
				out.println("NO");
		}
		
		out.close();
	}
	
	static boolean good(String s) {
		if (s.charAt(0) != '0')
			return false;
		int len = s.length();
		int i = 0;
		while (i < len && s.charAt(i) == '0')
			i++;
		if (i == len)
			return false;
		int amt = i;
		if (s.charAt(i) != '1')
			return false;
		int pos = i;
		while (i < len && s.charAt(i) == '1')
			i++;
		if (i == len)
			return false;
		if (amt != i - pos)
			return false;
		if (s.charAt(i) != '2')
			return false;
		pos = i;
		while (i < len && s.charAt(i) == '2')
			i++;
		if (i < len)
			return false;
		if (i - pos != amt)
			return false;
		return true;
	}
}
