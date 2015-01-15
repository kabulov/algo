import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		long[] x, y;
		x = new long[n];
		y = new long[n];
		for (int i = 0; i < n; ++i) {
			x[i] = in.nextLong();
			y[i] = in.nextLong();
		}
		
		long ans = 0, buf;
		long[] d = new long[n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				d[j] = dist(x[i], y[i], x[j], y[j]);
			
			Arrays.sort(d);
			
			buf = 1;
			for (int j = 1; j < n; ++j) 
				if (d[j] == d[j - 1])
					++buf;
				else {
					ans += buf * (buf - 1) / 2;
					buf = 1;
				}
			
			ans += buf * (buf - 1) / 2;
		}
		
		HashSet<Long> set = new HashSet<Long>();
		for (int i = 0; i < n; i++)
			set.add(pack(x[i], y[i]));
		
		long midxl, midyl;
		for (int i = 0; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j) {
				midxl = x[i] + x[j];
				midyl = y[i] + y[j];
				if ((midxl & 1) == 0 && (midyl & 1) == 0)
					if (set.contains(pack(midxl >> 1, midyl >> 1)))
						--ans;
			}
		
		buf = 0;
		long save, mx, my, xxx, yyy;
		double midx, midy, vxx, vyy;
		double constant = Math.sqrt(3) / 2;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; ++j) {
  				midx = (x[i] + x[j]) / 2.0;
				midy = (y[i] + y[j]) / 2.0;
				
				vxx = (-y[i] + y[j]) * constant;
				vyy = (x[i] - x[j]) * constant; 

				save = dist(x[i], y[i], x[j], y[j]);

				mx = Math.round(midx + vxx);
				my = Math.round(midy + vyy);
				if (save == dist(mx, my, x[i], y[i]) && save == dist(mx, my, x[j], y[j])) 
					if (set.contains(pack(mx, my)))
						++buf;
				
				mx = Math.round(midx - vxx);
				my = Math.round(midy - vyy);
				if (save == dist(mx, my, x[i], y[i]) && save == dist(mx, my, x[j], y[j])) 
					if (set.contains(pack(mx, my)))
						++buf;
			}
		
		out.println(ans - 2 * buf / 3);
		out.close();
	}
	
	static long cst = (long)1e9 * 3L;
	static long pack(long x, long y) {
		return x * cst + y;
	}
	
	static long dist(long x1, long y1, long x2, long y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
}

