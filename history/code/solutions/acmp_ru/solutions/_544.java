import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		
		long pppp, ppp, pp, p;
		ppp = pp = 0;
		p = 1;
		
		for (int i = 2; i <= n + 1; i++) {
			pppp = ppp;
			ppp = pp;
			pp = p;
			p = pp + ppp + pppp;
		}
		
		out.println(p);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
