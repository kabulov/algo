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
		
		int n = in.nextInt();
		int src = in.nextInt() - 1, dst = in.nextInt() - 1;
		if (src == dst) {
			out.println(0);
			out.close();
			return;
		}
		
		int m = in.nextInt();
		
		class edge {
			int e, tb, te;
			edge(int tf, int s, int ts) {
				e = s;
				tb = tf;
				te = ts;
			}
		}
		
		ArrayList<edge>[] map = new ArrayList[n];
		for (int i = 0; i < n; i++)
			map[i] = new ArrayList<edge>();
		
		int node, aaa, bbb, ccc;
		for (int i = 0; i < m; ++i) {
			node = in.nextInt() - 1;
			aaa = in.nextInt();
			bbb = in.nextInt() - 1;
			ccc = in.nextInt();
			if (node == bbb) continue;
			map[node].add(new edge(aaa, bbb, ccc));
		}
		
		boolean[] used = new boolean[n];
		Arrays.fill(used, false);
		used[src] = true;
		
		int[] d = new int[n];
		Arrays.fill(d, inf);
		d[src] = 0;
		
		int min = inf, pos;
		while (true) {
			for (int i = 0; i < map[src].size(); ++i) 
				if (d[src] <= map[src].get(i).tb && !used[map[src].get(i).e]) 
					if (map[src].get(i).te < d[map[src].get(i).e]) {
						d[map[src].get(i).e] = map[src].get(i).te;
					}
					
			min = inf;
			pos = -1;
			for (int i = 0; i < n; i++)
				if (!used[i] && d[i] < min) {
					min = d[i];
					pos = i;
				}
			
			if (min == inf) break;
			used[pos] = true;
			if (pos == dst) break;
			src = pos;
		}
		
		out.println(used[dst] ? d[dst] : -1);
		out.close();
	}
	
	static int inf = (int)1e6 + 1;
}