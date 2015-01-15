import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = nextInt();
		int k = nextInt();
		
		int[] pos = new int[1001];
		int[] neg = new int[1001];
		
		Arrays.fill(pos, 0);
		Arrays.fill(neg, 0);
		
		for (int i = 0; i < n; ++i) {
			int val = nextInt();			
			if (val > 0) 
				++pos[val];
			else
				++neg[-val];
		}
		
		int[] ps = new int[1001];
		int[] ng = new int[1001];
		
		Arrays.fill(ps, 0);
		Arrays.fill(ng, 0);
		
		for (int i = 0; i < n - 1; ++i) {
			int val = nextInt();
			if (val > 0) 
				++ps[val];
			else
				++ng[-val];
		}

		int[] amt = new int[1001];
		Arrays.fill(amt, 0);
		for (int i = 0; i < k; ++i) ++amt[nextInt()];
		
		int p = 1;
		for (int i = 1; i < 1001; ++i) 
			if (pos[i] + neg[i] != ps[i] + ng[i]) {
				p = i;
				break;
			}
		
		out.println((amt[p] + pos[p] - ps[p]) % 2 == 0 ? -p : p);
		out.close();
	}
	
	static StreamTokenizer in;

	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
