import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		n = in.nextInt();
		k = in.nextInt();
		
		v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = i;
		
		amt = 0;
		for (int i = 0; i < n; ++i) {
			swap(0, i);
			solve(1);
		}
						
		out.println(amt);
		out.close();
	}
	
	static void solve(int p) {
		if (p == n) {
			++amt;
			return;
		}
		
		for (int i = p; i < n; ++i) 
			if (Math.abs(v[i] - v[p - 1]) <= k) {
				swap(p, i);
				solve(p + 1);
				swap(p, i);
			}		
	}
	
	static void swap(int i, int j) {
		int t = v[i];
		v[i] = v[j];
		v[j] = t;
	}
	
	static int n, k;
	static int amt;
	static int[] v;
}