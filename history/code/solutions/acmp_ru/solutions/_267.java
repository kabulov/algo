import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt() - 1;
		int x = in.nextInt();
		int y = in.nextInt();
		
		int l = 0, r = Integer.MAX_VALUE - 1;
		int m = 0, tmp;
		
		while (l < r) {
			m = (l + r) / 2;
			tmp = m / x + m / y;
			if (tmp < n)
				l = m + 1;
			else
				r = m;
		}
		
		out.println(Math.min(y, x) + r); //or l : doesn`t matter
		out.close();
	}
}