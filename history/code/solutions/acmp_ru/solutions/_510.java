import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] argv) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		
		if (n % 2 == 0) { 
			int s = 1;
			int f = 1;
			
			int tmp;
			for (int i = 2; i <= n; i +=2) {
				tmp = f + 2 * s;
				s += tmp;
				f = tmp;
			}
			
			out.println(f);
		} else
			out.println(0);
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
