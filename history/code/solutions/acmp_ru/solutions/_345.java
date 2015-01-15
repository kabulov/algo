import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		hash = new HashMap<String, Integer>();
		String[] inp = new String[n * (n + 3)];
		int len = 0;
		boolean was = true;
		String str;
		int cnt = 0;
		while ((str = in.readLine().trim()) != null && str.length() > 0) {
			inp[len] = str;
			++len;
			if (was) {
				hash.put(str, cnt);
				++cnt;
			}
			was = equal(str);
			if (was && cnt == n) break;
		}
		
		boolean[][] map = new boolean[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = false;
		
		cnt = 0;
		while (cnt < len) {
			int i = hash.get(inp[cnt]);
			++cnt;
			int m = Integer.parseInt(inp[cnt]);
			++cnt;
			for (int k = 0; k < m; k++) {
				map[i][hash.get(inp[cnt])] = true;
				++cnt;
			}
			++cnt;
			if (i == n - 1) break;
		}
		
		for (int k = 0; k < n; ++k)
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j)
					map[i][j] |= map[i][k] && map[k][j];
		
		for (int i = 0; i < n; i++)
			out.println(map[i][i] ? "YES" : "NO");
		out.close();
	}
	
	static boolean equal(String s) {
		if (s.length() != 5) return false;
		for (int i = 0; i < 5; i++)
			if (s.charAt(i) != '*')
				return false;
		return true;
	}
	
	static HashMap<String, Integer> hash;
}