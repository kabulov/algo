import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;


public class Main {
	public static void main(String[] argv) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		if (n < 3) {
			out.println("NO");
			out.close();
			return;
		}
		
		x = next();
		y = next();
		
		inp = new circle[n];
		for (int i = 0; i < n; ++i)
			inp[i] = new circle(next(), next(), next());
		
		rand = new Random();
		sort(0, n - 1);

		int start = 0;
		for (int i = 1; i < n; i++) {
			double dsqr = (inp[start].x - inp[i].x) * (inp[start].x - inp[i].x) + (inp[start].y - inp[i].y) * (inp[start].y - inp[i].y);
			double rsqr = inp[start].r - inp[i].r;
			if (dsqr <= rsqr * rsqr && inp[i].r > inp[start].r)
				start = i;
		}
		
		lt = new point();
		rt = new point();
		get(inp[start]);
		
		point olt = new point();
		olt.set(lt);
//		point ort = new point();
		
		boolean yes = true;
		int i;
		for (i = start + 1; i != start; i = (i + 1) % n) {
			get(inp[i]);
			if ((olt.x - x) * (rt.y - y) - (olt.y - y) * (rt.x - x) > 0) {
				yes = false;
				break;
			}
	
			if ((olt.x - x) * (lt.y - y) - (olt.y - y) * (lt.x - x) > 0) {
				olt.set(lt);
			}
		}
		
		get(inp[i]);
		if ((olt.x - x) * (rt.y - y) - (olt.y - y) * (rt.x - x) > 0) 
			yes = false;
		
		if (yes)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
	
	static point lt, rt;
	static void get(circle crc) {
		double a, b, c;
		a = crc.r;
		c = Math.hypot(x - crc.x, y - crc.y);
		b = Math.sqrt(c * c - a * a);
		
		double vx, vy;
		vx = crc.x - x;
		vy = crc.y - y;
		
		double len = b * b / c;
		vx /= c;
		vy /= c;
		
		double xx = x + vx * len;
		double yy = y + vy * len;

		len = a * b / c;
		vx *= len;
		vy *= len;
		
		double tmp = vx;
		vx = -vy;
		vy = tmp;
		
		lt.x = xx + vx;
		lt.y = yy + vy;
		
		rt.x = xx - vx;
		rt.y = yy - vy;
		
		
		if ((crc.x - x) * (lt.y - y) <= (crc.y - y) * (lt.x - x)) {
			point buf = lt;
			lt = rt;
			rt = buf;
		}
	}
	
	static Random rand;
	static circle tmp, mid;
	static double angle;
	static void sort(int l, int r) {
		int i = l, j = r;
		mid = inp[l + rand.nextInt(r - l + 1)];
		angle = pangle(mid.x - x, mid.y - y);
		while (i <= j) {
			for (; pangle(inp[i].x - x, inp[i].y - y) < angle; ++i);
			for (; angle < pangle(inp[j].x - x, inp[j].y - y); --j);
			if (i <= j) {
				tmp = inp[i];
				inp[i] = inp[j];
				inp[j] = tmp;
				
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
	
	static double constant = 2 * Math.PI;
	static double pangle(double x, double y) {
		double ang = Math.atan2(y, x);
		if (ang < 0)
			ang += constant;
		return ang;
	}
	
	static double x, y;
	static circle[] inp;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static double next() throws IOException {
		in.nextToken();
		return in.nval;
	}
}

class circle {
	double x, y, r;
	circle(double a, double b, double c) {
		x = a;
		y = b;
		r = c;
	}
}

class point {
	double x, y;
	
	void set(point a) {
		x = a.x;
		y = a.y;
	}
}