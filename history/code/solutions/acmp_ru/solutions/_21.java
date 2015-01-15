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
		
		out.println(Math.max(Math.max(a, b), c) - Math.min(Math.min(a, b), c));
		
		out.close();
	}
}
