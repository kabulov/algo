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
		int i = 2, len = str.length();
		
		int d;
		for (int j = 0; j < len; j++) {
			d = Character.digit(str.charAt(j), 36);
			if (d + 1 > i)
				i = d + 1;
		}
		
		int num = in.nextInt();
		
		int number;
		for (; i > 0 && parse(str, i) != num; i = (i + 1) % 37) {}
		
		out.println(i);
		out.close();
	}
	
	static int parse(final String str, int radix) {
		int ans = 0, len = str.length();
		
		int d;
		for (int i = 0; i < len; i++) {
			d = Character.digit(str.charAt(i), 36);
			ans = ans * radix + d; 
		}
		
		return ans;
	}
}
