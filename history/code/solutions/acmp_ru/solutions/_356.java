import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
			
		int bound = nextInt();
		bound = nextInt() - bound;

		int[] max = new int[bound + 1];
		for (int i = 1; i <= bound; i++)
			max[i] = Integer.MIN_VALUE;
		
		int[] min = new int[bound + 1];
		for (int i = 1; i <= bound; i++)
			min[i] = Integer.MAX_VALUE;
		
		min[0] = max[0] = 0;
		
		int n = nextInt();
		int[] w = new int[n];
		int[] p = new int[n];
		
		for (int i = 0; i < n; i++) {
			p[i] = nextInt();
			w[i] = nextInt();
		}
			
		for (int i = 0; i < n; i++) {
			for (int j = 0; j + w[i] <= bound; j++) {
				if (min[j] != Integer.MAX_VALUE) {
					min[j + w[i]] = Math.min(min[j + w[i]], min[j] + p[i]);
					max[j + w[i]] = Math.max(max[j + w[i]], max[j] + p[i]);
				}
			}
		}
		
		if (bound == 0 || min[bound] == Integer.MAX_VALUE) {
			out.println("This is impossible.");
		} else {
			out.println(min[bound] + " " + max[bound]);
		}
		
		out.close();
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
	static PrintWriter out;
}