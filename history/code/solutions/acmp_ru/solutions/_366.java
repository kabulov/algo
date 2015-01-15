import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		N = nextInt();
		S = nextInt();

		vect = new int[N];
		for (int i = 0; i < N; i++)
			vect[i] = nextInt();
		
		sgn = new boolean[N - 1];
		solve(0, vect[0]);
		
		out.println("No solution");
		out.close();
	}
	
	static void solve(int pos, int sum) {
		if (pos == N - 1) {
			if (sum == S) {
				out.print(vect[0]);
				for (int i = 0; i < N - 1; i++) {
					if (sgn[i])
						out.print("+");
					else
						out.print("-");
					out.print(vect[i + 1]);
				}
				out.println("=" + S);
				out.close();
				System.exit(0);
			}
			return;
		}
		
		sgn[pos] = true;
		solve(pos + 1, sum + vect[pos + 1]);
		sgn[pos] = false;
		solve(pos + 1, sum - vect[pos + 1]);
	}
	
	static int N;
	static int S;
	static int[] vect;
	static boolean[] sgn;
	static PrintWriter out;
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}