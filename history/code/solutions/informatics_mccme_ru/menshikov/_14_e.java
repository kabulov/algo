import java.awt.Point;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		Point[] v = new Point[n + 4];
		
		int w, h;
		w = nextInt();
		h = nextInt();
		
		for (int i = 0; i < n; i++) {
			v[i] = new Point(nextInt(), nextInt());
		}
		
		v[n++] = new Point(w, 0);
		v[n++] = new Point(0, h);
		v[n++] = new Point(0, 0);
		v[n++] = new Point(w, h);
		
		int[] y = new int[n];
		for (int i = 0; i < n; i++)
			y[i] = v[i].y;
		
		Arrays.sort(y);
		Arrays.sort(v, new Comparator<Point>() {
			public int compare(Point arg0, Point arg1) {
				if (arg0.x == arg1.x) 
					return arg0.y - arg1.y;
				return arg0.x - arg1.x;
			}
		}
		);
		
		int k = 1;
		for (int i = 1; i < n; i++)
			if (y[i] != y[i - 1]) {
				y[k++] = y[i];
			}
		
		int xm = 0, ym = 0, sm = -1, tmp;
		for (int p1 = 0; p1 < k - 1; p1++)
			for (int p2 = p1 + 1; p2 < k; p2++) {
				int left = 0; 
				
				for (int j = 1; j < n; j++)
					if (y[p1] < v[j].y && v[j].y < y[p2]) {
						if ((tmp = Math.min(y[p2] - y[p1], v[j].x - left)) > sm) {
							sm = tmp;
							xm = left;
							ym = y[p1];
						}
						left = v[j].x;
					}
				
				if ((tmp = Math.min(y[p2] - y[p1], w - left)) > sm) {
					sm = tmp;
					xm = left;
					ym = y[p1];
				}
			}
		
		out.println(xm + " " + ym + " " + sm);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}