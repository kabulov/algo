import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int[][] map = new int[n][n];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; ++j) {
				map[i][j] = in.nextInt();
				if (map[i][j] == 100000)//1e5
					map[i][j] = inf; //1e7
			}
		
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j)
					if (map[i][k] < inf && map[k][j] < inf)
						map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
						
		boolean yes = false;
		for (int i = 0; i < n; i++)
			if (map[i][i] < 0) {
				yes = true;
				break;
			}
		
		out.println(yes ? "YES" : "NO");
		out.close();
	}
	
	static int inf = (int)1e7;
}