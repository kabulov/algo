import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		char[] str = in.readLine().toCharArray();
		char[] sub = in.readLine().toCharArray();
		
		int[] pref = new int[sub.length];
		pref[0] = 0;
		
		for (int i = 1, j = 0; i < sub.length; ++i) {
			while (j > 0 && sub[i] != sub[j]) j = pref[j - 1];
			if (sub[i] == sub[j]) ++j;
			pref[i] = j;
		}
		
		for (int i = 0, j = 0; i < str.length; ++i) {
			while (j > 0 && str[i] != sub[j]) j = pref[j - 1];
			if (str[i] == sub[j]) ++j;
			
			if (j == sub.length) {
				out.print(i - j + 1);
				out.print(" ");
				
				j = pref[j - 1];
			}
		}
		
		out.close();
	}
}