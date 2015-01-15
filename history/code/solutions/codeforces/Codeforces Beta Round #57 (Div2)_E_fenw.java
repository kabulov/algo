import java.io.IOException;
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
			in = new StreamTokenizer(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	void solve() throws IOException {
		int n = nextInt();
		pair[] v = new pair[n];
		for (int i = 0; i < n; ++i)
			v[i] = new pair(i + 1, nextInt());
		
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
		
		fenw amt = new fenw(n);
		fenw dp = new fenw(n);
		long ans = 0;
		
		for (int i = 0, t; i < n; ++i) {
			t = v[i].v;
			ans += dp.sum(t + 1, n);
			dp.put(t, amt.sum(t + 1, n));
			amt.put(t, 1);
		}
		
		out.println(ans);
	}
}

class fenw {
	int sz;
	long[] v;
	fenw(int size) {
		sz = size;
		v = new long[sz + 1];
		Arrays.fill(v, 0);
	}
	
	void put(int pos, long val) {
		for (; pos <= sz; pos = pos | (pos + 1))
			v[pos] += val;
	}
	
	long sum(int lt, int rt) {
		return get(rt) - get(lt - 1);
	}
	
	long get(int rt) {
		long ans = 0;
		for (; rt > 0; rt = (rt & (rt + 1)) - 1)
			ans += v[rt];
		return ans;
	}
}

class pair {
	int p, v;
	pair (int pos, int val) {
		p = pos;
		v = val;
	}
}
