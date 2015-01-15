import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));

		n = nextInt();
		int[] x = new int[2 * n];
		int[] y = new int[2 * n];
		inp = new rect[n];
		
		int xlen = 0, ylen = 0;
		
		for (int i = 0; i < n; i++) { 
			inp[i] = new rect(nextInt(), nextInt(), nextInt(), nextInt());
			x[xlen++] = inp[i].xld;
			x[xlen++] = inp[i].xru;
			y[ylen++] = inp[i].yld;
			y[ylen++] = inp[i].yru;
		}

		
		Arrays.sort(x);
		int k = 0;
		for (int i = 1; i < xlen; i++) {
			if (x[i] == x[i - 1])
				++k;
			else
				x[i - k] = x[i];
		}
		xlen -= k;
		
		Arrays.sort(y);
		k = 0;
		for (int i = 1; i < ylen; i++) {
			if (y[i] == y[i - 1])
				++k;
			else
				y[i - k] = y[i];
		}
		ylen -= k;
		
		int answer = 0;
		for (int i = 1; i < xlen; i++)
			for (int j = 1; j < ylen; j++) {
				if (in(x[i - 1], y[j - 1], x[i], y[j]))
					answer += (x[i] - x[i - 1]) * (y[j] - y[j - 1]);
			}
		
		out.println(answer);
		out.close();
	}
	
	static boolean in(int xl, int yl, int xr, int yr) {
		for (int i = 0; i < n; i++)
			if (inp[i].xld <= xl && xr <= inp[i].xru && inp[i].yld <= yl && yr <= inp[i].yru)
				return true;
		
		return false;
	}
	
	static rect[] inp;
	static int n;
	
	static PrintWriter out;
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class rect {
	int xld, yld, xru, yru;
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
		
		xld = x1;
		yld = y1;
		xru = x2;
		yru = y2;
	}
}