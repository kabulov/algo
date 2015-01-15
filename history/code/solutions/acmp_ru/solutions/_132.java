import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int a = in.nextInt() - 1, b = in.nextInt() - 1;
		
		if (a == b) {
			out.println(0);
			out.close();
			return;
		}
		
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = in.nextInt();
		
		int[] v = new int[n];
		int[] d = new int[n];
		
		Arrays.fill(d, inf);
		for (int i = 0; i < n; i++)
			v[i] = i;
		
		d[0] = 0;
		v[0] = a;
		v[a] = 0;
		
		int pos = 0, dst, mp = 0;
		while (pos < n - 1) {
			mp = pos + 1;
			for (int i = pos + 1; i < n; i++) {
				if ((dst = map[v[pos]][v[i]]) != -1)
					d[i] = Math.min(d[i], d[pos] + dst);
				if (d[i] < d[mp]) 
					mp = i;
			}
			if (d[mp] == inf) 
				break;
			else {
				if (v[mp] == b) break;
				++pos;
				dst = v[pos];
				v[pos] = v[mp];
				v[mp] = dst;
				dst = d[pos];
				d[pos] = d[mp];
				d[mp] = dst;
			}
		}
		
		out.println(d[mp] == inf ? -1 : d[mp]);
		out.close();
	}
	
	static int inf = (int)1e4 + 1;
}