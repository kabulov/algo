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
		for (int i = 0; i < n; ++i) v[i] = in.nextInt() - 1;
		
		int s = 1;
		for (int i = 0; i < n; ++i) 
			if (v[i] != -1) {
				int k = 0;
				int p = i;
				
				while (v[p] != -1) {
					++k;
					int t = p;
					p = v[p];
					v[t] = -1;
				}
				
				s = s / gcd(s, k) * k;
			}
		
		out.println(s);
		out.close();
	}
	
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}