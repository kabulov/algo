import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		in.nextToken();
		int n = (int)in.nval;
		v = new int[n];
		p = new int[n];
		for (int i = 0; i < n; ++i) {
			p[i] = i;
			in.nextToken();
			v[i] = (int)in.nval;
		}
		
		rand = new Random();
		sort(0, n - 1);
		
		int mlen = 1, len = 1, min = v[0];
		for (int i = 1; i < n; i++)
			if (v[i] == v[i - 1])
				len++;
			else {
				if (len > mlen) {
					mlen = len;
					min = v[i - 1];
				}
				
				len = 1;
			}
		
		if (len > mlen) {
			min = v[n - 1];
		}
		
		int[] vect = new int[n];
		for (int i = 0; i < n; i++)
			vect[p[i]] = v[i];
		
		len = 0;
		for (int i = 0; i < n; i++)
			if (vect[i] != min) {
				out.print(vect[i] + " ");
				len++;
			}
		
		for (int i = len; i < n; i++)
			out.print(min + " ");
		
		out.close();
	}
	
	static int[] v, p;
	
	static Random rand;
	static int mid, tmp;
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
}
