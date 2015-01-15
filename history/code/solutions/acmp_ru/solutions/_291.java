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

		int n = Integer.parseInt(in.readLine());
		String[] v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = in.readLine();
		
		String pat = in.readLine();
		int[] map = new int[26];
		
		for (int i = 0; i < 26; i++)
			map[i] = 0;
		
		for (int i = 0; i < pat.length(); i++)
			map[pat.charAt(i) - 'a']++;
		
		int ans = 0;
		int[] tmp = new int[26];
outer:	for (int i = 0; i < n; i++) {
			for (int j = 0; j < 26; j++)
				tmp[j] = 0;
			
			for (int j = 0; j < v[i].length(); j++)
				tmp[v[i].charAt(j) - 'a']++;
			
			for (int j = 0; j < 26; j++)
				if (tmp[j] > map[j])
					continue outer;
					
			++ans;
		}
		
		out.println(ans);
		out.close();
	}
}
