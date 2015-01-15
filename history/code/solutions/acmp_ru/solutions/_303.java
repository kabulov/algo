import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		char[] v = in.next().toCharArray();
		
		int len = v.length;
		int[] p = new int[len];
		p[len - 1] = v[len - 1] - '0';
		
		for (int i = len - 2; i >= 0; --i)
			p[i] = v[i] - '0' - p[i + 1];
		
		int max = p[1], tmp = Integer.MIN_VALUE;
		for (int i = 1; i < len - 1; i++) {
			if (i % 2 == 0) {
				tmp = p[0] - p[i] + p[i + 1];				
			} else {
				tmp = p[0] + p[i] - p[i + 1];
			}
			if (tmp > max)
				max = tmp;
		}

		if (len % 2 == 0) {
			tmp = p[0] + p[len - 1];
		} else {
			tmp = p[0] - p[len - 1];
		}
		
		if (tmp > max)
			max = tmp;
		
		out.println(max);
		out.close();
	}
}
