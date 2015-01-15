import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		x = new double[n];
		y = new double[n];
		
		for (int i = 0; i < n; ++i) {
			x[i] = in.nextDouble();
			y[i] = in.nextDouble();
		}
		
		map = new boolean[n][n];
		was = new boolean[n];
		
		for (int i = 0; i < n; ++i)
			map[i][i] = false;
		
		double lt = 0, rt = 4 * 1e4; //3 * 1e4 --> WA
		double bound = 1e-4; 	
		while (rt - lt > bound) {
			double r = (lt + rt) / 2;
			
			if (good(r))
				rt = r;
			else
				lt = r;
		}
		
		out.printf("%.2f", (lt + rt) / 2);
		out.close();
	}
	
	static boolean good(double rad) {
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j < n; ++j)
				map[i][j] = map[j][i] = cover(i, j, rad);
		
		Arrays.fill(was, false);
		
		bfs(0);
		
		for (int i = 0; i < n; ++i)
			if (!was[i])
				return false;
		
		return true;
	}
	
	static boolean[][] map;
	static boolean[] was;
	
	static void bfs(int i) {
		was[i] = true;
		for (int j = 0; j < n; ++j)
			if (map[i][j] && !was[j])
				bfs(j);
	}
	
	static int n;
	static double[] x, y;
	
	static boolean cover(int i, int j, double rad) {
		if ((rad - eps) * (rad - eps) > (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]))
			return true;
		
		return false;
	}
	
	static double eps = 1e-12;
}

