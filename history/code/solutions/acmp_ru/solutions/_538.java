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
		x = new int[n];
		y = new int[n];
		
		for (int i = 0; i < n; ++i) {
			x[i] = nextInt();
			y[i] = nextInt();
		}
		
		col = new int[n];
		size = new int[n];
		que = new short[n * n];
		list = new short[n][n];

		double lt = 0, rt = 3 * 1e4; // > 2*sqrt(2)1e4 // 3 * 1e4 //potentially
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
	
	static void BuildList() {
		double rad2 = rad * 2;

		Arrays.fill(size, 0);
		for (int i = 0; i < n; ++i) //n - 1
			for (int j = i + 1; j < n; ++j) {
				if ((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]) < (rad2 - eps) * (rad2 - eps)) { 			//potentially
					list[i][size[i]] = (short)j;
					list[j][size[j]] = (short)i;
					++size[i];
					++size[j];
				}
			}
	}
	
	static boolean noProblem() {
		Arrays.fill(col, 0);
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
							col[end] = 3 - col[node];
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
	static int[] x, y;
	
	static int n;
	static int[] col;
	static int[] size;
	static short[][] list;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
