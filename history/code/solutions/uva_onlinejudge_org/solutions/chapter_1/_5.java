import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("input.txt")
		out = new PrintWriter(System.out);
		
		map = new char[252][252];
		n = m = 0;
		
		String[] regex;
		String buf = in.readLine();
		
		Point pnt;
		list = new LinkedList<Point>();
		
		char c;
		int i, j, i1, i2, j1, j2;
		while (buf.charAt(0) != 'X') {
			if (n == 0) { //m == 0
				if (buf.charAt(0) == 'S') 
					out.println(buf.split(" ")[1]);

				if (buf.charAt(0) != 'I') {
					buf = in.readLine();
					continue;
				}
			}
			
			regex = buf.split(" ");
			switch(buf.charAt(0)) {
			case 'I':
				m = Integer.parseInt(regex[1]);
				n = Integer.parseInt(regex[2]);

				if (m < 1 || n < 1) {
					m = n = 0;
					buf = in.readLine();
					continue;
				}
				
				for (i = 0; i <= n + 1; i++)
					map[i][0] = map[i][m + 1] = '\0';
				for (j = 0; j <= m + 1; j++)
					map[0][j] = map[n + 1][j] = '\0';
					
				clear();
				break;
			case 'C':
				clear();
				break;
			case 'L':
				j = Integer.parseInt(regex[1]);
				i = Integer.parseInt(regex[2]);
				c = regex[3].charAt(0);
				if (proper(i, j))
					map[i][j] = c;
				break;
			case 'V':
				j = Integer.parseInt(regex[1]);
				i1 = Integer.parseInt(regex[2]);
				i2 = Integer.parseInt(regex[3]);
				c = regex[4].charAt(0);
				if (i1 > i2) {
					i = i1;
					i1 = i2;
					i2 = i;
				}
				for (i = i1; i <= i2; ++i) 
					if (proper(i, j))
						map[i][j] = c;
				break;
			case 'H':
				j1 = Integer.parseInt(regex[1]);
				j2 = Integer.parseInt(regex[2]);
				if (j1 > j2) {
					j = j1;
					j1 = j2;
					j2 = j;
				}
				i = Integer.parseInt(regex[3]);
				c = regex[4].charAt(0);
				for (j = j1; j <= j2; ++j) 
					if (proper(i, j))
						map[i][j] = c;
				break;
			case 'K':
				j1 = Integer.parseInt(regex[1]);
				i1 = Integer.parseInt(regex[2]);
				j2 = Integer.parseInt(regex[3]);
				i2 = Integer.parseInt(regex[4]);
				c = regex[5].charAt(0);
				for (i = i1; i <= i2; ++i)
					for (j = j1; j <= j2; ++j)
						if (proper(i, j))
							map[i][j] = c;
				break;
			case 'F':
				j = Integer.parseInt(regex[1]);
				i = Integer.parseInt(regex[2]);
				c = regex[3].charAt(0);
				if (proper(i, j) && c != map[i][j]) {
					prev = map[i][j];
					map[i][j] = c;
					list.add(new Point(i, j));
					while (!list.isEmpty()) {
						pnt = list.poll();
						i = pnt.x;
						j = pnt.y;
						check(i + 1, j, c);
						check(i - 1, j, c);
						check(i, j + 1, c);
						check(i, j - 1, c);
					}
				}
				break;
			case 'S':
				out.println(regex[1]);
				print();
				break;
			}
			buf = in.readLine();
		}
		
		out.close();
	}
	
	static char prev;
	static LinkedList<Point> list;
	static void check(int i, int j, char c) { 
		if (map[i][j] == prev) {
			map[i][j] = c;
			list.add(new Point(i, j));
		}
	}
	
	static char[][] map;
	static int n, m;
	
	static boolean proper(int i, int j) {
		return (1 <= i && i <= n) && (1 <= j && j <= m);
	}
	
	static void clear() {
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				map[i][j] = 'O';
	}
	
	static PrintWriter out;

	static void print() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) out.print(map[i][j]);
			out.println();
		}
	}
}
