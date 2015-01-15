
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution implements Runnable {
	public static void main(String[] argv) {
		new Thread(new Solution()).start();
	}
	
	StreamTokenizer in;
	PrintWriter out;
	
	String FileName = "stupid_rmq";
	
	public void run() {
		try {
			in = new StreamTokenizer(new FileReader(FileName + ".in"));
			out = new PrintWriter(FileName + ".out");
//			in = new StreamTokenizer(new FileReader("in.txt"));
//			out = new PrintWriter("out.txt");
			solve();			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	void solve() throws IOException {
		int n = next();
		int[] v = new int[n + 1];
		for (int i = 1; i <= n; ++i) v[i] = next();
		
		int m = next();
		for (int i = 0, lt, rt, mn; i < m; ++i) {
			lt = next();
			rt = next();
			mn = v[lt];
			for (int j = lt + 1; j <= rt; ++j)
				if (v[j] < mn)
					mn = v[j];
			out.println(mn);
		}
	}	
}
