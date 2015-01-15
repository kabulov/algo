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
		int k = nextInt();
		
		int[][] vect = new int[k + 1][4];
		for (int i = 1; i <= k; i++) {
			vect[i][0] = Integer.MIN_VALUE;
			vect[i][1] = Integer.MAX_VALUE;
			vect[i][2] = Integer.MAX_VALUE;
			vect[i][3] = Integer.MIN_VALUE;
		}
		
		int p;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				p = nextInt();
				if (vect[p][0] < i)
					vect[p][0] = i;
				if (vect[p][1] > j)
					vect[p][1] = j;
				if (vect[p][2] > i)
					vect[p][2] = i;
				if (vect[p][3] < j)
					vect[p][3] = j;
			}
		}
		
		for (int i = 1; i <= k; i++)
			out.println(vect[i][1] + " " + (n - 1 - vect[i][0]) + " " + (vect[i][3] + 1) + " " + (n - vect[i][2]));
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
