import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
//		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		int n = nextInt();
		int m = nextInt();
		
		int[][] map = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++)
			map[i][0] = 0;
		
		for (int i = 1; i <= m; i++)
			map[0][i] = 0;
		
		for (int i = 1; i <= n; i++) 
			for (int j = 1; j <= m; j++) {
				map[i][j] = nextInt();
				if (i == 1)
					map[i][j] += map[i][j - 1];
				else
				if (j == 1)
					map[i][j] += map[i - 1][j];
				else
					map[i][j] += Math.min(map[i][j - 1], map[i - 1][j]);
			}
		
		
		out.println(map[n][m]);
		out.close();
	}
	
	static PrintWriter out;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}