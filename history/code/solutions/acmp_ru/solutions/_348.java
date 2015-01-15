import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		segment a = new segment(in);
		segment b = new segment(in);
		
		if (vect(a.f, a.s, b.f) == 0 && vect(a.f, a.s, b.s) == 0) {
			if (scal(a.f, a.s, b.f) <= 0 || scal(a.f, a.s, b.s) <= 0)
				out.println("Yes");
			else
				out.println("No");
		} else {
			if (vect(a.f, a.s, b.f) * vect(a.f, a.s, b.s) > 0 || vect(b.f, b.s, a.f) * vect(b.f, b.s, a.s) > 0)
				out.println("No");
			else
				out.println("Yes");
		}
		
		out.close();
	}
	
	static long scal(Point p1, Point p2, Point p) {
		return (long)(p.x - p1.x) * (p.x - p2.x) + (long)(p.y - p1.y) * (p.y - p2.y);
	}
	
	static long vect(Point p, Point p1, Point p2) {
		return (long)(p1.x - p.x) * (p2.y - p.y) - (long)(p1.y - p.y) * (p2.x - p.x);
	}
}

class segment {
	Point f, s;
	
	segment(Scanner in) throws IOException {
		f = new Point();
		s = new Point();
		
		f.x = in.nextInt();
		f.y = in.nextInt();
		s.x = in.nextInt();
		s.y = in.nextInt();
	}
}