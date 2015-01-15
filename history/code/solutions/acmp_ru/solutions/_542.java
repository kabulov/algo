import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		long answer = 0;
		while (n > 0) {
			answer = answer * 2 + n % 2;
			n /= 2;
		}
		
		out.println(answer);
		out.close();
	}
}