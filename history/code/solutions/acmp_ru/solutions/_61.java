import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int as = 0, bs = 0;
		
		as += in.nextInt();
		bs += in.nextInt();
		
		as += in.nextInt();
		bs += in.nextInt();

		as += in.nextInt();
		bs += in.nextInt();

		as += in.nextInt();
		bs += in.nextInt();

		if (as == bs)
			out.println("DRAW");
		else
			out.println((as > bs) ? 1 : 2);
		
		out.close();
	}
}
