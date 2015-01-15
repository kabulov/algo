import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int m = nextInt();
		
		int[][] map = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++)
			map[i][0] = 0;
		for (int i = 0; i <= m; i++)
			map[0][i] = 0;
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				map[i][j] = nextInt();
		
		for (int j = 1; j <= m; j++)
			for (int i = 2; i <= n; i++)
				map[i][j] += map[i - 1][j];
		
		int sum, tmp;
		int answer = Integer.MIN_VALUE;
		for (int ilow = 1; ilow <= n; ++ilow)
			for (int iup = 1; iup <= ilow; ++iup) {
				sum = 0;
				for (int j = 1; j <= m; j++) {
					tmp = map[ilow][j] - map[iup - 1][j];
					if (sum < 0)
						sum = tmp;
					else
						sum += tmp;
					if (sum > answer)
						answer = sum;
				}
			}
		
		out.println(answer);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}