import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.next();
		
		int max = 0;
		int cur = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1') {
				max = Math.max(max, cur);
				cur = 0;
			} else {
				cur++;
			}
		}
		
		out.println(Math.max(cur, max));
		
		out.close();
	}
}
