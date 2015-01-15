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
		p = new int[n];
		for (int i = 0; i < n; i++) {
			v[i] = nextInt();
			p[i] = i + 1;
		}
		
		rand = new Random();
		sort(0, n - 1);		
		
		int l = p[0], r = p[1];
		int min = v[1] - v[0];
		
		for (int i = 1; i < n - 1; i++) 
			if (v[i + 1] - v[i] < min) {
				min = v[i + 1] - v[i];
				l = p[i];
				r = p[i + 1];
			}
		
		out.println(min);
		out.println(l + " " + r);
		out.close();
	}
	
	static int[] v, p;
	static int mid, tmp;
	static Random rand;
	
	static void sort(int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; v[i] < mid; ++i);
			for (; mid < v[j]; --j);
			if (i <= j) {
				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;
				
				tmp = p[i];
				p[i] = p[j];
				p[j] = tmp;
				
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
