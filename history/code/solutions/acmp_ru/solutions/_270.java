import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		String str = in.next();
		
		boolean has_ = false;
		boolean hasUC = false;
		
		char c;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if (!(c == '_' || ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z'))) {
				out.println("Error!");
				out.close();
				return;
			}
			if ('A' <= c && c <= 'Z')
				hasUC = true;
			if (c == '_')
				has_ = true;
		}
		
		if (!hasUC && !has_)
			out.println(str);
		else
		if (hasUC && !has_) {
			if ('A' <= str.charAt(0) && str.charAt(0) <= 'Z')
				out.println("Error!");
			else {
				String answer = "";
				for (int i = 0; i < len; i++) {
					if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z')
						answer = answer + "_";
					answer = answer + Character.toLowerCase(str.charAt(i));
				}
				out.println(answer);
			}				
		} else
		if (!hasUC && has_) {
			boolean good = true;
			if (str.charAt(0) == '_')
				good = false;
			if (str.charAt(str.length() - 1) == '_')
				good = false;
			for (int i = 2; i < len - 1; i++)
				if (str.charAt(i - 1) == str.charAt(i) && str.charAt(i) == '_') {
					good = false;
					break;
				}
			if (!good) 
				out.println("Error!");
			else {
				boolean up = false;
				String answer = "";
				for (int i = 0; i < len; i++)
					if (str.charAt(i) == '_') {
						up = true;
					} else
					if (up) {
						answer = answer + Character.toUpperCase(str.charAt(i));
						up = false;
					} else {
						answer = answer + str.charAt(i);
					}
				out.println(answer);
			}
		} else
			out.println("Error!");
		
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}