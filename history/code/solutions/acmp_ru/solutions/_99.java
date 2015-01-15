import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();
		String[] buf = str.split(" ");
		
		int h, m, n;
		h = Integer.parseInt(buf[0]);
		m = Integer.parseInt(buf[1]);
		n = Integer.parseInt(buf[2]);
		
		int bi, bj;
		int ei, ej;
		bi = bj = ei = ej = 0;
		
		int[][][] map = new int[h + 2][m + 2][n + 2];
		for (int i = 0; i < m + 2; ++i)
			for (int j = 0; j < n + 2; ++j)
				map[0][i][j] = map[h + 1][i][j] = 0;
				
		for (int k = 1; k <= h; ++k) {
			for (int i = 0; i < m + 2; ++i)
				map[k][i][0] = map[k][i][n + 1] = 0;
			for (int j = 0; j < n + 2; ++j)
				map[k][0][j] = map[k][m + 1][j] = 0;
		}
		
		for (int k = 1; k <= h; ++k) {
			for (int i = 1; i <= m; ++i) {
				str = in.readLine();
				for (int j = 1; j <= n; ++j)
					switch(str.charAt(j - 1)) {
					case '.':
						map[k][i][j] = -1;
						break;
					case 'o':
						map[k][i][j] = 0;
						break;
					case '1':
						map[k][i][j] = 0;
						bi = i;
						bj = j;
						break;
					case '2':
						map[k][i][j] = -1;
						ei = i;
						ej = j;
						break;
					}
			}
			in.readLine();
		}
		
		int[] kv, iv, jv;
		kv = new int[h * n * m];
		iv = new int[h * n * m];
		jv = new int[h * n * m];
		
		int rem = 0;
		int add = 1;
		
		kv[0] = 1;
		iv[0] = bi;
		jv[0] = bj;
		
		int k, i, j, s;
outer:	while (rem < add) {
			k = kv[rem];
			i = iv[rem];
			j = jv[rem];
			s = map[k][i][j];
			
			for (int p = 0; p < 5; ++p) {
				nk = k + way[p][0];
				ni = i + way[p][1];
				nj = j + way[p][2];
				
				if (map[nk][ni][nj] == -1) {
					map[nk][ni][nj] = s + 1;
					kv[add] = nk;
					iv[add] = ni;
					jv[add] = nj;
					if (nk == h && ni == ei && nj == ej) break outer;
					++add;
				}
			}
			
			++rem;
		}
		
		out.println(5 * map[kv[add]][iv[add]][jv[add]]);
		out.close();
	}
	
	static int nk, ni, nj;
	static int[][] way = {
		{1, 0, 0},
		{0, 1, 0},
		{0, -1, 0},
		{0, 0, 1},
		{0, 0, -1}
	};
}