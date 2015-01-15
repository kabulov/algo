import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int[] vect = new int[10];
		Arrays.fill(vect, 0);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		int tmp;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++) {
				tmp = i * j;
				while (tmp > 0) {
					vect[tmp % 10]++;
					tmp /= 10;
				}
			}
				
		for (int i = 0; i < 10; i++)
			out.println(vect[i]);
		
		out.close();
	}
}
