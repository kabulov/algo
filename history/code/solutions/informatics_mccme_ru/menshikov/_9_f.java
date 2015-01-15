import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		n = in.nextInt();
		inp = new rect[n];
		
		for (int i = 0; i < n; i++) {
			inp[i] = new rect(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
//			if (inp[i].ld.x == inp[i].ru.x || inp[i].ld.y == inp[i].ru.y) {
//				while (true);//check
//			}
//			no points and lines, all rectangles have positive(!) areas (>= 1)
		}
		
		int[] x = new int[2 * n];
		int[] y = new int[2 * n];
		int xlen, ylen;
		xlen = ylen = 0;
		
		for (int i = 0; i < n; i++) {
			x[xlen++] = inp[i].ld.x;
			x[xlen++] = inp[i].ru.x;
			y[ylen++] = inp[i].ld.y;
			y[ylen++] = inp[i].ru.y;
		}
		
		rand = new Random();
		sort(x, 0, xlen - 1);
		sort(y, 0, ylen - 1);
		
		xlen = ylen = 1;
		for (int i = 1; i < 2 * n; i++) {
			if (x[i] != x[i - 1]) 
				x[xlen++] = x[i];
			
			if (y[i] != y[i - 1]) 
				y[ylen++] = y[i];
		}
		
		int isz = 2 * ylen + 3, jsz = 2 * xlen + 3;
		map = new boolean[isz][jsz];
		
		for (int i = 1; i < isz - 1; i++) 
			for (int j = 1; j < jsz - 1; j++)
				map[i][j] = false;
		
		for (int i = 0; i < isz; i++)
			map[i][0] = map[i][jsz - 1] = true;
		
		for (int j = 0; j < jsz; j++)
			map[0][j] = map[isz - 1][j] = true;
		
		for (int j = 0; j < xlen; j++) {
			tmp = 2 * (j + 1);
			for (int i = 2; i < isz - 2; i++)//1 .. isz - 1
				map[i][tmp] = true;
		}
			
		for (int i = 0; i < ylen; i++) {
			tmp = 2 * (i + 1);
			for (int j = 2; j < jsz - 2; j++)//1 .. jsz - 1
				map[tmp][j] = true;
		}
		
		//schitaem chto tochki ne imeyut znacheniya
		for (int j = 0; j < xlen; j++) 
			for (int i = 1; i < ylen; i++) 
				if (!yline(y[i - 1], y[i], x[j])) 
					map[2 * i + 1][2 * (j + 1)] = false;

		
		for (int i = 0; i < ylen; i++) 
			for (int j = 1; j < xlen; j++)
				if (!xline(x[j - 1], x[j], y[i])) 
					map[2 * (i + 1)][2 * j + 1] = false;
			
		int answer = 0;
		LinkedList<point> list = new LinkedList<point>();
		
		point buf;
		int ic, jc;
		
		for (int i = 1; i < isz - 1; i++)
			for (int j = 1; j < jsz - 1; j++)
				if (!map[i][j]) {
					++answer;
					map[i][j] = true;
					list.add(new point(i, j));
					while (!list.isEmpty()) {
						buf = list.poll();
						ic = buf.x;
						jc = buf.y;
						if (!map[ic - 1][jc]) {
							map[ic - 1][jc] = true;
							list.add(new point(ic - 1, jc));
						}
						if (!map[ic + 1][jc]) {
							map[ic + 1][jc] = true;
							list.add(new point(ic + 1, jc));
						}
						if (!map[ic][jc - 1]) {
							map[ic][jc - 1] = true;
							list.add(new point(ic, jc - 1));
						}
						if (!map[ic][jc + 1]) {
							map[ic][jc + 1] = true;
							list.add(new point(ic, jc + 1));
						}
					}
				}
		
		out.println(answer);
		out.close();
	}
	
	static int n;
	static rect[] inp;
	static boolean[][] map;
	
	static boolean xline(int l, int r, int y) {
		for (int i = 0; i < n; i++) { 
			if (inp[i].ld.y != y && inp[i].ru.y != y)
				continue;
			
			if (inp[i].ld.x <= l && r <= inp[i].ru.x)
				return true;
		}
		
		return false;
	}
	
	static boolean yline(int d, int u, int x) {
		for (int i = 0; i < n; i++) {
			if (inp[i].ld.x != x && inp[i].ru.x != x)
				continue;
			
			if (inp[i].ld.y <= d && u <= inp[i].ru.y)
				return true;
		}
		
		return false;
	}
	
	static Random rand;
	static int mid, tmp;
	static void sort(int[] v, int l, int r) {
		int i = l, j = r;
		int mid = v[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			for (; v[i] < mid; ++i);
			for (; mid < v[j]; --j);
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

//areas > 0 always
class rect {
	point ld, ru;
	
	rect(int a1, int b1, int a2, int b2) {
		ld = new point(a1, b1);
		ru = new point(a2, b2);
		normalize();
	}
	
	void normalize() {
		int tmp;
		if (ld.x > ru.x) {
			tmp = ld.x;
			ld.x = ru.x;
			ru.x = tmp;
		}
		
		if (ld.y > ru.y) {
			tmp = ld.y;
			ld.y = ru.y;
			ru.y = tmp;
		}
	}
}

class point {
	int x, y;
	
	point(int a, int b) {
		x = a;
		y = b;
	}
}