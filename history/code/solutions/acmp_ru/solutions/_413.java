import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.io.StreamTokenizer;
//import java.util.Formatter;
//import java.util.Locale;
//import java.util.Arrays;
//import java.util.Scanner;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main implements Runnable {
	public static void main(String[] args) throws IOException {
		new Thread(new Main()).start();
	} 
	  
	public void run() {
		try {
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	public void solve() throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
//		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		in = new BufferedReader(new FileReader("input.txt"), 4096);
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		String str;
		list = new LinkedList<psn>();
		for (int i = 0; i < n; i++) {
			str = in.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = str.charAt(j);
		}
		
		int answer = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[i][j] == '#') {
					answer++;
					clear(i, j);
				}
		
		out.println(answer);
	}
	
	LinkedList<psn> list;
	public void clear(int i, int j) {
		map[i][j] = '.';
		list.addLast(new psn(i, j));
		
		psn pos;
		while (!list.isEmpty()) {
			pos = list.getFirst();
			list.removeFirst();
			i = pos.i;
			j = pos.j;
			if (i > 0 && map[i - 1][j] == '#') {
				map[i - 1][j] = '.';
				list.addLast(new psn(i - 1, j));
			}
			if (j > 0 && map[i][j - 1] == '#') {
				map[i][j - 1] = '.';
				list.addLast(new psn(i, j - 1));
			}
			if (i < (n - 1) && map[i + 1][j] == '#') {
				map[i + 1][j] = '.';
				list.addLast(new psn(i + 1, j));
			}
			if (j < (m - 1) && map[i][j + 1] == '#') {
				map[i][j + 1] = '.';
				list.addLast(new psn(i, j + 1));
			}
		}
		
	}
	
	public class psn {
		int i, j;
		psn(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	int n, m;
	char[][] map;
		
	BufferedReader in;
//	static Scanner in;
	PrintWriter out;
//	static StreamTokenizer in;

//	public static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	public static long nextLong() throws IOException {
//		in.nextToken();
//		return (long)in.nval;
//	}
	
//	public static boolean hasNext() throws IOException {
//		in.nextToken();
//		if (in.ttype == StreamTokenizer.TT_EOL || in.ttype == StreamTokenizer.TT_EOF)
//			return false;
//		return true;
//	}
}
