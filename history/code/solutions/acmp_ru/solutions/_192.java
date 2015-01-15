import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = in.nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = in.nextInt();
		
		if (last(v, n)) {
			for (int i = 0; i < n; ++i) {
				out.print(i + 1);
				out.print(" ");
			}
		} else {
			int i = n - 1;
			while (v[i - 1] > v[i]) --i;
			int p = i - 1;
			while (i + 1 < n && v[i + 1] > v[p]) ++i;
			
			int t = v[i];
			v[i] = v[p];
			v[p] = t;
			
			for (i = 1; 2 * i <= n - 1 - p; ++i) {
				t = v[p + i];
				v[p + i] = v[n - i];
				v[n - i] = t;
			}
			
			for (i = 0; i < n; ++i) {
				out.print(v[i]);
				out.print(" ");
			}
		}
		
		out.close();
	}
	
	static boolean last(int[] v, int n) {
		if (n == 1) return true;
		
		for (int i = 0; i < n; ++i)
			if (v[i] != n - i)
				return false;
		
		return true;
	}
}