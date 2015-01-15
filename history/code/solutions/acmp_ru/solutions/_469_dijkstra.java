import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		m = in.nextInt();
		
		if (n == 1 && m == 1) {
			out.println(in.nextInt());
			out.close();
			return;
		}
		
		int[][] map = new int[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				map[i][j] = in.nextInt();
		
		v = new int[n * m];
		d = new int[n * m];
		for (int i = 0; i < n * m; ++i) {
			v[i] = i;
			d[i] = inf;
		}
		
		d[0] = map[0][0];
		int pos = 0, dst = n * m - 1;
		int i1, j1, i2, j2;
		while (v[pos] != dst) {
			int min = pos + 1;
			i1 = v[pos] / m;
			j1 = v[pos] % m;
			for (int i = pos + 1; i <= dst; ++i) {
				i2 = v[i] / m;
				j2 = v[i] % m;
				if (Math.abs(i1 - i2) + Math.abs(j1 - j2) == 1) {
					d[i] = Math.min(d[i], d[pos] + map[i2][j2]);
				}
				if (d[i] < d[min]) {
					min = i;
				}
			}
			swap(pos + 1, min);
			++pos;
		}
		
		out.println(d[pos]);
		out.close();
	}
	
	static void swap(int a, int b) {
		int tmp = v[a];
		v[a] = v[b];
		v[b] = tmp;
		tmp = d[a];
		d[a] = d[b];
		d[b] = tmp;
	}
	
	static int[] v;
	static int[] d;
	static int inf = (int)1e6 + 1;
	static int n, m;
}