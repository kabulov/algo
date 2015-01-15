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

		Point[] v = new Point[n + 1];
		for (int i = 0; i < n; ++i)
			v[i] = new Point(in.nextInt(), in.nextInt());
		
		Point tmp;
		for (int i = 1; i < n; ++i)
			if (v[i].x < v[0].x || (v[i].x == v[0].x && v[i].y < v[0].y)) {
				tmp = v[i];
				v[i] = v[0];
				v[0] = tmp;
			}
		v[n] = v[0];
		
		rand = new Random();
		sort(v, 1, n - 1);
		
		Point[] s = new Point[n + 1];
		int top = 2;
		
		s[0] = v[0];
		s[1] = v[1];
		
		int i = 1;
		do {
			++i;
			while (vect(s[top - 2], s[top - 1], v[i]) > 0) 
				--top;
			
			s[top++] = v[i];
		} while (!equal(s[0], s[top - 1]));
		
		double perim = 0;
		tmp = s[--top];
		
		while (top > 0) {
			buf = s[--top];
			perim += Math.sqrt(dist(tmp, buf));
			tmp = buf;
		}
		
		out.println(perim);
		out.close();
	}
	
	static Random rand;
	static Point buf, mid;
	static int mul;
	
	static void sort(Point[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; (mul = vect(v[0], mid, v[i])) > 0 || (mul == 0 && dist(v[0], v[i]) < dist(v[0], mid)); ++i);
			for (; (mul = vect(v[0], mid, v[j])) < 0 || (mul == 0 && dist(v[0], mid) < dist(v[0], v[j])); --j);
			if (i <= j) {
				buf = v[i];
				v[i] = v[j];
				v[j] = buf;
				++i;
				--j;
			}
		}
		if (l < j) sort(v, l, j);
		if (i < r) sort(v, i, r);
	}
	
	static boolean equal(Point a, Point b) {
		return a.x == b.x && a.y == b.y;
	}
	
	static int dist(Point a, Point b) {
		return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
	}
	
	static int vect(Point p, Point p1, Point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y);
	}
}
