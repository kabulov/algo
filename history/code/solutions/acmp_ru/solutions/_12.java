import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int ans = 0;
		
		point p = new point();
		polygon poly = new polygon(4);
		
		for (int i = 0; i < n; i++) {
			p.x = in.nextLong();
			p.y = in.nextLong();
			
			for (int j = 0; j < 4; j++) 
				poly.v[j].set(in.nextLong(), in.nextLong());
			
			if (poly.contains(p))
				++ans;
		}
		
		out.println(ans);
		out.close();
	}
}

class polygon {
	int np;
	point[] v;
	
	polygon(int n) {
		np = n;
		v = new point[n + 1];
		for (int i = 0; i <= n; i++) 
			v[i] = new point();
	}
	
	boolean contains(point p) {
		v[np] = v[0];

		int zero = 0;
		int sum = 0;
		
		for (int i = 0; i < 4; i++) {
			switch(sgn(vect(v[i], v[i + 1], p))) {
			case 1:
				++sum;
				break;
			case 0:
				++zero;
				break;
			case -1:
				--sum;
				break;
			}
		}
			
		if (sum < 0)
			sum = -sum;
		
		switch(zero) {
		case 2:
			if (sum == 2)
				return true;
			return false;
		case 1:
			if (sum == 3)
				return true;
			return false;
		case 0:
			if (sum == 4)
				return true;
			return false;
		}

		return true;
	}
	
	int sgn(long val) {
		if (val > 0)
			return 1;
		
		if (val < 0)
			return -1;
		
		return 0;
	}
	
	long vect(point p1, point p2, point p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
	}
}

class point {
	long x, y;
	
	void set(long a, long b) {
		x = a;
		y = b;
	}
}

