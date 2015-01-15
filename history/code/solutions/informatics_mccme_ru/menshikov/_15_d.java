import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = next();
		long len = next();
		
		x = new long[n + 1];
		y = new long[n + 1];
		
		p = 0;
		for (int i = 0; i < n; i++) {
			x[i] = next();
			y[i] = next();
			if (x[i] < x[p] || (x[i] == x[p] && y[i] > y[p]))
				p = i;
		}
		
		buf = x[0];
		x[0] = x[p];
		x[p] = buf;
		
		buf = y[0];
		y[0] = y[p];
		y[p] = buf;
		
		rand = new Random();
		sort(1, n - 1);
		
		x[n] = x[0];
		y[n] = y[0];
		
		long[] sx, sy;
		sx = new long[n + 1];
		sy = new long[n + 1];
		sx[0] = x[0];
		sy[0] = y[0];
		sx[1] = x[1];
		sy[1] = y[1];
		
		int k = 2;
		int i = 1;
		do {
			++i;
			while (vect(sx[k - 2], sy[k - 2], sx[k - 1], sy[k - 1], x[i], y[i]) > 0)
				--k;
			sx[k] = x[i];
			sy[k] = y[i];
			++k;
		} while (i < n);
		
		double perim = 2 * Math.PI * len;
		for (i = 1; i < k; i++) {
			perim += Math.hypot(sx[i] - sx[i - 1], sy[i] - sy[i - 1]);
		}
		
		out.println(Math.round(perim));
		out.close();
	}
	
	static int p;
	static long[] x, y;
	static Random rand;
	static long mx, my, buf;
	static void sort(int l, int r) {
		int i = l, j = r;
		p = l + rand.nextInt(r - l + 1);
		mx = x[p];
		my = y[p];
		while (i <= j) {
			for (; (buf = vect(x[0], y[0], mx, my, x[i], y[i])) > 0 || (buf == 0 && dist(x[0], y[0], x[i], y[i]) < dist(x[0], y[0], mx, my)); ++i);
			for (; (buf = vect(x[0], y[0], mx, my, x[j], y[j])) < 0 || (buf == 0 && dist(x[0], y[0], mx, my) < dist(x[0], y[0], x[j], y[j])); --j);
			if (i <= j) {
				buf = x[i];
				x[i] = x[j];
				x[j] = buf;
				
				buf = y[i];
				y[i] = y[j];
				y[j] = buf;
				
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
	
	static long vect(long x, long y, long x1, long y1, long x2, long y2) {
		return (x1 - x) * (y2 - y) - (x2 - x) * (y1 - y);
	}
	
	static long dist(long x1, long y1, long x2, long y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
