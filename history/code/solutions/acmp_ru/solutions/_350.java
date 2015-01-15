import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new BufferedReader(new FileReader("input.txt")); 
		
		str = new StringBuilder(in.readLine());
		len = str.length();
		
		pos = 0;
		vectlen = 1;
		for (int i = 2; i <= len; i++)
			vectlen *= i;
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
		for (int i = p + 1; i < len; ++i) {
			tmp = str.charAt(p);
			str.setCharAt(p, str.charAt(i));
			str.setCharAt(i, tmp);
			solve(p + 1);
			tmp = str.charAt(p);
			str.setCharAt(p, str.charAt(i));
			str.setCharAt(i, tmp);
		}
	}

	static int pos;
	static String[] vect;
	static int vectlen;
	static char tmp;
	static int len;
	static StringBuilder str;
	static BufferedReader in;
	static PrintWriter out;
}