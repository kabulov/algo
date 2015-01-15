import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int m = nextInt();
		
		int[][] map = new int[n + 2][m + 2];
		for (int i = 0; i < n + 2; ++i)
			map[i][0] = map[i][m + 1] = wall;
			
		for (int j = 0; j < m + 2; ++j)
			map[0][j] = map[n + 1][j] = wall;
		
		boolean[][] was = new boolean[n + 2][m + 2];
		for (int i = 0; i < n + 2; ++i)
			for (int j = 0; j < m + 2; ++j)
				was[i][j] = true;

		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= m; ++j) {
				was[i][j] = false;
				map[i][j] = nextInt();
			}
		
		int ans = 0;
		
		int[] iv = new int[n * m];
		int[] jv = new int[n * m];
		
		int add, rem, ic, jc, tmp;
		
		for (int i = 1; i <= n; ++i) 
			for (int j = 1; j <= m; ++j)
				if (!was[i][j]) {
					was[i][j] = true;
					add = 1;
					rem = 0;
					iv[0] = i;
					jv[0] = j;
					
					boolean flag = true;
					while (rem < add) {
						ic = iv[rem];
						jc = jv[rem];
						tmp = map[ic][jc];

						if (map[ic - 1][jc] < tmp) flag = false;
						if (map[ic - 1][jc] == tmp && !was[ic - 1][jc]) {
							was[ic - 1][jc] = true;
							iv[add] = ic - 1;
							jv[add] = jc;
							++add;
						}
							
						if (map[ic + 1][jc] < tmp) flag = false;
						if (map[ic + 1][jc] == tmp && !was[ic + 1][jc]) {
							was[ic + 1][jc] = true;
							iv[add] = ic + 1;
							jv[add] = jc;
							++add;
						}
						
						if (map[ic][jc - 1] < tmp) flag = false;
						if (map[ic][jc - 1] == tmp && !was[ic][jc - 1]) {
							was[ic][jc - 1] = true;
							iv[add] = ic;
							jv[add] = jc - 1;
							++add;
						}
						
						if (map[ic][jc + 1] < tmp) flag = false;
						if (map[ic][jc + 1] == tmp && !was[ic][jc + 1]) {
							was[ic][jc + 1] = true;
							iv[add] = ic;
							jv[add] = jc + 1;
							++add;
						}
						
						++rem;
					}
					
					if (flag) ++ans;
				}
		
		out.println(ans);
		out.close();
	}
	
	static int wall = (int)1e4 + 1;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}