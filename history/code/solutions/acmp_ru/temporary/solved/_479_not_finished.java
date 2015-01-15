import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		segment seg = new segment(in);

		Point mid = new Point();
		mid.x = in.nextDouble();
		mid.y = in.nextDouble();
		double rad = in.nextDouble();
		
		double dist;
		if (scal(seg.f, seg.s, mid) * scal(seg.s, seg.f, mid) < 0) {
			dist = Math.min(Math.hypot(seg.f.x - mid.x, seg.f.y - mid.y), Math.hypot(seg.s.x - mid.x, seg.s.y - mid.y));
		} else {
			dist = Math.abs(vect(seg.f, seg.s, mid)) / Math.hypot(seg.f.x - seg.s.x, seg.f.y - seg.s.y);
		}
		
		if (dist + eps >= rad) 
			dist = Math.hypot(seg.f.x - seg.s.x, seg.f.y - seg.s.y);
		else {
			
		}
		
		out.println(dist);
		out.close();
	}
	
	static double scal(Point p, Point p1, Point p2) {
		return (p1.x - p.x) * (p2.x - p.x) + (p1.y - p.y) * (p2.y - p.y);
	}
	
	static double vect(Point p, Point p1, Point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p1.y - p.y) * (p2.x - p.x);
	}
	
	static double eps = 1e-15;
}

class segment {
	Point f, s;
	
	segment(Scanner in) throws IOException {
		f = new Point();
		s = new Point();
		
		f.x = in.nextDouble();
		f.y = in.nextDouble();
		s.x = in.nextDouble();
		s.y = in.nextDouble();
	}
}

class Point {
	double x, y;
}