import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		long area = 0;
		long perim = 0;
		
		Point fst = new Point(in.nextLong(), in.nextLong());
		Point prev = new Point(0, 0), cur = new Point(fst);
		
		Point zero = new Point(0, 0);
		for (int i = 1; i < n; i++) {
			prev.set(cur);
			cur.set(in.nextLong(), in.nextLong());
			
			area += vect(zero, prev, cur);
			perim += gcd(Math.abs(prev.x - cur.x), Math.abs(prev.y - cur.y));
		}
		
		area += vect(zero, cur, fst);
		perim += gcd(Math.abs(cur.x - fst.x), Math.abs(cur.y - fst.y));
		
		out.println((Math.abs(area) - perim) / 2 + 1);
		out.close();
	}
	
	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	static long vect(Point p, Point p1, Point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y); 
	}
}

class Point {
	long x, y;
	Point(long a, long b) {
		x = a;
		y = b;
	}
	
	Point(Point p) {
		x = p.x;
		y = p.y;
	}
	
	void set(long a, long b) {
		x = a;
		y = b;
	}
	
	void set(Point p) {
		x = p.x;
		y = p.y;
	}
}
