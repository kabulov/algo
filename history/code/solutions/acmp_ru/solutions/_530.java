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
		int m = Integer.parseInt(st.nextToken());
		int n= Integer.parseInt(st.nextToken());
		
		String str;
		char[][] fst = new char[n][m];
		for (int i = 0; i < n; i++) {
			str = in.readLine();
			for (int j = 0; j < m; j++)
				fst[i][j] = str.charAt(j);
		}
			
		char[][] scd = new char[n][m];
		for (int i = 0; i < n; i++) {
			str = in.readLine();
			for (int j = 0; j < m; j++)
				scd[i][j] = str.charAt(j);
		}
		
		str = in.readLine();
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (fst[i][j] == '0') {
					if (scd[i][j] == '0') {
						fst[i][j] = str.charAt(0);
					} else {
						fst[i][j] = str.charAt(1);
					}
				} else {
					if (scd[i][j] == '0') {
						fst[i][j] = str.charAt(2);
					} else {
						fst[i][j] = str.charAt(3);
					}
				}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				out.print(fst[i][j]);
			out.println();
		}
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
