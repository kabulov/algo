import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		if (n == 1) {
			out.println(0);
			out.close();
			return;
		}
		
		Point[] v = new Point[n + 1];
		for (int i = 0; i < n; i++)
			v[i] = new Point(in.nextInt(), in.nextInt());
		
		rand = new Random();
		sortPoint(v, 0, n - 1);
		
		int len = 1;
		for (int i = 1; i < n; i++)
			if (!equal(v[i - 1], v[i]))
				v[len++] = v[i];
		
		if (len == 1) {
			out.println(0);
			out.close();
			return;
		}
		
		n = len;
		for (int i = 1; i < n; i++)
			if (v[i].x > v[0].x || (v[0].x == v[i].x && v[0].y < v[i].y)) {
				buf = v[0];
				v[0] = v[i];
				v[i] = buf;
			}		
		v[n] = v[0];
		
		sortPolar(v, 1, n - 1);
		Point[] s = new Point[n + 1];
		
		s[0] = v[0];
		s[1] = v[1];
		
		len = 2;
		int i = 1;
		do {
			++i;
			while (vect(s[len - 2], s[len - 1], v[i]) < 0)
				--len;
			
			s[len++] = v[i];
		} while (!equal(s[0], s[len - 1]));
		
		int area = 0;
		buf = new Point(0, 0);
		for (i = 0; i < len - 1; ++i)
			area += vect(buf, s[i], s[i + 1]);
			
		out.println(Math.round((double)Math.abs(area) / 2));
		out.close();
	}
	
	static Random rand;
	static Point mid, buf;
	
	static void sortPoint(Point[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; v[i].x < mid.x || (v[i].x == mid.x && v[i].y < mid.y); ++i);
			for (; mid.x < v[j].x || (mid.x == v[j].x && mid.y < v[j].y); --j);
			if (i <= j) {
				buf = v[i];
				v[i] = v[j];
				v[j] = buf;
				++i;
				--j;
			}
		}
		if (l < j) sortPoint(v, l, j);
		if (i < r) sortPoint(v, i, r);
	}
	
	static boolean equal(Point a, Point b) {
		return a.x == b.x && a.y == b.y;
	}
	
	static int mul;
	static void sortPolar(Point[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; (mul = vect(v[0], mid, v[i])) < 0 || (mul == 0 && dist(v[0], v[i]) < dist(v[0], mid)); ++i);
			for (; (mul = vect(v[0], mid, v[j])) > 0 || (mul == 0 && dist(v[0], mid) < dist(v[0], v[j])); --j);
			if (i <= j) {
				buf = v[i];
				v[i] = v[j];
				v[j] = buf;
				++i;
				--j;
			}
		}
		if (l < j) sortPolar(v, l, j);
		if (i < r) sortPolar(v, i, r);
	}
	
	static int dist(Point a, Point b) {
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}
	
	static int vect(Point p, Point p1, Point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y);
	}
}
