import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		int[] v = new int[n+1];
		for (int i = 1; i <= n; ++i) v[i] = in.nextInt();
		
		int[] p = new int[n+1];
		p[n] = n;
		for (int i = n - 1; i > 0; --i) p[i] = v[i] > v[p[i + 1]] ? i : p[i + 1];
		
		int i = 0, r = 0;
		while (i < n) {
			int j = p[i+1];
			r += (j - i) * v[j];
			i = j;
		}
		
		out.println(r);
		out.close();
	}
}