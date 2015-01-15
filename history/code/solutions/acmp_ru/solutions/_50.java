import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 
		
		String a = in.next();
		String b = in.next();
		
		len = b.length();
		str = (b+b).toCharArray();
		sub = new char[len];		
		subpf = new int[len];
		
		int ans = 0, ln = a.length() - len;
		
		for (int i = 0; i <= ln; ++i) {
			for (int j = 0; j < len; ++j) sub[j] = a.charAt(i + j);
			if (offer()) ++ans;
		}
		
		out.println(ans);
		out.close();
	}
	
	static boolean offer() {
		subpf[0] = 0;
		for (int i = 1, j = 0; i < len; ++i) {
			while (j > 0 && sub[i] != sub[j]) j = subpf[j - 1];
			if (sub[i] == sub[j]) ++j;
			subpf[i] = j;
		}
		
		for (int i = 0, j = 0; i < 2 * len; ++i) {
			while (j > 0 && str[i] != sub[j]) j = subpf[j - 1];
			if (str[i] == sub[j]) ++j;
			if (j == len) return true;
		}
		
		return false;
	}
	
	static int len;	//strlen = 2 * sublen;
	static char[] str, sub;
	static int[] subpf;
}
