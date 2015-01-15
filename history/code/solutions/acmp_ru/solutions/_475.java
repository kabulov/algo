import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int len = 0, tmp;
		long[] v = new long[100000];
		for (; (tmp = nextInt()) != -1; v[len++] = tmp);
		
		if (len <= 2) {
			out.println("Yes");
			out.close();
			return;
		}
		
		rand = new Random();
		sort(v, 0, len - 1);
		
		long d = v[1] - v[0];
		boolean yes = true;
		for (int i = 2; i < len; ++i)
			if (v[i - 1] + d != v[i]) {
				yes = false;
				break;
			}
		
		if (yes)
			out.println("Yes");
		else
			out.println("No");
		
		out.close();
	}
	
	static Random rand;
	static long mid, tmp;
	
	static void sort(long[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j; ) {
			for (; v[i] < mid; ++i);
			for (; mid < v[j]; --j);
			if (i <= j) {
				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;
				++i;
				--j;
			}
		}
		if (l < j) sort(v, l, j);
		if (i < r) sort(v, i, r);
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		if (in.ttype == StreamTokenizer.TT_EOF)
			return -1;
		return (int)in.nval;
	}
}
