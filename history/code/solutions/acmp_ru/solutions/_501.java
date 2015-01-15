//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.math.BigInteger;
import java.io.StreamTokenizer;
//import java.util.Formatter;
//import java.util.Locale;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.StringTokenizer;

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
		in = new StreamTokenizer(new FileReader("input.txt"));
//		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
//		in = new BufferedReader(new FileReader("input.txt"), 256 * 8192);

		int n = nextInt();
		rect[] vect = new rect[n];
		
		for (int i = 0; i < n; i++)
			vect[i] = new rect(nextInt(), nextInt(), nextInt(), nextInt());
		
		rect area = new rect(nextInt(), nextInt(), nextInt(), nextInt());
		int[][] map = new int[100][100];
		
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				map[i][j] = 0;
		
		for (int l = 0; l < n; l++) {
			for (int i = vect[l].y1; i < vect[l].y2; i++)
				for (int j = vect[l].x1; j < vect[l].x2; j++)
					if (area.y1 <= i && i < area.y2 && area.x1 <= j && j < area.x2)
						map[i][j]++;
		}
		
		int answer = 0;
		for (int i = area.y1; i < area.y2; i++)
			for (int j = area.x1; j < area.x2; j++)
				answer += map[i][j];
				
		out.println(answer);
	}
	
	class rect {
		int x1, y1, x2, y2;
		rect(int x1, int y1, int x2, int y2) {
			int tmp;
			if (x1 > x2) {
				tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			if (y1 > y2) {
				tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
	}
	
//	BufferedReader in;
	PrintWriter out;
//	static Scanner in;
	static StreamTokenizer in;

	public static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
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
