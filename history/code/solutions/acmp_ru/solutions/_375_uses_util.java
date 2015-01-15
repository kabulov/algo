import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		Scanner in = new Scanner(new File("input.txt"));
		
		int from = in.nextInt();
		int to = in.nextInt();
		
		BigInteger number = in.nextBigInteger(from);
		
		out.println(number.toString(to).toUpperCase());
		out.close();
	}
}