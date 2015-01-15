import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextInt();
		
		long max2 = 1;
		while (max2 * 2 < n)
			max2 *= 2;
		
		long ctr = 0;
		
		while (max2 > 0) {
			if (n > max2) {
				n -= max2;
				++ctr;
			}
				
			max2 /= 2;
		}
		
		out.print(ctr % 3);
		
		in.close();
		out.close();
	}
}