import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long f, p;
		f = in.nextLong();
		p = in.nextLong();
		
		long x = f, k;
		for (int i = 0; i < 4; ++i) {
			x = x + ((x - f) / p + 1) * (f - p);
		}
		
		out.println(x);
		out.close();
	}	
}
