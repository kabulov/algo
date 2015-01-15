import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		if (n == 0) {
			out.println(0);
			out.close();
			return;
		}
		
		int a, b = 0, c = 1;
		for (int i = 2; i <= n; i++) {
			a = b;
			b = c;
			c = a + b;
		}
			
		out.println(c);
		out.close();
	}
}
