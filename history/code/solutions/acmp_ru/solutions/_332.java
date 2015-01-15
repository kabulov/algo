import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		
		int[][] map = new int[n][];
		for (int i = 0; i < n; i++) {
			map[i] = new int[n - i];
			for (int j = 0; j < n - i; j++)
				map[i][j] = nextInt();
		}
		
		int[] answer = new int[n + 1];
		answer[0] = 0;
		
		for (int i = 1; i <= n; i++) {
			answer[i] = map[0][i - 1];
			for (int j = 1; j < i; j++)
				answer[i] = Math.min(answer[i], answer[j] + map[j][i - j - 1]);
		}
		
		out.println(answer[n]);		
		out.close();
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}