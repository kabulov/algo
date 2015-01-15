import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
			if (v[i].x > v[0].x || (v[i].x == v[0].x && v[i].y < v[0].y)) {
				tmp = v[i];
				v[i] = v[0];
				v[0] = tmp;
			}
		
		v[n] = v[0];
		
		double perim = 0;
		
		int i = 0, mul;
		do {
			++i;
			for (int j = i + 1; j <= n; ++j) 
				if ((mul = vect(v[i - 1], v[i], v[j])) < 0 || (mul == 0 && dist(v[i - 1], v[j]) > dist(v[i - 1], v[i]))) {
					tmp = v[i];
					v[i] = v[j];
					v[j] = tmp;
				}
			
			perim += Math.sqrt(dist(v[i - 1], v[i]));
		} while (!equal(v[i], v[0]));
		
		out.println(perim);
		out.close();
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
