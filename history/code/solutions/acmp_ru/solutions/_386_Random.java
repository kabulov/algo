import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		out.println("YES");

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		for (int i = 0; i < n; ++i) {
			out.print(-10000 + rand.nextInt(20001));
			out.print(" ");
			out.println(-10000 + rand.nextInt(20001));
		}
		
		out.close();
	}
}
