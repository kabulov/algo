import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		String str = in.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int K = Integer.parseInt(str.split(" ")[1]);
		
		int[][] map = new int[n + 2][n + 2];
		for (int i = 0; i < n + 2; i++) {
			map[i][0] = map[0][i] = map[n + 1][i] = map[i][n + 1] = -1;
		}
		
		for (int i = 1; i <= n; i++) {
			str = in.readLine();
			for (int j = 1; j <= n; j++)
				if (str.charAt(j - 1) == '1')
					map[i][j] = -1;
				else
					map[i][j] = 0;
		}
		
		map[1][1] = 1;
		for (int k = 1; k <= K; k++) 
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					if (((i + j) % 2) == ((k + 2) % 2) && i + j <= k + 2 && map[i][j] != -1) {
						map[i][j] = 0;
						if (map[i - 1][j] != -1)
							map[i][j] += map[i - 1][j];
						if (map[i + 1][j] != -1)
							map[i][j] += map[i + 1][j];
						if (map[i][j - 1] != -1)
							map[i][j] += map[i][j - 1];
						if (map[i][j + 1] != -1)
							map[i][j] += map[i][j + 1];
					}
		
		
		out.println(map[n][n]);
		out.close();
	}
}
