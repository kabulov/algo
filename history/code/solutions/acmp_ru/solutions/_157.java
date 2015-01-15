import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.next();
		
		int[] s = new int[26];
		Arrays.fill(s, 0);
		
		long ans = 1;

		for (int i = 0; i < str.length(); ++i) {
			++s[str.charAt(i) - 'a'];
			ans *= i + 1;
		}
		
		for (int i = 0; i < 26; ++i)
			if (s[i] > 0) {
				long mid = 1;
				for (int j = 2; j <= s[i]; ++j) mid *= j;
				ans /= mid;
			}
		
		out.println(ans);
		out.close();
	}	
}
