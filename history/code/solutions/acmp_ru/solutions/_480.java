import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");
	
		int n = nextInt();
		int[] inp = new int[n];
		int[] sum = new int[n];
		
		inp[0] = sum[0] = nextInt();
		for (int i = 1; i < n; i++) {
			inp[i] = nextInt();
			sum[i] = sum[i - 1] + inp[i];
		}
		
		int k = Math.min(nextInt(), n);
				
		int[][] map = new int[n][k];
		
		map[n - 1][0] = inp[n - 1];
		
		int min;
		for (int i = n - 2; i >= 0; --i) {
			if (k < n - i)
				min = k;
			else
				min = n - i;
			
			map[i][0] = inp[i] + sum[n - 1] - sum[i] - map[i + 1][0];
			for (int j = 1; j < min; j++) {
				int max;
				if (i + j >= n - 1)
					max = sum[n - 1] - sum[i] + inp[i];
				else
					max = sum[i + j] - sum[i] + inp[i] + sum[n - 1] - sum[i + j] - map[i + j + 1][Math.min(j, (n - 1) - (i + j + 1))];
				
				if (map[i][j - 1] > max)
					map[i][j] = map[i][j - 1];
				else
					map[i][j] = max;
			}
		}
		
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++)
			answer = Math.max(answer, map[0][i]);
		
		out.println(answer);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}