import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		
		if (a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		out.println(Math.min(b - a - 1, n - (b - a + 1)));		
		
		out.close();
	}
}
