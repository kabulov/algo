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
		int p = nextInt();
		
		int[][] big = new int[n][m];
		int[][] sml = new int[n][m];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) 
				big[i][j] = sml[i][j] = 0;
		
		int answer = 0;
		
		int rnk, exc, i, j;
		for (i = 0; i < p; i++) {
			rnk = nextInt() - 1;
			exc = nextInt() - 1;
			
			for (j = 0; j < exc; j++) 
				big[rnk][j]++;
			
			for (j = exc + 1; j < m; j++)
				sml[rnk][j]++;
			
			for (j = 0; j < rnk; j++)
				answer += big[j][exc];
			
			for (j = rnk + 1; j < n; j++)
				answer += sml[j][exc];
		}
		
		out.println(answer);
		out.close();
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}