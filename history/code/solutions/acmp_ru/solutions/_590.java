import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		pnt mid = new pnt(0, 0, 0);
		pnt[] v = new pnt[n];
		
		for (int i = 0; i < n; ++i) {
			v[i] = new pnt(n * in.nextInt(), n * in.nextInt(), n * in.nextInt());
			mid.x += v[i].x;
			mid.y += v[i].y;
			mid.z += v[i].z;
		}
		
		mid.x /= n;
		mid.y /= n;
		mid.z /= n;
		
		pnt[] mir = new pnt[n];
		for (int i = 0; i < n; ++i) 
			mir[i] = new pnt(mid.x - (v[i].x - mid.x), mid.y - (v[i].y - mid.y), mid.z - (v[i].z - mid.z));
		
		rand = new Random();
		sort(v, 0, n - 1);
		sort(mir, 0, n - 1);
		
		boolean yes = true;
		for (int i = 0; i < n; ++i) 
			if (!v[i].equals(mir[i])) {
				yes = false;
				break;
			}
		
		if (yes)
			out.println("Yes");
		else
			out.println("No");
		
		out.close();
	}
	
	static pnt m, tmp;
	static Random rand;
	static void sort(pnt[] v, int l, int r) {
		int i = l, j = r;
		m = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; v[i].cmp(m) < 0; ++i);
			for (; m.cmp(v[j]) < 0; --j);
			if (i <= j) {
				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;
				++i;
				--j;
			}
		}
		if (l < j) sort(v, l, j);
		if (i < r) sort(v, i, r);
	}
}

class pnt {
	long x, y, z;
	pnt(long a, long b, long c) {
		x = a;
		y = b;
		z = c;
	}
	
	long cmp(pnt arg) {
		if (x != arg.x)
			return x - arg.x;
		
		if (y != arg.y)
			return y - arg.y;
		
		return z - arg.z;
	}
	
	public boolean equals(pnt arg) {
		return x == arg.x && y == arg.y && z == arg.z;
	}
}