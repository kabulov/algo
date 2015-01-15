
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		n = in.nextInt();
		v = new int[n];
		
		int m = in.nextInt();
		for (int i = 0; i < n; ++i) v[i] = in.nextInt();
		
		int[] d = new int[m];
		for (int i = 0; i < m; ++i) d[i] = in.nextInt();
		
		for (int i = m - 1; i >= 0; --i) 
			solve(d[i] - 1);
		
		for (int i = 0; i < n; ++i) 
			out.print(v[i] + " ");
		
		out.close();
	}
	
	static void solve(int last) {
		int min = 0;
		for (int i = 1; i < n; ++i)
			if (v[i] < v[min])
				min = i;
		
		int mnamt = 1;
		for (int i = (min + 1) % n; i != min; i = (i + 1) % n)
			if (v[i] == v[min])
				++mnamt;
		
		if (mnamt > 1) {
			if (v[last] != v[min]) {
				int mn = v[min];
				for (min = (n + last - 1) % n; v[min] != mn; min = (n + min - 1) % n);
			} else
				min = last;
		}
		
		if (last == min) {
			int amt = v[min];
			for (int i = 0; i < n; ++i)
				v[i] -= amt;
			v[min] = n * amt;
		} else {
			int amt = v[min] * n, buf = v[min];
			for (int i = (min + 1) % n; i != last; i = (i + 1) % n) {
				++amt;
				v[i] -= buf + 1;
			}
			++amt;
			v[last] -= buf + 1;
			for (int i = (last + 1) % n; i != min; i = (i + 1) % n) {
				v[i] -= buf;
			}
			v[min] = amt;
		}
	}
	
	static int[] v;
	static int n;
}
