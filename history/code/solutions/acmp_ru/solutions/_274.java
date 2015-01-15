import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
	
		int K = nextInt();
		boolean[] fst = new boolean[10];
		boolean[] scd = new boolean[10];
		
		int f, s;
		boolean equal;
		for (int k = 0; k < K; ++k) {
			f = nextInt();
			s = nextInt();
			
			Arrays.fill(fst, false);
			Arrays.fill(scd, false);
			
			while (f > 0) {
				fst[f % 10] = true;
				f /= 10;
			}
			
			while (s > 0) {
				scd[s % 10] = true;
				s /= 10;
			}
			
			equal = true;
			for (int i = 0; i < 10; i++)
				if (fst[i] ^ scd[i]) {
					equal = false;
					break;
				}
			
			if (equal)
				out.println("YES");
			else
				out.println("NO");
		}
		
		out.close();
	}

	static PrintWriter out;
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
