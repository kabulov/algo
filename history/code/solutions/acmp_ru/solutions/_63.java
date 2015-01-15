import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int c1 = in.nextInt();
		int c2 = in.nextInt();
		
		int b;
		for (b = 1; b <= 1000; b++)
			if ((c1 - b) * b == c2)
				break;
		
		int a = c1 - b;
		if (a > b) {
			c1 = a;
			a = b;
			b = c1;
		}
		
		out.println(a + " " + b);
		out.close();
	}
}