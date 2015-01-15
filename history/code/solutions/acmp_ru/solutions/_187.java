import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int bound = 2 * n - 1, mid = n - 1, size;
		BigInteger[][] map = new BigInteger[bound][];
		for (int i = 0; i < bound; i++) {
			if (i <= mid)
				size = n + i;
			else
				size = bound - (i - mid);

			map[i] = new BigInteger[size];
			
			for (int j = 0; j < size; j++) {
				if (i == 0) {
					map[i][j] = BigInteger.ONE;
				} else
				if (j == 0) {
					if (i <= mid)
						map[i][j] = BigInteger.ONE;
					else
						map[i][j] = map[i - 1][0].add(map[i - 1][1]);
				} else
				if (j == size - 1) {
					if (i <= mid) 
						map[i][j] = map[i - 1][size - 2].add(map[i][j - 1]);
					else
						map[i][j] = map[i - 1][size].add(map[i - 1][size - 1]).add(map[i][j - 1]);
				} else {
					if (i <= mid)
						map[i][j] = map[i - 1][j].add(map[i - 1][j - 1]).add(map[i][j - 1]);
					else
						map[i][j] = map[i - 1][j + 1].add(map[i - 1][j]).add(map[i][j - 1]);
				}
			}
		}
		
		out.println(map[bound - 1][n - 1]);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}