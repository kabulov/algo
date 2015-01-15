import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//too much ambiguities in problem statement. each given point is considered as an angle of a convex hull.
//but when n > 8 it is possible to construct a convex hull in which we can insert a circle not considering each point from input an angle.
//because some points can be an angle of 180 degrees!!!
public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		v = new point[n + 2];
		
		for (int i = 0; i < n; ++i)
			v[i] = new point(in.nextInt(), in.nextInt());
		
		v[n] = v[0];
		v[n + 1] = v[1];
//		while (Math.abs(vect(v[0], v[1], v[2])) < eps) 
//			shl(v, n);

//		shl(v, n);
//		v[n] = v[0];

//		int k = 2;
//		for (int i = 2; i <= n; i++) 
//			if (Math.abs(vect(v[k - 2], v[k - 1], v[i])) < eps) {
//				v[k - 1] = v[i];
//			} else
//				v[k++] = v[i];
		
//		v[k] = v[1];
//		k--;
		//k points on poly
		
		getBis(v[0], v[1], v[2]);
		line fst = new line(a, b, c);
		
		getBis(v[1], v[2], v[3]);
		line scd = new line(a, b, c);
		
		boolean yes = true;
		point isect = new point();
		
		//no coinciding lines
		if (intersect(fst, scd, isect)) {
			point mid = new point();
			for (int i = 2; i < n; i++) {//i < k in commented algorithm
				fst.set(scd.a, scd.b, scd.c);
				
				getBis(v[i], v[i + 1], v[i + 2]);
				scd.set(a, b, c);
				
				if (intersect(fst, scd, mid)) {
					if (!mid.equal(isect)) {
						yes = false;
						break;
					}
				} else {
					yes = false;
					break;
				}
			}
		} else {
			yes = false;
		}
		
		if (yes) {
			out.println("YES");
			double rad = dist(isect, v[0], v[1]);
			
			// this is important 
			if (Math.abs(isect.x) < eps)
				isect.x = eps;
			if (Math.abs(isect.y) < eps)
				isect.y = eps;
			if (Math.abs(rad) < eps) 
				rad = eps;
			
			out.printf("%.3f %.3f %.3f", isect.x, isect.y, rad);
		} else
			out.println("NO");
		
		out.close();
	}
	
	static boolean intersect(line f, line s, point p) {
		double d;
		d = f.a * s.b - f.b * s.a;
		if (Math.abs(d) < eps) {
			//lines do not coincide
			//that is they are parallel
			return false;
		} else {
			double dx, dy;
			dx = - f.c * s.b + f.b * s.c;
			dy = - f.a * s.c + f.c * s.a;
			p.x = dx / d;
			p.y = dy / d;
			return true;
		}
	}
	
	static double dist(point p, point p1, point p2) {
		return Math.abs(vect(p, p1, p2)) / Math.hypot(p1.x - p2.x, p1.y - p2.y);
	}
	
	static double a, b, c;
	
	static void getBis(point p1, point p, point p2) {
		double x1, y1, x2, y2, len1, len2;
		x1 = p1.x - p.x;
		y1 = p1.y - p.y;
		len1 = Math.hypot(x1, y1);
		
		x2 = p2.x - p.x;
		y2 = p2.y - p.y;
		len2 = Math.hypot(x2, y2);
		
		x1 /= len1;
		y1 /= len1;
		
		x2 /= len2;
		y2 /= len2;
		
		double vx, vy;
		vx = x1 + x2;
		vy = y1 + y2;
		
		a = vy;
		b = -vx;
		c = vx * p.y - vy * p.x;
	}
	
	static point[] v;
	
	static void shl(point[] v, int n) {
		point tmp = v[0];
		for (int i = 0; i < n - 1; i++)
			v[i] = v[i + 1];
		v[n - 1] = tmp;
	}
	
	static double vect(point p, point p1, point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y);  
	}
	
	static double eps = 1e-12;
}

class line {
	double a, b, c;
	line(double f, double s, double t) {
		a = f;
		b = s;
		c = t;
	}
	
	void set(double f, double s, double t) {
		a = f;
		b = s;
		c = t;
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
	
	boolean equal(point arg) {
		return Math.abs(x - arg.x) < eps && Math.abs(y - arg.y) < eps;
	}
	
	static double eps = 1e-12;
}