import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int[] inp = new int[3];
		for (int i = 0; i < 3; i++)
			inp[i] = in.nextInt();
		
		int n = in.nextInt();
		int[] v = new int[n + 1];
		Arrays.fill(v, 0);
		v[0] = 1;
		
		for (int j = 0; j < 3; j++)
			for (int i = 0; i + inp[j] <= n; i++)
				if (v[i] > 0)
					v[i + inp[j]] += v[i];
		
		out.println(v[n]);
		out.close();
	}
}
