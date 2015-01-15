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
//import java.util.Arrays;
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
		in = new BufferedReader(new FileReader("input.txt"), 256 * 8192);

		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		int I = Integer.parseInt(st.nextToken());
		int J = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(in.readLine());
		
		int[][] inp = new int[K][K];
		int[] fst = new int[K];
		int[] scd = new int[K];
		int cur = 0;
		
		I--; 
		J--;
		
		in.readLine();
		for (int i = 0; i < K; i++)
			if (i != I)
				in.readLine();
			else {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < K; j++)
					fst[j] = Integer.parseInt(st.nextToken());
			}
			
		int tmp;
		for (int loop = 1; loop < N; loop++) {
			in.readLine();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < K; j++)
					inp[i][j] = Integer.parseInt(st.nextToken());
			}
			
			if (cur == 0) {
				for (int j = 0; j < K; j++) {
					tmp = 0;
					for (int i = 0; i < K; i++)
						tmp += fst[i] * inp[i][j];
					scd[j] = tmp % P;
				}
				cur = 1;
			} else {
				for (int j = 0; j < K; j++) {
					tmp = 0;
					for (int i = 0; i < K; i++)
						tmp += scd[i] * inp[i][j];
					fst[j] = tmp % P;
				}
				cur = 0;
			}
		}
		
		if (cur == 0) {
			out.println(fst[J] % P);
		} else {
			out.println(scd[J] % P);
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
