import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();

		int num = n, div = 2;
		
		while (div * div <= num) {
			if (num % div == 0) {
				n = n / div * (div - 1);
				while (num % div == 0)
					num /= div;
			} else
				div++;
		}
		
		if (num > 1) {
			n = n / num * (num - 1);
		}
				
		out.println(n);
		out.close();
	}
}
