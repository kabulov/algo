import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		while (n % 2 == 0)
			n /= 2;
		
		int q = 1;
		if (n > 1) {
			n /= 2;
			int p = 1;
			while (n > 0) {
				if (n % 2 == 0)
					p += q;
				else
					q += p;
				n /= 2;
			}
		}
		
		out.println(q);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
