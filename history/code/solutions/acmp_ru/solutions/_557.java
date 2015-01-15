//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileReader;
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
//		in = new StreamTokenizer(new FileReader("input.txt"));
//		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		File fin = new File("input.txt");
		size = (int)fin.length();
		blen = 1024 * 1024;
		buffer = new byte[blen];
		cursize = 0;
		pos = 0;
		
		
		fr = new FileInputStream(fin);
		fr.read(buffer);
		
		c = buffer[0];
		int N = nextInt();
		int K = nextInt();
		int I = nextInt();
		int J = nextInt();
		int P = nextInt();
		
		int[][] inp = new int[K][K];
		int[] fst = new int[K];
		int[] scd = new int[K];
		int cur = 0;
		
		I--; 
		J--;
		
		for (int i = 0; i < K; i++)
			if (i != I)
				for (int j = 0; j < K; j++)
					nextInt();
			else {
				for (int j = 0; j < K; j++)
					fst[j] = nextInt();
			}
			
		int tmp;
		for (int loop = 1; loop < N; loop++) {
			for (int i = 0; i < K; i++) {
				for (int j = 0; j < K; j++)
					inp[i][j] = nextInt();
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
	
	FileInputStream fr;
	byte[] buffer;
	int blen;
	int pos;
	int size;
	int cursize;
	
	int c;
	int nextInt() throws IOException {		
		while (!(48 <= c && c <= 57)) {
			pos++;
			if (pos == blen) { //>=
				cursize += blen;
				fr.read(buffer);
				pos = 0;
			}
			c = buffer[pos];
		}
		
		int result = 0;
		do {
			result = result * 10 + c - 48;
			pos++;
			if (pos == blen) {
				cursize += blen;
				if (cursize == size)
					break;
				fr.read(buffer);
				pos = 0;
			}
			c = buffer[pos];
			if (!(48 <= c && c <= 57))
				break;
		} while (true);
		
		return result;
	}
//	BufferedReader in;
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
