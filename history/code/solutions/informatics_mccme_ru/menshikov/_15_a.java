import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		
		int ans = 0;
		int len = Integer.MAX_VALUE;
		
		int tmp;
		for (int i = 1; i < 10; i++) {
			tmp = offer(i, n);
			if (tmp < len) {
				len = tmp;
				ans = i;
			}
		}
		
		if (ans == 0)
			out.println("Impossible");
		else
			out.println(ans + " " + len);
		
		out.close();
	}
	
	static int offer(int d, int n) {
		d %= n;
		int s = 0, ten = 1;
		for (int i = 1; i <= n; i++) {
			s += (ten * d) % n;
			s %= n;
			if (s == 0)
				return i;
			ten = (ten * 10) % n;
		}
		
		return Integer.MAX_VALUE;
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}