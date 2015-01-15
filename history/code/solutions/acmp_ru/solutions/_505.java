import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Input in = new Input("input.txt", 2 * 1024);
		PrintWriter out = new  PrintWriter("output.txt");

		l = in.nextInt();
		int m = in.nextInt();
		int n = in.nextInt();
		
		pattern = new int[m + 1][l + 1];
		
		for (int i = 0; i < m; ++i) { 
			pattern[i][l] = in.nextInt();
			for (int j = 0; j < l; ++j) pattern[i][j] = in.nextInt();
		}
		
		rand = new Random();
		sort(0, m - 1);
		
		int ok = 0, bad = 0, pos;
		buf = new int[l];
		
		for (int i = 0; i < l; ++i) pattern[m][i] = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < l; ++j) buf[j] = in.nextInt();
			pos = offer(0, m);
			if (pos >= 0) {
				out.println(pattern[pos][l]);
				++ok;
			} else {
				out.println("-");
				++bad;
			}
		}
		
		out.println("OK=" + ok + " BAD=" + bad);
		out.close();
	}	
	
	static int[][] pattern;
	
	static int offer(int lt, int rt) {
		int mid;
		while (rt - lt > 1) {
			mid = (lt + rt) / 2;
			if (compareTo(buf, pattern[mid]) < 0)
				rt = mid;
			else
				lt = mid;
		}		
		return compareTo(buf, pattern[lt]) == 0 ? lt : -1; //not found
	}
	
	static Random rand;	
	static int[] mid, buf;
	static void sort(int l, int r) {
		int i = l, j = r;
		mid = pattern[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			for(;compareTo(pattern[i], mid) < 0;++i);
			for(;compareTo(mid, pattern[j]) < 0;--j);
			if (i <= j) {
				buf = pattern[i];
				pattern[i] = pattern[j];
				pattern[j] = buf;
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
	
	static int l;
	
	static int compareTo(int[] f, int[] s) {
		for (int i = 0; i < l; ++i) { 
			if (f[i] > s[i]) return 1;
			if (f[i] < s[i]) return -1;
		}
		
		return 0;
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class Input {
	byte[] buffer;
	int bufpos;
	int bufsize;
	
	int number;
	
	byte c;
	
	FileInputStream in;
	
	Input(final String name, int bufsiz) throws IOException {
		in = new FileInputStream(new File(name)); 
		
		buffer = new byte[bufsiz];
		bufsize = in.read(buffer);
		
		bufpos = 0;
		c = buffer[0];
	}
	
	int nextInt() throws IOException {
		number = 0;
		
		while (!('0' <= c && c <= '9')) {
			++bufpos;
			if (bufpos >= bufsize) {
				bufsize = in.read(buffer);
				bufpos = 0;
			}
			c = buffer[bufpos];
		}
		
		do {
			number = number * 10 + c - 48;
			++bufpos;
			if (bufpos >= bufsize) {
				if ((bufsize = in.read(buffer)) <= 0)
					break;
				bufpos = 0;
			}
			c = buffer[bufpos];
		} while ('0' <= c && c <= '9');
		
		return number;
	}
}

//...
//Input in = new Input("input.txt", 2 * 1024);
//...
