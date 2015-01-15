import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = (int)next();
		poly[] vect = new poly[n];
		
		int k;
		for (int i = 0; i < n; ++i) {
			k = (int)next();
			vect[i] = new poly(k);
			for (int j = 0; j < k; ++j)
				vect[i].p[j] = new point(next(), next());
		}
		
		matrix = new boolean[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				matrix[i][j] = false;
		
		for (int i = 0; i < n - 1; i++) 
			for (int j = i + 1; j < n; j++)
				if (vect[i].common(vect[j].p[vect[j].size - 1])) {
					matrix[i][j] = matrix[j][i] = true;
					vect[j].size--;
				} else
				if (vect[j].common(vect[i].p[vect[i].size - 1])) {
					matrix[i][j] = matrix[j][i] = true;
					vect[i].size--;
				}
		
		set = new int[n];
		for (int i = 0; i < n; i++)
			set[i] = -1;
		
		k = 0;
		for (int i = 0; i < n; i++)
			if (set[i] == -1)
				dfs(i, k++);
		
		double area = -1;
		
		poly temp = new poly(1001);
		for (int i = 0; i < 1001; i++)
			temp.p[i] = new point(-1, -1);
		
		temp.prepare();
		for (int i = 0; i < n; i++) {
			if (set[i] != -1) {
				temp.clear();
				
				k = set[i];
				for (int j = i; j < n; j++) 
					if (set[j] == k) {
						set[j] = -1;
						for (int m = 0; m < vect[j].size; m++) {
							//temp.add(vect[j].p[m]);
							temp.p[temp.size].set(vect[j].p[m]);
							++temp.size;
						}
					}
				
				area = Math.max(area, temp.area());
			}
		}
		
		out.printf("%.2f", area);
		out.close();
	}
	
	static int n;
	static int[] set;
	static boolean[][] matrix;
	static void dfs(int i, int k) {
		set[i] = k;
		for (int j = 0; j < n; j++)
			if (matrix[i][j] && set[j] == -1)
				dfs(j, k);
	}
	
	
	static StreamTokenizer in;
	static double next() throws IOException {
		in.nextToken();
		return in.nval;
	}
}

class point {
	double x, y;
	point(double a, double b) {
		x = a;
		y = b;
	}
	
	void set(point pnt) {
		x = pnt.x;
		y = pnt.y;
	}
}

class poly {
	int size;
	point[] p;

	double eps = 1e-10;
	
	poly(int s) {
		size = s;
		p = new point[s];
	}
	
	boolean common(point pt) {
//		for (int i = 1; i < size; i++)
//			if (belong(pt, p[i - 1], p[i])) 
//				return true;
			
		for (int i = 0; i < size; i++)
			if (equal(p[i], pt))
				return true;
		
		return false;
	}
	
	boolean equal(point a, point b) {
		return Math.abs(a.x - b.x) < eps && Math.abs(a.y - b.y) < eps;
	}
	
//	boolean belong(point pt, point a, point b) {
//		if (Math.abs(vect(a, b, pt)) < eps)
//			if (scal(a, b, pt) >= 0 && scal(b, a, pt) >= 0)
//				return true;
//			
//		return false;
//	}
	
	double vect(point p, point p1, point p2) {
		return (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y);
	}
	
	double scal(point p, point p1, point p2) {
		return (p1.x - p.x) * (p2.x - p.x) + (p1.y - p.y) * (p2.y - p.y);
	}
	
	void clear() {
		size = 0;
	}
	
	void add(point pt) {
		p[size].set(pt);
		++size;
	}
	
	point[] stack;
	int top;
	
	point zero;
	void prepare() {
//		--size;
//		p[size] = new point(-1, -1);
		stack = new point[1001];
		zero = new point(0, 0); //!!! 0, 0
		mid = new point(-1, -1);
		rand = new Random();
	}
	
	void build() {
		int pt = 0;
		for (int i = 1; i < size; i++)
			if (p[i].x > p[pt].x || (Math.abs(p[i].x - p[pt].x) < eps && p[i].y < p[pt].y))
				pt = i;
		
		point mediary = p[0];
		p[0] = p[pt];
		p[pt] = mediary;
		
		p[size] = new point(p[0].x, p[0].y); 
		
		rand = new Random();
		sort(1, size - 1);
		
		stack[0] = p[0];
		stack[1] = p[1];
		
		top = 2;
		int i = 1;
		do {
			++i;
			while (vect(stack[top - 2], stack[top - 1], p[i]) < 0)
				--top;
			
			stack[top++] = p[i];
		} while (i < size);
	}
	
	double dist(point a, point b) {
		return Math.hypot(a.x - b.x, a.y - b.y);
	}
	
	Random rand;
	double buffer;
	point mid, tmp;
	void sort(int l, int r) {
		int i = l, j = r;
		mid.set(p[l + rand.nextInt(r - l + 1)]);
		while (i <= j) {
			for (; (buffer = vect(p[0], mid, p[i])) < 0 || (Math.abs(buffer) < eps && dist(p[0], p[i]) < dist(p[0], mid)); ++i);
			for (; (buffer = vect(p[0], mid, p[j])) > 0 || (Math.abs(buffer) < eps && dist(p[0], mid) < dist(p[0], p[j])); --j);
			if (i <= j) {
				tmp = p[i];
				p[i] = p[j];
				p[j] = tmp;
				
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
	
	double area() {
		if (size <= 2) //!!!!!!!!!!!!!
			return 0;
		
		build();
		
		double ans = 0;
		for (int i = 1; i < top; i++)
			ans += vect(zero, stack[i - 1], stack[i]);
			
		return Math.abs(ans) / 2;
	}
}