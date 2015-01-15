import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		switch (n % 5) {
		case 0:
			out.println((n - 0 * 3) / 5 + " " + 0);
			break;
		case 1:
			out.println((n - 2 * 3) / 5 + " " + 2);
			break;
		case 2:
			out.println((n - 4 * 3) / 5 + " " + 4);
			break;
		case 3:
			out.println((n - 1 * 3) / 5 + " " + 1);
			break;
		case 4:
			out.println((n - 3 * 3) / 5 + " " + 3);
			break;
		}
		
		out.close();
	}
}
