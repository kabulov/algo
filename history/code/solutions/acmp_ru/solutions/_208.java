import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int max = n;
		
		int len = -1;
		int tmp = n;
		while (tmp > 0) {
			++len;
			tmp >>= 1;
		}
		
		for (int i = 0; i < len; i++) {
			tmp = n & 1;
			n = (n >> 1) | (tmp << len);
			if (n > max)
				max = n;
		}
		
		out.println(max);
		out.close();
	}
}
