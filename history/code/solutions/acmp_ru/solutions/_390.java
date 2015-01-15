import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        point p = new point();
        long x1, y1, x2, y2, x3, y3;
        
        x1 = in.nextLong();
        y1 = in.nextLong();
        
        x2 = in.nextLong();
        y2 = in.nextLong();
        
        x3 = in.nextLong();
        y3 = in.nextLong();
        
        p.set(in.nextLong(), in.nextLong());
        
        segment fst = new segment(x1, y1, x2, y2);
        segment scd = new segment(x1, y1, x3, y3);
        segment thd = new segment(x3, y3, x2, y2);
        
        double dist = Math.min(fst.dist(p), scd.dist(p));
        dist = Math.min(dist, thd.dist(p));
        
        out.println(dist);
        out.close();
    }
} 

class segment {
	point f, s;
	
	segment(long x1, long y1, long x2, long y2) {
		f = new point();
		s = new point();
		
		f.set(x1, y1);
		s.set(x2, y2);
	}
	
	double dist(point p) {
		if (scal(f, s, p) >= 0 && scal(s, f, p) >= 0) 
			return Math.abs(vect(f, s, p)) / Math.hypot(f.x - s.x, f.y - s.y);

		return Math.min(Math.hypot(f.x - p.x, f.y - p.y), Math.hypot(s.x - p.x, s.y - p.y));
	}
	
	long vect(point p, point p1, point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y);
	}
	
	long scal(point p, point p1, point p2) {
		return (p1.x - p.x) * (p2.x - p.x) + (p1.y - p.y) * (p2.y - p.y);
	}
}

class point {
	long x, y;
	
	void set(long a, long b) {
		x = a;
		y = b;
	}
}