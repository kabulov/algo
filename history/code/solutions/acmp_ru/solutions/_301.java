import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int s = in.nextInt();
		int k = in.nextInt();
		
		if (k == 1) {
			out.println(s + " " + s);
		} else 
			out.println(max(s, k) + " " + min(s, k));		

		out.close();
	}
	
	static String max(int s, int k) {
		String str = "";
		
		for (int i = 0; i < s / 9; ++i) str += "9";
		if (s / 9 < k) str += new Integer(s % 9).toString();
		for (int i = 0; i < k - s / 9 - 1; ++i) str += "0";		
		
		return str;
	}
	
	static String reverse(String str) {
		String s = "";
		for (int i = 0; i < str.length(); ++i) s += str.charAt(str.length() - i - 1);
		return s;
	}
	
	static String min(int s, int k) {
		if (s / 9 == k || (s / 9 == k - 1 && s % 9 != 0)) return reverse(max(s, k));
		
		String str = "1";
		--s;
		--k;
		
		for (int i = 0; i < k - s / 9 - 1; ++i) str += "0";
		str += new Integer(s % 9).toString();
		for (int i = 0; i < s / 9; ++i) str += "9";
		
		return str;
	}
}