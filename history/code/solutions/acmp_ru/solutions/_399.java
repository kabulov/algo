import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();
		
		n = Integer.parseInt(str.split(" ")[0]);
		m = Integer.parseInt(str.split(" ")[1]);
		map = new int[n][m];
		
		for (int i = 0; i < n; ++i) {
			str = in.readLine();
			for (int j = 0; j < m; ++j) map[i][j] = str.charAt(j) == '@' ? -1 : 0;
		}
		
		r = -1;
		if (offer(map)) {
			r = 0;
			
			int i, j;
			i = j = 1;
			
			int iv, jv;
			iv = jv = 0;
			
			while (!(i == n - 2 && j == m - 2)) {
				++map[i][j];
				
				int amt = 0, val = Integer.MAX_VALUE;
				
				for (int k = 0; k < 4; ++k) { 
					int ci = i+vect[k][0];
					int cj = j+vect[k][1];
					
					if (map[ci][cj] == -1) continue; 

					if (map[ci][cj] < val) {
						val = map[ci][cj];
						amt = 1;
					} else
					if (map[ci][cj] == val)
						++amt;
				}
				
				if (amt == 1) {
					for (int k = 0; k < 4; ++k) { 
						int ci = i+vect[k][0];
						int cj = j+vect[k][1];
						
						if (map[ci][cj] == val) {
							iv = vect[k][0];
							jv = vect[k][1];
							i = ci;
							j = cj;
							break;
						}
					}
				} else {
					if (map[i+iv][j+jv] == val) {
						i += iv;
						j += jv;
					} else {
						for (int k = 0; k < 4; ++k) {
							int ci = i+vect[k][0];
							int cj = j+vect[k][1];
							
							if (map[ci][cj] == val) {
								iv = vect[k][0];
								jv = vect[k][1];
								i = ci;
								j = cj;
								break;
							}
						}
					}
				}
				
				++r;
			}
		}
		
		out.println(r);
		out.close();
	}
	
	static int[][] vect = {
		{1, 0},
		{0, 1},
		{-1, 0},
		{0, -1}
	};
	
	static boolean offer(int[][] map) {
		boolean[][] v = new boolean[n][m];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j)
				v[i][j] = map[i][j] == -1 ? true : false;
		
		int[] iv, jv;
		iv = new int[n*m];
		jv = new int[n*m];
		
		int h = 1, t = 0;
		iv[0] = jv[0] = 1;
		v[1][1] = true;
		
		int i, j;
		while (t < h) {
			i = iv[t];
			j = jv[t];
			++t;
			
			if (!v[i][j-1]) {
				v[i][j-1] = true;
				iv[h] = i;
				jv[h] = j-1;
				++h;
			}

			if (!v[i-1][j]) {
				v[i-1][j] = true;
				iv[h] = i - 1;
				jv[h] = j;
				++h;
			}

			if (!v[i+1][j]) {
				if (i + 1 == n - 2 && j == m - 2) return true;
				v[i+1][j] = true;
				iv[h] = i + 1;
				jv[h] = j;
				++h;
			}
			
			if (!v[i][j+1]) {
				if (i == n - 2 && j + 1 == m - 2) return true;
				v[i][j+1] = true;
				iv[h] = i;
				jv[h] = j + 1;
				++h;
			}
		}
		
		return false;
	}
	
	static int r;
	static int n, m;
	static int[][] map;
}