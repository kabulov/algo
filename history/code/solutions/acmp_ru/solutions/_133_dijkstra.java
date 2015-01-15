import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		if (n == 1) {
			out.println(0);
			out.close();
			return;
		}
		
		int[] cost = new int[n];
		for (int i = 0; i < n; i++)
			cost[i] = in.nextInt();
			
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = inf;
		
		int a, b;
		int m = in.nextInt();
		for (int i = 0; i < m; ++i) {
			a = in.nextInt() - 1;
			b = in.nextInt() - 1;
			map[a][b] = cost[a];
			map[b][a] = cost[b];
		}
		
		int[] v = new int[n];
		int[] d = new int[n];
		
		for (int i = 0; i < n; i++) {
			d[i] = inf;
			v[i] = i;
		}
			
		d[0] = 0;
		int len = 0, mpos = 0;
		while (len < n - 1) {
			mpos = len + 1;
			for (int i = len + 1; i < n; i++) {
				d[i] = Math.min(d[i], d[len] + map[v[len]][v[i]]);
				if (d[i] < d[mpos]) mpos = i;
			}
			if (d[mpos] == inf) 
				break;
			else
			if (v[mpos] == n - 1)
				break;
			++len;
			int tmp = v[len];
			v[len] = v[mpos];
			v[mpos] = tmp;
			tmp = d[len];
			d[len] = d[mpos];
			d[mpos] = tmp;
		}
		
		out.println(d[mpos] == inf ? -1 : d[mpos]);
		out.close();
	}
	
	static int inf = (int)1e6 + 1;
}