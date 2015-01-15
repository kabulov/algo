import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		
		int n = nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = nextInt();
	
		tree tr = new tree(n);
		tr.build(1, 0, n - 1, v);
		
		int m = nextInt();
		for (int i = 0; i < m; ++i) {
			in.nextToken();
			if (in.sval.charAt(0) == 'g') {
				out.print(tr.get(1, 0, n - 1, nextInt() - 1));
				out.print(" ");
			} else {
				tr.upd(1, 0, n - 1, nextInt() - 1, nextInt() - 1, nextInt());
			}
		}
		
		out.close();
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static PrintWriter out;
	static StreamTokenizer in;
}

class tree {
	int sz;
	long[] t;
	
	tree(int size) {
		sz = size;
		t = new long[4 * sz];
	}
	
	void build(int v, int lt, int rt, int[] src) {
		if (lt == rt)
			t[v] = src[lt];
		else {
			t[v] = 0;
			int mt = (lt + rt) >>> 1;
			build(v + v, lt, mt, src);
			build(v + v + 1, mt + 1, rt, src);
		}
	}
	
	long get(int v, int lt, int rt, int pos) {
		if (lt == rt) return t[v];
		int mt = (lt + rt) >>> 1;
		return t[v] + ((pos <= mt) ? get(v + v, lt, mt, pos) : get(v + v + 1, mt + 1, rt, pos));
	}
	
	void upd(int v, int lt, int rt, int l, int r, int add) {
		if (l == lt && r == rt) 
			t[v] += add;
		else {
			int mt = (lt + rt) >>> 1;
			if (r <= mt) 
				upd(v + v, lt, mt, l, r, add);
			else
			if (mt + 1 <= l) 
				upd(v + v + 1, mt + 1, rt, l, r, add);
			else {
				upd(v + v, lt, mt, l, mt, add);
				upd(v + v + 1, mt + 1, rt, mt + 1, r, add);
			}
		}
	}
}