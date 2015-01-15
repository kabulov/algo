import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt() - 1;
		
		triangle fst = new triangle();
		triangle mid = new triangle();
		
		fst.read(in);
		boolean yes = true;
		
		for (int i = 0; i < n; i++) {
			mid.read(in);
			
			if (fst.a.len != mid.a.len || fst.b.len != mid.b.len || fst.c.len != mid.c.len) {
				yes = false;
				break;
			}
			
			if (fst.vmul != mid.vmul) {
				yes = false;
				break;
			}
		}
		
		if (yes)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
}

class triangle {
	segment a, b, c;
	long vmul;
	
	triangle() {
		a = new segment();
		b = new segment();
		c = new segment();
		vmul = 0;
	}
	
	void read(Scanner in) {
		long x1, y1, x2, y2, x3, y3;
		
		x1 = in.nextLong();
		y1 = in.nextLong();
		x2 = in.nextLong();
		y2 = in.nextLong();
		x3 = in.nextLong();
		y3 = in.nextLong();
		
		a.assign(x1, y1, x2, y2);
		b.assign(x1, y1, x3, y3);
		c.assign(x2, y2, x3, y3);
		
		segment tmp;
		if (a.len > b.len) {
			tmp = a;
			a = b;
			b = tmp;
		}
		
		if (a.len > c.len) {
			tmp = a;
			a = c;
			c = tmp;
		}
		
		if (b.len > c.len) {
			tmp = b;
			b = c;
			c = tmp;
		}
		
		if (a.len == b.len || b.len == c.len)
			vmul = 0;
		else {
			if (equal(a.x1, a.y1, b.x1, b.y1)) {
				vmul = mul(a.x2 - a.x1, a.y2 - a.y1, b.x2 - b.x1, b.y2 - b.y1);
			} else 
			if (equal(a.x1, a.y1, b.x2, b.y2)) {
				vmul = mul(a.x2 - a.x1, a.y2 - a.y1, b.x1 - b.x2, b.y1 - b.y2);
			} else 
			if (equal(a.x2, a.y2, b.x1, b.y1)) {
				vmul = mul(a.x1 - a.x2, a.y1 - a.y2, b.x2 - b.x1, b.y2 - b.y1);
			} else {
				vmul = mul(a.x1 - a.x2, a.y1 - a.y2, b.x1 - b.x2, b.y1 - b.y2);
			}
		}
	}
	
	boolean equal(long a, long b, long c, long d) {
		return a == c && b == d;
	}
	
	long mul(long x1, long y1, long x2, long y2) {
		return x1 * y2 - x2 * y1;
	}
}

class segment {
	long len;
	long x1, y1, x2, y2;

	segment() {
		len = -1;
	}
	
	void assign(long a, long b, long c, long d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
		len = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
}
