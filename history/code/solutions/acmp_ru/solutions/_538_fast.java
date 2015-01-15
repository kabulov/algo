import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = nextInt();
		x = new short[n];
		y = new short[n];
		
		double max = 0;
		for (int i = 0; i < n; ++i) {
			x[i] = (short)nextInt();
			y[i] = (short)nextInt();
			if (Math.abs(x[i]) > max)
				max = Math.abs(x[i]);
			if (Math.abs(y[i]) > max)
				max = Math.abs(y[i]);
		}
		
		col = new short[n];
		size = new short[n];
		que = new short[n];
		list = new short[n][n];
		tlist = new short[n][n];
		tsize = new short[n];
		
		PrepareList(3 * max);
		
		double lt = 0, rt = 3 * max; // > 2*sqrt(2)1e4 // 3 * 1e4 //potentially
		while (rt - lt > bound) {			//potentially
			rad = (lt + rt) / 2;
			
			BuildList();
			
			if (noProblem())
				lt = rad;
			else
				rt = rad;
		}
		
		rad = lt;
		BuildList();
		noProblem();
		
		out.println(lt);
		for (int i = 0; i < n; ++i) 
			out.print(col[i] + " ");
		out.close();
	}
	
	static double prev;
	static void PrepareList(double init) {
		prev = init;
		for (int i = 0; i < n; ++i) {
			tsize[i] = 0;
			for (int j = i + 1; j < n; ++j) {
				list[i][size[i]] = (short)j;
				list[j][size[j]] = (short)i;
				++size[i];
				++size[j];
			}
		}
	}
	
	static short[][] tlist;
	static short[] tsize;
	
	static void BuildList() {
		short e;
		int k, s;
		double rad2 = rad * 2;
		if (rad < prev) {
			for (int i = 0; i < n; ++i) {
				k = 0;
				s = size[i];
				for (int j = 0; j < s; ++j) {
					e = list[i][j];
					if ((rad2 + eps) * (rad2 + eps) < (x[i] - x[e]) * (x[i] - x[e]) + (y[i] - y[e]) * (y[i] - y[e])) {
						tlist[i][tsize[i]] = e;
						++tsize[i];
						++k;
					} else
						list[i][j - k] = list[i][j];
				}
				size[i] -= k;
			}
		} else {
			for (int i = 0; i < n; ++i) {
				k = 0;
				s = tsize[i];
				for (int j = 0; j < s; ++j) {
					e = tlist[i][j];
					if ((rad2 - eps) * (rad2 - eps) > (x[i] - x[e]) * (x[i] - x[e]) + (y[i] - y[e]) * (y[i] - y[e])) {
						list[i][size[i]] = e;
						++size[i];
						++k;
					} else
						tlist[i][j - k] = tlist[i][j];
				}
				tsize[i] -= k;
			}
		}
		prev = rad;
	}
	
	static boolean noProblem() {
		Arrays.fill(col, (short)0);
		for (int i = 0; i < n; ++i)
			if (col[i] == 0) {
				col[i] = 1;
				add = 1;
				rem = 0;
				que[0] = (short)i;
				
				int node, sz, end;
				while (rem < add) {
					node = que[rem];
					sz = size[node];
					for (int j = 0; j < sz; ++j) {
						end = list[node][j];
						if (col[end] == 0) {
							col[end] = (short)(3 - col[node]);
							que[add] = (short)end;
							++add;
						} else
						if (col[end] == col[node])
							return false;
					}
					++rem;
				}
			}
		return true;
	}
	
	static int add, rem;
	static short[] que;
	
	static double bound = 1e-10;
	static double eps = 1e-14;

	static double rad;
	static short[] x, y;
	
	static int n;
	static short[] col;
	static short[] size;
	static short[][] list;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
