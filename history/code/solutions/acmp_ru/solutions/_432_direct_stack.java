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
		
		ipos = new int[40000];
		jpos = new int[40000];
		
		int ans = 0, curi, curj;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				if (map[i][j] == '#') {
					++ans;
					top = 1;
					ipos[0] = i;
					jpos[0] = j;
					map[i][j] = '.';
					while (top > 0) {
						--top;
						curi = ipos[top];
						curj = jpos[top];
						
						check(curi + 1, curj);
						check(curi - 1, curj);
						check(curi, curj + 1);
						check(curi, curj - 1);
					}
				}
		
		out.println(ans);
		out.close();
	}
	
	static void check(int i, int j) {
		if (map[i][j] == '#') {
			map[i][j] = '.';
			ipos[top] = i;
			jpos[top] = j;
			top++;
		}
	}
	
	static int top;
	static int[] ipos;
	static int[] jpos;
	static char[][] map;
}
