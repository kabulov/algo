import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		if (n == 1) {
			out.println(nextInt());
			out.close();
			return;
		}
		
		if (n == 2) {
			out.println(nextInt() + nextInt());
			out.close();
			return;
		}
		
		int[] v = new int[n];
		for (int i = 0; i < n; ++i)
			v[i] = nextInt();
		
		rand = new Random();
		sort(v, 0, n - 1);
		
		int max = v[n - 1] + v[n - 2], tmp = max, r = n - 1;
		
		for (int i = n - 3; i >= 0; --i) {
			if (v[i] + v[i + 1] < v[r]) {
				if (tmp > max)
					max = tmp;
				
				for (; v[i] + v[i + 1] < v[r]; --r)
					tmp -= v[r];
			}
			tmp += v[i];
		}
		
		if (tmp > max)
			max = tmp;
		
		out.println(max);
		out.close();
	}
	
	static Random rand;
	static int mid, tmp;
	
	static void sort(int[] v, int l, int r) {
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
		return (int)in.nval;
	}
}
