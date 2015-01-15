import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextInt();
		long m = in.nextInt();
		
		long answer = (n + 2) * m + (m + 2) * n;
		
		if (n == 1 || m == 1)
			answer -= 2;
		else
		if (n % 2 == 1 && m % 2 == 1)
			answer -= 2;
		
		out.println(answer);
		out.close();
	}
}