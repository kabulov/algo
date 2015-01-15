import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		m = in.nextInt();
		n = in.nextInt();
		
		inp = new int[n + 2][m + 2];
		for (int i = 1; i <= n; ++i) {
			inp[i][0] = inp[i][m + 1] = 0;
			String str = in.next();
			for (int j = 0; j < m; ++j)
				inp[i][j + 1] = str.charAt(j) == 'X' ? 1 : 0;
		}
		for (int j = 0; j < m + 2; ++j)
			inp[0][j] = inp[n + 1][j] = 0;
		
		m += 2;
		n += 2;
		
		wave = new int[n + 2][m + 2];
		for (int i = 0; i < n + 2; ++i)
			wave[i][0] = wave[i][m + 1] = -1;
		for (int j = 0; j < m + 2; ++j)
			wave[0][j] = wave[n + 1][j] = -1;
		
		iv = new int[n * m];
		jv = new int[n * m];
		
		for (;;) {
			j1 = in.nextInt() + 1;
			if (j1 == 1) break;					//look for bugs here
			i1 = in.nextInt() + 1;
			j2 = in.nextInt() + 1;
			i2 = in.nextInt() + 1;
			clear();
			run();
			out.println(wave[i2][j2] == inf ? 0 : wave[i2][j2]);
		}
		
		out.close();
	}
	
	static void run() {
		wave[i1][j1] = 0;
		wave[i2][j2] = inf;
		add = 1;
		rem = 0;
		iv[0] = i1;
		jv[0] = j1;
		int i, j;
		while (rem < add) {
			i = iv[rem];
			j = jv[rem];
			offer(i - 1, j, wave[i][j]);
			offer(i + 1, j, wave[i][j]);
			offer(i, j - 1, wave[i][j]);
			offer(i, j + 1, wave[i][j]);
			if (Math.abs(i - i2) + Math.abs(j - j2) == 1) break;
			++rem;
		}
	}
	
	static void offer(int i, int j, int len) {
		if (wave[i][j] == inf) {
			wave[i][j] = len + 1;
			iv[add] = i;
			jv[add] = j;
			++add;
		}
	}
	
	static int[] iv, jv;
	static int rem, add;
	
	static void clear() {
		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= m; ++j)
				wave[i][j] = inp[i - 1][j - 1] == 1 ? -1 : inf;
	}
	
	static int inf = (int)1e6;
	
	static int i1, j1, i2, j2;
	static int[][] wave;
	static int[][] inp;
	static int n, m;
}