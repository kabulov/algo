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
import java.util.Arrays;
//import java.util.LinkedList;
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
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		String str;
		char[][] adv = new char[n][m];
		for (int i = 0; i < n; i++) {
			str = in.readLine();
			for (int j = 0; j < m; j++)
				adv[i][j] = str.charAt(j);
		}
		
		boolean[][] matrix = new boolean[8][4];
		for (int i = 0; i < 8; i++)
			Arrays.fill(matrix[i], true);
		
		matrix[0][0] = matrix[0][1] = matrix[0][2] = false;
		matrix[1][0] = matrix[1][1] = false;
		matrix[2][0] = matrix[2][2] = false;
		matrix[3][0] = false;
		matrix[4][1] = matrix[4][2] = false;
		matrix[5][1] = false;
		matrix[6][2] = false;
		
		
		int row, col = 0;//unnecessary
		boolean bool = true;
outer:	for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				row = Integer.parseInt(st.nextToken());
				switch(adv[i][j]) {
				case 'R':
					col = 0;
					break;
				case 'G':
					col = 1;
					break;
				case 'B':
					col = 2;
					break;
				case '.':
					col = 3;
					break;
				}
				if (!matrix[row][col]) {
					bool = false;
					break outer;
				}
			}
		}
		
		if (bool)
			out.println("YES");
		else
			out.println("NO");
	}
	
	
	BufferedReader in;
	PrintWriter out;
//	static Scanner in;
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
