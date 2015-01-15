import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int m = nextInt();
		
		win = new byte[201][201][101];
		for (int i = 0; i <= 200; ++i)
			for (int j = 0; j <= 100; ++j)
				for (int k = 0; k <= 100; ++k)
					win[i][j][k] = FREE;
		
		win[0][0][0] = FAILS;
		solve(0, n, m);
		
		if (win[0][n][m] == WINS) {
			out.println(1);
		} else {
			out.println(2);
		}
		
		out.close();
	}
	
	static final byte FREE = 0;
	static final byte WINS = 1;
	static final byte FAILS = 2;
	
	static byte[][][] win;
	static void solve(int f, int s, int t) {
		if (f > 0) {
			if (win[f - 1][s][t] == FREE)
				solve(f - 1, s, t);
			if (win[f - 1][s][t] == FAILS) {
				win[f][s][t] = WINS;
				return;
			}
		}
		
		if (s > 0) {
			if (win[f][s - 1][t] == FREE)
				solve(f, s - 1, t);
			if (win[f][s - 1][t] == FAILS) {
				win[f][s][t] = WINS;
				return;
			}
			if (win[f + 1][s - 1][t] == FREE)
				solve(f + 1, s - 1, t);
			if (win[f + 1][s - 1][t] == FAILS) {
				win[f][s][t] = WINS;
				return;
			}
		}
		
		if (t > 0) {
			if (win[f][s][t - 1] == FREE)
				solve(f, s, t - 1);
			if (win[f][s][t - 1] == FAILS) {
				win[f][s][t] = WINS;
				return;
			}
			if (win[f + 1][s][t - 1] == FREE)
				solve(f + 1, s, t - 1);
			if (win[f + 1][s][t - 1] == FAILS) {
				win[f][s][t] = WINS;
				return;
			}
			if (win[f][s + 1][t - 1] == FREE)
				solve(f, s + 1, t - 1);
			if (win[f][s + 1][t - 1] == FAILS) {
				win[f][s][t] = WINS;
				return;
			}
		}
		
		win[f][s][t] = FAILS;
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}