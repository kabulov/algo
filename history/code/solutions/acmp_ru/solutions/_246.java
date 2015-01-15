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
		int cur, next = nextInt();
		for (int i = 1; i < n; i++) {
			cur = next;
			next = nextInt();
			if (cur > next || cur + 1 < next)
				++ans;
		}
		
		out.println(ans);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
