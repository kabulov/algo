import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		int fx = nextInt();
		int fy = nextInt();
		
		int px;
		int py;
		
		long amt = 0;
		
		int x = fx, y = fy;
		for (int i = 2; i <= n; i++) {
			px = x; py = y;
			x = nextInt();
			y = nextInt();
			amt += gcd(Math.abs(x - px), Math.abs(y - py));
		}
		
		out.println(amt + gcd(Math.abs(x - fx), Math.abs(y - fy)));
		out.close();
	}
	
	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
