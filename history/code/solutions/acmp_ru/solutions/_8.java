import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		
		if (a * b == c)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
}
