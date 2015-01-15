import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int R = in.nextInt();
		int r = in.nextInt();
		int h = in.nextInt();
		int b = in.nextInt();
		
		int x = r;
		int y = r + h - b;
		
		if (x * x + y * y <= R * R)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
}