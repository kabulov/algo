import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = nextInt();
		
		v = new int[3650];
		Arrays.fill(v, 0);
		last = cur = 1;
		
		for (int T = 0; T < t; T++) {
			int n = nextInt();
			int p = nextInt();
			
			clear();
			
			for (int P = 0; P < p; ++P) {
				int h = nextInt();
				
				for (int pos = -1 + h; pos < n; pos += h)
					put(pos);
			}
			
			int amt = 0;
			for (int i = 0; i < n; ++i) {
				if (get(i) && (i % 7 < 5)) {
					++amt;
				}
			}
			
			out.println(amt);
		}
		
		out.close();
	}
	
	static int last, cur;
	static int[] v;
		
	static void put(int pos) {
		v[pos] = ++cur;
	}
	
	static boolean get(int pos) {
		return v[pos] < last ? false : true;
	}
	
	static void clear() {
		last = ++cur;
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;		
	}
}

