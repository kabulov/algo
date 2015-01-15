import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		fill = new int[10];
		
		if (fit(n)) {
			out.println(n);
		} else {
			int tmp = n - 1;
			int a = 1;
			while (!fit(tmp)) {
				a++;
				tmp--;
			}
			int b = 1;
			tmp = n + 1;
			while (!fit(tmp)) {
				b++;
				tmp++;
			}
			if (a <= b) {
				out.println(n - a);
			} else
			if (b < a) {
				out.println(n + b);
			}
		}
		
		out.close();
	}

	static int[] fill;
	static boolean fit(int n) {
		if (n < 102) {
			return true;
		}
		for (int i = 0; i < 10; i++)
			fill[i] = 0;
		
		while (n > 0) {
			fill[n % 10] = 1;
			n /= 10;
		}
		
		int s = 0;
		for (int i = 0; i < 10; i++)
			s += fill[i];
		
		if (s > 2)
			return false;
		
		return true;
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}