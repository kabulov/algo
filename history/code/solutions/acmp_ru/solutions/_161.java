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
		
		int[] p = new int[n];
		for (int i = 0; i < n; ++i) p[i] = i + 1;
		
		for (int i = n - 1; i > 0; --i) 
			for (int j = i - v[i]; j < i; ++j) {
				int t = p[j];
				p[j] = p[j + 1];
				p[j + 1] = t;
			}
							
		for (int i = 0; i < n; ++i) out.print(p[i] + " ");			
		out.close();
	}
}