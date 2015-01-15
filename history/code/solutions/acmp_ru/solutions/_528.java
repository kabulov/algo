import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextLong();
		long k = in.nextLong();
		
		out.println(1 + n * k * (k + 1) / 2 - k * k);
		
		out.close();
	}
}