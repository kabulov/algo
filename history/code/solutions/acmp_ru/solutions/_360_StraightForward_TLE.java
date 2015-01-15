import java.io.BufferedReader;
//import java.io.File;
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
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
//		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		in = new BufferedReader(new FileReader("input.txt"), 4096);
		
		int n = Integer.parseInt(in.readLine());
		int[][] map = new int[n + 4][n + 4];
		
		for (int i = 0; i < n + 2; i++) {
			map[0][i] = -1000;
			map[1][i] = -1000;
			map[n + 2][i] = -1000;
			map[n + 3][i] = -1000;
			map[i][0] = -1000;
			map[i][1] = -1000;
			map[i][n + 2] = -1000;
			map[i][n + 3] = -1000;
		}
		
		StringTokenizer st;
		for (int i = 2; i <= n + 1; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 2; j <= n + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int tmp, buf;
		int max = -1000;
		int lu, ld, ru, rd;
		int left, right, up, down;
		for (int i = 2; i < n + 2; i++)
			for (int j = 2; j < n + 2; j++) {
				buf = map[i][j];
				left = map[i][j - 1];
				right = map[i][j + 1];
				up = map[i - 1][j];
				down = map[i + 1][j];
				lu = map[i - 1][j - 1];
				ld = map[i + 1][j - 1];
				ru = map[i - 1][j + 1];
				rd = map[i + 1][j + 1];
				
//				tmp = buf + up + map[i - 2][j];
//				if (tmp > max) max = tmp;
				
				tmp = buf + down + map[i + 2][j];
				if (tmp > max) max = tmp;

//				tmp = buf + left + map[i][j - 2];
//				if (tmp > max) max = tmp;
				
				tmp = buf + right + map[i][j + 2];
				if (tmp > max) max = tmp;

//				tmp = buf + left + lu;
//				if (tmp > max) max = tmp;

//				tmp = buf + up + lu;
//				if (tmp > max) max = tmp;

//				tmp = buf + up + ru;
//				if (tmp > max) max = tmp;

//				tmp = buf + right + ru;
//				if (tmp > max) max = tmp;

				tmp = buf + left + ld;
				if (tmp > max) max = tmp;

				tmp = buf + down + ld;
				if (tmp > max) max = tmp;

				tmp = buf + down + rd;
				if (tmp > max) max = tmp;

				tmp = buf + right + rd;
				if (tmp > max) max = tmp;
			}
		
		out.println(max);
		out.close();
	} 
	  
	static BufferedReader in;
//	static Scanner in;
	static PrintWriter out;
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
