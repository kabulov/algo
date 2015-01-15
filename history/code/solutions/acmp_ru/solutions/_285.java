import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int m = in.nextInt();
		
		boolean ans = true;
		int c = in.nextInt();

		if (c <= m) {
			for (int i = 1; i < n; ++i) {
				int t = in.nextInt();
				
				if (t > m) {
					ans = false;
					break;
				}
				
				if (c < m)  c += t - 1;
			}
			
			if (c < m) ans = false;
		} else
			ans = false;
		
		out.println(ans ? "yes" : "no");
		out.close();
	}
}