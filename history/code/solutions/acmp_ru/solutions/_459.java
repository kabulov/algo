import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String inp = in.readLine();
		int len = inp.length();
		
		Arrays.fill(map, -1);
		map[zero] = 0;
		
		int n = 1;
		list = new int[201][4];
		for (int i = 0; i < 200; i++) 
			Arrays.fill(list[i], -1);
		
		int x, y, p, node, prev;
		x = y = p = node = 0;
		char c = '\0';
		for (int i = 0; i < len; i++) { 
			switch(inp.charAt(i)) {
			case 'N':
				++y;
				c = 'S';
				break;
			case 'E':
				++x;
				c = 'W';
				break;
			case 'S':
				--y;
				c = 'N';
				break;
			case 'W':
				--x;
				c = 'E';
				break;
			}
			prev = p;
			p = pos(x, y);
			if (map[p] == -1) {
				map[p] = n;
				n++;
			}
			p = map[p];
			switch(c) {
			case 'N':
				node = 0;
				break;
			case 'E':
				node = 1;
				break;
			case 'S':
				node = 2;
				break;
			case 'W':
				node = 3;
				break;
			}
			list[p][node] = prev;
		}
		
		if (p == 0) {
			//out.println(); //important?
			out.close();
			return;
		}
		
		was = new boolean[201];
		Arrays.fill(was, false);
		
		que = new int[201];
		par = new int[201];
		path = new char[201];
		
		was[p] = true;
		par[0] = -1;
		que[0] = p;
		//path[0] = '\0';
		
		add = 1;
		rem = 0;

outer:	while (rem < add) {
			for (int i = 0; i < 4; i++) {
				node = list[que[rem]][i];
				if (node != -1 && !was[node]) {
					que[add] = node;
					par[add] = rem;
					path[add] = way(i);
					if (node == 0)  break outer;
					was[node] = true;
					++add;
				}
			}
			++rem;
		}
		
		StringBuilder ans = new StringBuilder();
		while (par[add] != -1) {
			ans.append(path[add]);
			add = par[add];
		}
		
		out.println(ans.reverse());
		out.close();
	}
	
	static char way(int i) {
		switch(i) {
		case 0: return 'N';
		case 1: return 'E';
		case 2: return 'S';
		default: return 'W';
		}
	}
	
	static char[] path;
	static boolean[] was;
	static int add, rem;
	static int[] que;
	static int[] par;
	
	static int[][] list;
	static int[] map = new int[2 * 80400 + 1];
	
	static int zero = 80400;
	static int pos(int x, int y) {
		return zero + x * 401 + y;
		//return base + hash(x, y);
		//hash(x, y) = x * 401 + y;
		//base == 80400 - half amount of various points (x, y), -200 <= x, y <= 200;
	}
}