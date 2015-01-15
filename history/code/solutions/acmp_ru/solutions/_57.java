import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int c = in.nextInt();
		long p = in.nextLong();
		
		int[] x, y;
		x = new int[n];
		y = new int[n];
		
		for (int i = 0; i < n; ++i) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
		}
		
		double[] dist = new double[n];
		for (int i = 0; i < n; ++i) {
			dist[i] = 0;
			for (int j = 0; j < n; ++j) dist[i] += hypot(x[i] - x[j], y[i] - y[j]);
		}
		
		int xnet, ynet;
		xnet = in.nextInt();
		ynet = in.nextInt();
		
		boolean flag = false;
		for (int i = 0; i < n; ++i)
			if (c * (dist[i] + hypot(x[i] - xnet, y[i] - ynet)) < p + eps) {
				flag = true;
				break;
			}
		
		out.println(flag ? "YES" : "NO");
		out.close();
	}

	static double hypot(double a, double b) {
		return Math.sqrt(a * a + b * b);
	}
	
	static double eps = 1e-14;
}