import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 
		
		n = in.nextInt();
		
		v = new int[n][4];
		
		for (int i = 0; i < n; ) {
			v[i][0] = in.nextInt();
			v[i][1] = in.nextInt();
			v[i][2] = in.nextInt();
			v[i][3] = in.nextInt();
			
			if (v[i][0] == v[i][2] && v[i][1] == v[i][3])
				--n;
			else
				++i;
		}
		
		if (n == 0)
			out.println(24 * 60);
		else {
			int s = 0;
			for (int i = 0; i < 24 * 60; ++i)
				if (offer(i / 60, i % 60)) 
					++s;
			
			out.println(s);
		}
		
		out.close();
	}
	
	static boolean offer(int h, int m) {
		for (int i = 0; i < n; ++i)
			if (!Within(h, m, i))
				return false;
				
		return true;
	}
	
	static boolean Within(int h, int m, int i) {
		if (Less(v[i][0], v[i][1], v[i][2], v[i][3])) {
			return In(v[i][0], v[i][1], v[i][2], v[i][3], h, m);
		} else {
			return In(v[i][0], v[i][1], 24, 0, h, m) || In(0, 0, v[i][2], v[i][3], h, m);
		}		
	}
	
	static boolean In(int h1, int m1, int h2, int m2, int h, int m) {
		return h1 * 60 + m1 <= h * 60 + m && h * 60 + m < h2 * 60 + m2;
	}
	
	static boolean Less(int h1, int m1, int h2, int m2) {
		return (h1 < h2) || (h1 == h2 && m1 < m2);
	}
	
	static int n;
	static int[][] v;
}