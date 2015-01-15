import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int m = Integer.parseInt(str.split(" ")[1]);
		
		map = new char[n + 2][m + 2];
		for (int i = 0; i < n + 2; i++)
			map[i][0] = map[i][m + 1] = '.';
		
		for (int j = 0; j < m + 2; j++)
			map[0][j] = map[n + 1][j] = '.';
		
		for (int i = 1; i <= n; i++) {
			str = in.readLine();
			for (int j = 1; j <= m; j++)
				map[i][j] = str.charAt(j - 1);
		}
		
		int ans = 0;
		for (int ii = 1; ii <= n; ii++)
			for (int jj = 1; jj <= m; jj++)
				if (map[ii][jj] == '#') {
					++ans;
					i = ii;
					j = jj;
					paint();
				}
		
		out.println(ans);
		out.close();
	}
	
	static int i, j;
	static char[][] map;

	static void paint() {
		if (map[i][j] != '#')
			return;

		map[i][j] = '.';
		
		++i;
		paint();
		--i;
		
		--i;
		paint();
		++i;
		
		++j;
		paint();
		--j;
		
		--j;
		paint();
		++j;
	}
}
