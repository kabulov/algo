import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int K = nextInt();
		
		int[][] inp = new int[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				inp[i][j] = nextInt();
		
		int[][] map = new int[n + 2][n + 2];
		for (int i = 0; i < n + 2; i++)
			for (int j = 0; j < n + 2; j++)
				map[i][j] = 0;
		
		int diag, min, i, j, cst = 2 * n - 1, tmp, buf;
		for (int k = 1; k <= K; ++k) {
//			diag = 2 - (k % 2);
			if (k % 2 == 0)
				diag = 2;
			else
				diag = 1;
			
//			min = Math.min(2 * n - 1, k);
			if (k < cst)
				min = k;
			else
				min = cst;
			
			for (; diag <= min; diag += 2) {
//				i = Math.min(diag, n);
				if (diag < n)
					i = diag;
				else
					i = n;
				
//				j = Math.max(1, diag - n + 1);
				if (diag > n)
					j = diag - n + 1;
				else
					j = 1;
				
				while (i >= 1 && j <= n) {
//					map[i][j] += Math.max(Math.max(map[i - 1][j], map[i + 1][j]), Math.max(map[i][j - 1], map[i][j + 1]));
					tmp = map[i - 1][j];
					if ((buf = map[i + 1][j]) > tmp)
						tmp = buf;
					if ((buf = map[i][j - 1]) > tmp)
						tmp = buf;
					if ((buf = map[i][j + 1]) > tmp)
						tmp = buf;
					map[i][j] = tmp + inp[i - 1][j - 1];
					--i;
					++j;
				}
			}
		}
		
		int answer = 0;
		for (i = 1; i <= n; ++i)
			for (j = 1; j <= n; ++j)
				if (map[i][j] > answer)
					answer = map[i][j];
		
		out.println(answer);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
