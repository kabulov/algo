import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		segment seg = new segment(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
		circle cir = new circle(in.nextDouble(), in.nextDouble(), in.nextDouble());
		
		double answer = 0;
		if (pSegDist(seg, cir.centre) >= cir.rad)
			answer = dist(seg.a, seg.b);
		else {
			point scd, thd = new point();
			if (onCircle(cir, seg.a) && onCircle(cir, seg.b)) {
				answer = arc(cir, seg.a, seg.b);
			} else
			if (onCircle(cir, seg.a)) {
				scd = solve(cir, seg.b, seg.a, thd);
				answer = dist(scd, seg.b) + arc(cir, seg.a, scd);
			} else
			if (onCircle(cir, seg.b)) {
				scd = solve(cir, seg.a, seg.b, thd);
				answer = dist(scd, seg.a) + arc(cir, seg.b, scd);
			} else {
				point tmp = new point();
				scd = solve(cir, seg.a, seg.b, tmp);
				thd = solve(cir, seg.b, seg.a, tmp);
				
				if (pLineDist(seg, cir.centre) < eps) 
					thd = tmp;
				
				answer = dist(seg.a, scd) + dist(seg.b, thd) + arc(cir, scd, thd);
			}
		}
		
		out.println(answer);
		out.close();
	}
	
	static point solve(circle crc, point p, point pv, point sol) {
		point p1, p2;
		double a = crc.rad;
		double b = dist(crc.centre, p);
		double c = Math.sqrt(sqr(b) - sqr(a));
		double vx, vy, len = b;
		vx = crc.centre.x - p.x;
		vy = crc.centre.y - p.y;
		vx /= len;
		vy /= len;
		len = sqr(c) / b;
		point mid = new point(p.x + vx * len, p.y + vy * len);
		double tmp = vx;
		vx = -vy;
		vy = tmp;
		len = c * a / b;
		p1 = new point(mid.x + vx * len, mid.y + vy * len);
		p2 = new point(mid.x - vx * len, mid.y - vy * len);
		
		if (vect(p, pv, p1) * vect(p, pv, crc.centre) <= eps) {
			sol.x = p2.x;
			sol.y = p2.y;
			return p1;
		}
		//else
		sol.x = p1.x;
		sol.y = p1.y;
		return p2;
	}
	
	static double arc(circle c, point p1, point p2) {
		double angle = Math.abs(pAngle(c.centre, p1, p2));
		return c.rad * angle;
	}
	
	static double pAngle(point p, point p1, point p2) {
		return Math.atan2(vect(p, p1, p2), scal(p, p1, p2));
	}
	
	static boolean onCircle(circle c, point p) {
		if (Math.abs(sqr(p.x - c.centre.x) + sqr(p.y - c.centre.y) - sqr(c.rad)) < eps)
			return true;
		
		return false;
	}
	
	static double sqr(double a) {
		return a * a;
	}
	
	static double pSegDist(segment seg, point p) {
		if (scal(seg.a, seg.b, p) >= 0 && scal(seg.b, seg.a, p) >= 0)
			return pLineDist(seg, p);
		else
			return Math.min(dist(seg.a, p), dist(seg.b, p));
	}
	
	static double pLineDist(segment seg, point p) {
		return Math.abs(vect(seg.a, seg.b, p)) / dist(seg.a, seg.b);
	}
	
	static double dist(point a, point b) {
		return Math.hypot(a.x - b.x, a.y - b.y);
	}
	
	static double scal(point p, point p1, point p2) {
		return (p1.x - p.x) * (p2.x - p.x) + (p1.y - p.y)* (p2.y - p.y); 
	}
	
	static double vect(point p, point p1, point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y) ;
	}
	
	static double eps = 1e-12;
}

class circle {
	point centre;
	double rad;
	circle(double x, double y, double r) {
		centre = new point(x, y);
		rad = r;
	}
}

class segment {
	point a, b;
	segment(double x1, double y1, double x2, double y2) {
		a = new point(x1, y1);
		b = new point(x2, y2);
	}
}

class point {
	double x, y;
	point(double a, double b) {
		x = a;
		y = b;
	}
	
	point() {
		this(0, 0);
	}
}


