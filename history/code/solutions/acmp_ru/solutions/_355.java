import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new BufferedReader(new FileReader("input.txt")); 
		
		str = new StringBuilder(in.readLine());
		len = str.length();
		pos = 0;
		int[] fact = new int[9];
		fact[1] = 1;
		for (int i = 2; i <= len; i++)
			fact[i] = fact[i - 1] * i;
		
		vectlen = fact[len];
		int[] tmpvect = new int[96];
		Arrays.fill(tmpvect, 0);
		for (int i = 0; i < len; i++)
			tmpvect[str.charAt(i) - 32]++;
		for (int i = 0; i < 96; i++)
			if (tmpvect[i] > 0)
				vectlen /= fact[tmpvect[i]];
		
		vect = new String[vectlen];
		solve(0);
		
		Arrays.sort(vect);
		for (int i = 0; i < vectlen; i++)
			out.println(vect[i]);
		out.close();
	}
	
	static void solve(int p) {
		if (p == len) {
			vect[pos++] = new String(str);
			return;
		}
		
		solve(p + 1);
		boolean[] have = new boolean[96];
		Arrays.fill(have, false);
		have[str.charAt(p) - 32] = true;
		for (int i = p + 1; i < len; i++) 
			if (!have[str.charAt(i) - 32]) {
				have[str.charAt(i) - 32] = true;
				c = str.charAt(p);
				str.setCharAt(p, str.charAt(i));
				str.setCharAt(i, c);
				solve(p + 1);
				c = str.charAt(p);
				str.setCharAt(p, str.charAt(i));
				str.setCharAt(i, c);
			}
	}

	static char c;
	static StringBuilder str;
	static int len, vectlen, pos;
	static String[] vect;
	static BufferedReader in;
	static PrintWriter out;
}