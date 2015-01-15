
import static java.lang.Math.min;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		char[] v = in.nextLine().toCharArray();
		int n = v.length;		
		int[] p = new int[n];		
		
		for (int i = 1, j = 0; i < n; ++i) {
			while (j > 0 && v[i] != v[j]) j = p[j - 1];
			if (v[i] == v[j]) ++j;
			p[i] = j;
		}

		out.println(n - p[n - 1]);
		out.close();
	}
}