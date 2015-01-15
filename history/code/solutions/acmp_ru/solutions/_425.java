import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		n = in.nextInt();
		w = in.nextInt();
		e = in.nextInt();
		
		a = w - e;
		b = 100 * n;
		c = - 100 * n * w;
		
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (intersect(100 * i, 100 * j, 100 * i - 100, 100 * j - 100))
					++ans;
			}
		}
		
		out.println(ans);
		out.close();
	}
	
	static boolean intersect(int xu, int yu, int xd, int yd) {
		if (w == e) {
			if (yd <= w && w <= yu)
				return true;
			else
				return false;
		}
		
		if (a * xd + b * yu + c == 0)
			return true;
		
		if (a * xu + b * yu + c == 0)
			return true;
		
		if (a * xd + b * yd + c == 0)
			return true;
		
		if (a * xu + b * yd + c == 0)
			return true;
		
		double y, x;
		y = (double)(-c - a * xd) / (double)b;
		if (yd <= y && y <= yu)
			return true;
		
		y = (double)(-c - a * xu) / (double)b;
		if (yd <= y && y <= yu)
			return true;
		
		x = (double)(-c - b * yd) / (double)a;
		if (xd <= x && x <= xu)
			return true;
		
		x = (double)(-c - b * yu) / (double)a;
		if (xd <= x && x <= xu)
			return true;
		
		return false;
	}
	
	static int n, w, e, a, b, c;
}