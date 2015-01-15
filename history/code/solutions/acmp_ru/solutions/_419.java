import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		v = in.readLine().toLowerCase().toCharArray();

		int n = 0;
		for (int i = 0; i < v.length; ++i) 
			if ('a' <= v[i] && v[i] <= 'z')
				v[n++] = v[i];
		
		int s = 0, p = 0;
		for (int i = 0; 2 * i < n; ++i) 
			if (v[i] != v[n - 1 - i]) { 
				p = i;
				++s;
			}
		
		if (s <= 1) {
			out.println("YES");
			if (s == 1) v[n - 1 - p] = v[p];
			for (int i = 0; i < n; ++i) out.print(v[i]);
		} else {
			for (p = 0; v[p] == v[n - 1 - p]; ++p);
			if (mirror(p + 1, n - 1 - p)) {
				out.println("YES");
				for (int i = 0; i < n; ++i) 
					if (i != p)
						out.print(v[i]);
			} else
			if (mirror(p, n - 2 - p)) {
				out.println("YES");
				for (int i = 0; i < n; ++i) 
					if (i != n - 1 - p)
						out.print(v[i]);
			} else
				out.println("NO");
		}
		
		out.close();
	}
	
	static boolean mirror(int l, int r) {
		while (l < r && v[l] == v[r]) {
			++l;
			--r;
		} 
		return l >= r;
	}
	
	static int n;
	static char[] v;
}