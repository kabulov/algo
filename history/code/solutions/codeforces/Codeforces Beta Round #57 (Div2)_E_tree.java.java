import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;


public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	public void run() {
		try {
			in = new Input(System.in, 2 * 1024);
			out = new PrintWriter(System.out);
			solve();			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	Input in;
	PrintWriter out;
	
	void solve() throws IOException {
		int n = in.nextInt();
		pair[] v = new pair[n];
		for (int i = 0; i < n; ++i)
			v[i] = new pair(i, in.nextInt());
		
		Arrays.sort(v, new Comparator<pair>() {
			public int compare(pair o1, pair o2) {
				return o1.v - o2.v;
			}
		});
		
		for (int i = 0; i < n; ++i)
			v[i].v = i;
		
		Arrays.sort(v, new Comparator<pair>() {
			public int compare(pair o1, pair o2) {
				return o1.p - o2.p;
			}
		});
		
		tree amt = new tree(n);
		tree dp = new tree(n);	
		long ans = 0;
		
		for (int i = 0, t; i < n; ++i) {
			t = v[i].v;
			ans += dp.sum(1, 0, n - 1, t + 1, n - 1);//   sum(t + 1, n);
			dp.upd(1, 0, n - 1, t, amt.sum(1, 0, n - 1, t + 1, n - 1));//sum(t + 1, n));
			amt.upd(1, 0, n - 1, t, 1);//put(t, 1);
		}
		
		out.println(ans);
	}
}

class tree {
	int sz;
	long[] t;
	
	tree(int size) {
		sz = size;
		t = new long[4 * sz];
		Arrays.fill(t, 0);
	}
	
	void upd(int v, int lt, int rt, int pos, long nval) {
		if (lt == rt)
			t[v] = nval;
		else {
			int mt = (lt + rt) >>> 1;
			if (pos <= mt) 
				upd(v + v, lt, mt, pos, nval);
			else
				upd(v + v + 1, mt + 1, rt, pos, nval);
			t[v] = t[v + v] + t[v + v + 1];
		}		
	}
	
	long sum(int v, int lt, int rt, int l, int r) {
		if (l > r) return 0;
		if (lt == l && rt == r) return t[v];
		int mt = (lt + rt) >>> 1;
		if (r <= mt) return sum(v + v, lt, mt, l, r);
		if (mt + 1 <= l) return sum(v + v + 1, mt + 1, rt, l, r);
		return sum(v + v, lt, mt, l, mt) + sum(v + v + 1, mt + 1, rt, mt + 1, r);
	}
}

class pair {
	int p, v;
	pair (int pos, int val) {
		p = pos;
		v = val;
	}
}

class Input {
	char[] buffer;
	int bufpos;
	int bufsize;
	
	int number;
	
	char c;
	
	InputStreamReader in;
	
	Input(InputStream inn, int bufsiz) throws IOException {
		in = new InputStreamReader(inn); 
		
		buffer = new char[bufsiz];
		bufsize = in.read(buffer);
		
		bufpos = 0;
		c = buffer[0];
	}
	
	int nextInt() throws IOException {
		number = 0;
		boolean neg = false;
		
		while (!('0' <= c && c <= '9')) {
			if (c == '-') neg = true;
			
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
		
		if (neg) return -number;
		return number;
	}
}

//...
//Input in = new Input("input.txt", 2 * 1024);
//...
