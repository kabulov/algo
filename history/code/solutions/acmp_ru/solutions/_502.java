import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] argv) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		
		int[][] inp = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				inp[i][j] = nextInt();
		
		int[][] map = new int[n + 1][n];
		for (int i = 0; i < n; i++)
			map[0][i] = 0;
		
		for (int i = 1; i <= n; i++)
			map[i][0] = inp[i - 1][0];
		
		int tmp;
		for (int j = 1; j < n; j++) 
			for (int i = 1; i <= n; i++) {
				map[i][j] = map[i][j - 1];
				for (int k = 1; k <= i; k++) {
					tmp = inp[k - 1][j] + map[i - k][j - 1];
					
					if (tmp > map[i][j])
						map[i][j] = tmp;
				}
			}
		
		out.println(map[n][n - 1]);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
