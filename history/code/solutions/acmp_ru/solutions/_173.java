import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		int len = 0;
		int[] vect = new int[36];
		
		for (int i = 2; i <= 36; i++) {
			if (palind(convert(n, i))) {
				vect[len++] = i;
			}
		}
		
		if (len == 0) {
			out.println("none");
		} else
		if (len == 1) {
			out.println("unique");
			out.println(vect[0]);
		} else {
			out.println("multiple");
			for (int i = 0; i < len; i++)
				out.print(vect[i] + " ");
		}
		
		out.close();
	}
	
	static char c;
	static StringBuilder convert(int num, int base) {
		StringBuilder result = new StringBuilder();
		while (num > 0) {
			if (num % base >= 10)
				c = (char)(55 + num % base);
			else
				c = (char)(48 + num % base);
			
			result.append(c);
			num /= base;
		}
		return result;
	}
	
	static boolean palind(StringBuilder str) {
		int len = str.length();
		for (int i = 0; i < len / 2; i++) 
			if (str.charAt(i) != str.charAt(len - 1 - i)) {
				return false;
			}
		return true;
	}
}
