import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		if (in.next().equalsIgnoreCase("Home")) {
			out.println("Yes");
		} else {
			if (in.nextInt() % 2 == 0) {
				out.println("No");
			} else {
				out.println("Yes");
			}
		}
		
		out.close();
	}
}
