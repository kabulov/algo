import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] argv) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		n = nextInt();
		inp = new int[n];
		for (int i = 0; i < n; i++)
			inp[i] = nextInt();
		
		int k = nextInt();
		map = new int[k + 1];
		Arrays.fill(map, Integer.MAX_VALUE);
		map[0] = 0;
		
		int tmp;
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < n; j++) 
				if (i >= inp[j] && (tmp = map[i - inp[j]]) != Integer.MAX_VALUE) {
					++tmp;
					if (tmp < map[i])
						map[i] = tmp;
				}
		}
		
		if (map[k] == Integer.MAX_VALUE)
			out.println(-1);
		else
			out.println(map[k]);
		
		out.close();
	}
	
	static int n;
	static int[] inp, map;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
