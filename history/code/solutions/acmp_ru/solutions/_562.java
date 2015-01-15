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
		int[][] map = new int[n][n];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = inf;
		
		for (int i = 0; i < m; i++)
			map[nextInt() - 1][nextInt() - 1] = 0;
		
		for (int i = 0; i < n; i++)
			map[i][i] = 0;
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (map[i][j] == inf && map[j][i] == 0)
					map[i][j] = 1;
		
		int dst;
		for (int k = 0; k < n; ++k)
			for (int j = 0; j < n; ++j)
				for (int i = 0; i < n; ++i)
//					if (map[i][k] < inf && map[k][j] < inf)
					if ((dst = map[i][k] + map[k][j]) < map[i][j])
						map[i][j] = dst;
		
		int max = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (map[i][j] > max)
					max = map[i][j];
					            
		out.println(max);
		out.close();
	}
	
	static int inf = 300 + 1;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}