import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int m = in.nextInt();
		int n = in.nextInt();
		
		int t;
		if (m > n) {
			t = m;
			m = n;
			n = t;
		}
		
		t = in.nextInt();
		
		int amt = 0, min = Integer.MAX_VALUE, k = 0;
		while (t - k * n >= 0) {
			if ((t - k * n) % m < min) {
				min = (t - k * n) % m;
				amt = k + (t - k * n) / m;
			}
			if (min == 0)
				break;
			else
				++k;
		}
		
		out.print(amt);
		if (min > 0)
			out.println(" " + min);
		out.close();
	}
}
