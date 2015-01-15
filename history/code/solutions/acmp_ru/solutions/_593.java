import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		tower[] v = new tower[n];
		
		for (int i = 0; i < n; ++i) 
			v[i] = new tower(in.nextInt(), in.nextInt(), i);
		
		rand = new Random();
		sort(v, 0, n - 1, 
			new Comparator<tower>() {
				public int compare(tower a, tower b) {
					return (int)(a.x - b.x);
				}
			}	
		);
		
		long x, y;
		for (int i = 0; i < n - 1; i++) {
			++v[i].amt;
			++v[i + 1].amt;
			
			x = v[i + 1].x;
			y = v[i + 1].y;
			
			for (int j = i + 2; j < n; ++j) 
				if (vect(v[i].x, v[i].y, x, y, v[j].x, v[j].y) >= 0) {
					++v[i].amt;
					++v[j].amt;
					x = v[j].x;
					y = v[j].y;
				}
		}
		
		sort(v, 0, n - 1, 
			new Comparator<tower>() {
				public int compare(tower a, tower b) {
					return a.ord - b.ord;
				}
			}
		);
		
		for (int i = 0; i < n; ++i) 
			out.println(v[i].amt);
		
		out.close();
	}
	
	static long vect(long x1, long y1, long x2, long y2, long x3, long y3) {
		return (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
	}
	
	static tower mid, tmp;
	static Random rand;
	
	static void sort(tower[] v, int l, int r, Comparator<tower> com) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; com.compare(v[i], mid) < 0; ++i);
			for (; com.compare(mid, v[j]) < 0; --j);
			if (i <= j) {
				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;				
				
				++i;
				--j;
			}
		}
		if (l < j) sort(v, l, j, com);
		if (i < r) sort(v, i, r, com);
	}
}

class tower {
	long x, y;
	int amt, ord;
	
	tower(int a, int b, int pos) {
		x = a;
		y = b;
		ord = pos;
		amt = 0;
	}
}