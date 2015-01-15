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

		
		String str = in.readLine();
		if (allsame(str)) {
			out.println("NO SOLUTION");
		} else
		if (palindrom(str)) {
			out.println(str.substring(1));
		} else {
			out.println(str);
		}

		out.close();
	}
	
	static boolean allsame(String s) {
		int len = s.length();
		for (int i = 1; i < len; i++)
			if (s.charAt(i) != s.charAt(i - 1))
				return false;
		return true;
	}
	
	static boolean palindrom(String s) {
		int len = s.length();
		for (int i = 0; i < len / 2; i++)
			if (s.charAt(i) != s.charAt(len - 1 - i))
				return false;
		return true;
	}
}
