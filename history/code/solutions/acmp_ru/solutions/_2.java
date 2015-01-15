import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new Scanner(new File("input.txt")); 

		int n = in.nextInt();
		
		if (n > 0)
			out.println(n * (n + 1) / 2);
		else
		if (n == 0)
			out.println(1);
		else {
			n = -n;
			n = n * (n + 1) / 2 - 1;
			n = -n;
			out.println(n);
		}
			
		
		out.close();
	}
	

	static PrintWriter out;
	static Scanner in;
}