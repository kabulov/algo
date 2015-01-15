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
		
		char[][] map = new char[n + 2][m + 2];
		for (int i = 0; i < n + 2; i++) 
			map[i][0] = map[i][m + 1] = '.';
			
		for (int i = 0; i < m + 2; i++) 
			map[0][i] = map[n + 1][i] = '.';
		
		String str;
		for (int i = 1; i <= n; i++) {		
			str = in.readLine();
			for (int j = 1; j <= m; j++)
				map[i][j] = str.charAt(j - 1);
		}
		
		int answer = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				if (map[i][j] != '*')
					if (map[i + 1][j] == '.' && map[i - 1][j] == '.' && map[i][j + 1] == '.' && map[i][j - 1] == '.')
						answer++;
		
		out.println(answer);
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
