import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		
		long h2, h1 = 0, h;
		long min2, min1, min = 0;
		
		h2 = nextInt();
		if (n > 1)
			h1 = nextInt();
			
		if (n == 2) {
			min = Math.abs(h1 - h2);
		} else {
			min2 = 0;
			min1 = Math.abs(h1 - h2);
			for (int i = 3; i <= n; i++) {
				h = nextInt();
				min = Math.min(min1 + Math.abs(h - h1), min2 + 3 * Math.abs(h - h2));
				min2 = min1;
				min1 = min;
				h2 = h1;
				h1 = h;
			}
		}
		
		out.println(min);		
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
