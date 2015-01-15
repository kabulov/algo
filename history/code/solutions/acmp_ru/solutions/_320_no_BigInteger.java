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
		
		long[] map = new long[n / m];
		map[0] = 1;
		
		int len = 1, j;
		long i = m, k;
		
		while (++i <= n) {
			j = -1;
			while (++j < len) {
				k = j + 1;
				map[j] = map[j] * (i - k * m + k)  / (i - k * m);
			}
			
			if (i % m == 0) {
				map[len++] = 1;
			}
		}
		
		long answer = 1;
		for (j = 0; j < len; j++)
			answer += map[j];
		
		out.println(answer);
		out.close();
	}
}
