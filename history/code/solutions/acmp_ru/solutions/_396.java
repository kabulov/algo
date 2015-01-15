import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int m = nextInt();
		
		int[] lt, rt;
		lt = new int[n];
		rt = new int[n];
		
		for (int i = 0; i < n; ++i) {
			lt[i] = nextInt();
			rt[i] = nextInt();
			
			if (lt[i] > rt[i]) {	//
				int tmp = lt[i];
				lt[i] = rt[i];
				rt[i] = tmp;
			}
		}
		
		Arrays.sort(lt);
		Arrays.sort(rt);
		
		int amt = 0;
		int cl = 0, cr = 0;
		
		v = new int[m];
		p = new int[m];
		for (int i = 0; i < m; ++i) v[p[i] = i] = nextInt();
		
		rand = new Random(); 
		sort(v, 0, m - 1);
		
		for (int i = 0; i < m; ++i) {
			while (cl < n && lt[cl] <= v[i]) {
				++amt;
				++cl;
			}
			
			while (cr < n && rt[cr] < v[i]) {
				--amt;
				++cr;
			}
			
			v[i] = amt;
		}
		
		int[] prn = new int[m];
		for (int i = 0; i < m; ++i) prn[p[i]] = v[i];
		
		for (int i = 0; i < m; ++i) { 
			out.print(prn[i]);
			out.print(" ");
		}
		
		out.close();
	}
	
	static Random rand;
	
	static void sort(int[] v, int left, int right) {
		int l = left, r = right;
		int mid = v[l + rand.nextInt(r - l + 1)];
		while (l <= r) {
			for (; v[l] < mid; ++l);
			for (; mid < v[r]; --r);
			if (l <= r) {
				int tmp = v[l];
				v[l] = v[r];
				v[r] = tmp;
				
				tmp = p[l];
				p[l] = p[r];
				p[r] = tmp;
				
				++l;
				--r;
			}
		}
		if (left < r) sort(v, left, r);
		if (l < right) sort(v, l, right);
	}
	
	static int[] v;
	static int[] p;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}