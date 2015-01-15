import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int i, j, g, f;
		i = in.nextInt();
		j = in.nextInt();
		
		g = gcd(i, j);
		
		if (g == 1)
			f = 1;
		else {
			j = 0;
			f = 1;
			
			int cst = (int)1e9;
			for (int k = 2; k <= g; k++) {
				i = j;
				j = f;
				f = (i + j) % cst;
			}
		}
		
		out.println(f);
		out.close();
	}
	
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
