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

		String s1 = in.readLine();
		String s2 = in.readLine();
		
		boolean yes = true;
		if (s1.length() != s2.length())
			yes = false;
		else {
			int[] v1 = new int[26];
			Arrays.fill(v1, 0);
			int[] v2 = new int[26];
			Arrays.fill(v2, 0);
			int len = s1.length();
			for (int i = 0; i < len; i++) {
				v1[s1.charAt(i) - 65]++;
				v2[s2.charAt(i) - 65]++;
			}
			for (int i = 0; i < 26; i++)
				if (v1[i] != v2[i]) {
					yes = false;
					break;
				}
		}
		
		if (yes)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
}
