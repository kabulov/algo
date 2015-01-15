import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		Scanner in = new Scanner(new File("input.txt"));
		
		long n = in.nextLong();
		
		int len = 0;
		int[] amt = new int[64];
		
		long div = 2;
		while (n > 1 && div < 998) {
			if (n % div == 0) {
				amt[len] = 0;
				while (n > 1 && n % div == 0) {
					amt[len]++;
					n /= div;
				}
				len++;
			} else
				div++;
		}
		
		int result = 1;
		for (int i = 0; i < len; i++)
			result *= amt[i] + 1;
		
		out.println(result);
		out.close();
	}
}