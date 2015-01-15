import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int max = 0;
		int cur = 0;
		
		for (int i = 0; i < n; i++) {
			if (in.nextInt() > 0) {
				cur++;
			} else {
				max = Math.max(max, cur);
				cur = 0;
			}
		}
		
		out.println(Math.max(max, cur));
		
		out.close();
	}
}
