import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		n = Integer.parseInt(in.readLine());
		
		map = new char[n][n][n];
		
		int bk = 0, bi = 0, bj = 0;
		
		char c;
		String str;
		for (int k = 0; k < n; k++) {
			in.readLine();
			for (int i = 0; i < n; i++) {
				str = in.readLine();
				for (int j = 0; j < n; j++) {
					c = map[k][i][j] = str.charAt(j);
					if (c == 'S') {
						if (k == 0)
							end(0);
						
						bk = k;
						bi = i;
						bj = j;
					}
				}
			}
		}
		
		loc tmp;
		int i, j, k, d;
		
		list = new LinkedList<loc>();
		list.add(new loc(bk, bi, bj, 0));
		
		while (!list.isEmpty()) {
			tmp = list.poll();
			i = tmp.i;
			j = tmp.j;
			k = tmp.k;
			d = tmp.d + 1;
			
			offer(k + 1, i, j, d);
			offer(k - 1, i, j, d);
			offer(k, i + 1, j, d);
			offer(k, i - 1, j, d);
			offer(k, i, j + 1, d);
			offer(k, i, j - 1, d);
		}
		
	}
	
	static boolean bound(int k, int i, int j) {
		return k < 0 || k >= n || i < 0 || i >= n || j < 0 || j >= n; 
	}
	
	static void end(int dist) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		out.println(dist);
		out.close();
		System.exit(0);
	}
	
	static void offer(int k, int i, int j, int d) throws IOException {
		if (!bound(k, i, j) && map[k][i][j] == '.') {
			if (k == 0) 
				end(d);
			
			map[k][i][j] = 'S';
			list.add(new loc(k, i, j, d));
		}
	}
	
	static int n;
	static LinkedList<loc> list;
	static char[][][] map;
}

class loc {
	int k, i, j, d;
	loc(int kk, int ii, int jj, int dist) {
		k = kk;
		i = ii;
		j = jj;
		d = dist;
	}
}
