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
		v = new int[n];
		for (int i = 0; i < n; ++i)
			v[i] = nextInt();
		
		rand = new Random();
		sort(0, n - 1);
		
		for (int i = 0; i < n; ++i)
			out.print(v[i] + " ");
				
		out.close();
	}
	
	static int[] v;
	static Random rand;
	static int mid, tmp;
	static void sort(int l, int r) {
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
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
