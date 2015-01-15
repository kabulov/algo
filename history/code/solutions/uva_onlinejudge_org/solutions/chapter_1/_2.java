import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		PrintWriter out = new PrintWriter(System.out);
		
		int[][] map = new int[102][102];
		int n, m;
		
		String buf = in.readLine();
		String[] mid = buf.split(" ");
		n = Integer.parseInt(mid[0]);
		m = Integer.parseInt(mid[1]);
		
		int ctr = 0;
		while (!(n == 0 && m == 0)) {
			for (int i = 0; i <= n + 1; i++)
				for (int j = 0; j <= m + 1; j++)
					map[i][j] = 0;
			
			out.print("Field #");
			out.print(++ctr);
			out.println(":");
			
			for (int i = 0; i < n; i++) {
				buf = in.readLine();
				for (int j = 0; j < m; j++)
					if (buf.charAt(j) == '*') {
						map[i + 1][j + 1] = Integer.MIN_VALUE;
						++map[i][j];
						++map[i][j + 1];
						++map[i][j + 2];
						++map[i + 1][j];
						++map[i + 1][j + 2];
						++map[i + 2][j];
						++map[i + 2][j + 1];
						++map[i + 2][j + 2];
					}
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++)
					out.print(map[i][j] < 0 ? "*" : map[i][j]);
				out.println();
			}
			
			mid = in.readLine().split(" ");
			n = Integer.parseInt(mid[0]);
			m = Integer.parseInt(mid[1]);
			
			if (n > 0) out.println();
		}
		
		out.close();
	}
}