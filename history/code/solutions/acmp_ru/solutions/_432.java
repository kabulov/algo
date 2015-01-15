import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int m = Integer.parseInt(str.split(" ")[1]);
		
		char[][] map = new char[n + 2][m + 2];
		for (int i = 1; i <= n; i++) {
			str = in.readLine();
			for (int j = 1; j <= m; j++)
				map[i][j] = str.charAt(j - 1);
		}
		
		for (int i = 0; i < n + 2; i++) 
			map[i][0] = map[i][m + 1] = '.';
		for (int i = 0; i < m + 2; i++)
			map[0][i] = map[n + 1][i] = '.';
		
		LinkedList<point> list = new LinkedList<point>();
		
		point tmp;
		int ans = 0, ic, jc;
		for (int i = 1; i <= n; i++) 
			for (int j = 1; j <= m; j++)
				if (map[i][j] == '#') {
					++ans;
					
					map[i][j] = '.';
					list.add(new point(i, j));
					while (!list.isEmpty()) {
						tmp = list.poll();
						ic = tmp.i;
						jc = tmp.j;

						if (map[ic + 1][jc] == '#') {
							map[ic + 1][jc] = '.';
							list.add(new point(ic + 1, jc));
						}
						
						if (map[ic - 1][jc] == '#') {
							map[ic - 1][jc] = '.';
							list.add(new point(ic - 1, jc));
						}
						
						if (map[ic][jc + 1] == '#') {
							map[ic][jc + 1] = '.';
							list.add(new point(ic, jc + 1));
						}
						
						if (map[ic][jc - 1] == '#') {
							map[ic][jc - 1] = '.';
							list.add(new point(ic, jc - 1));
						}
					}
				}
		
		out.println(ans);
		out.close();
	}
}

class point {
	int i, j;
	point(int ii, int jj) {
		i = ii;
		j = jj;
	}
}