import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] argv) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		int[] inp = new int[n];
		for (int i = 0; i < n; i++)
			inp[i] = nextInt();
		
		int k = nextInt();
		
		int[] map = new int[n];
		map[0] = inp[0];
		for (int i = 1; i < n; i++)
			map[i] = map[i - 1] + inp[i];
			
		if (k > 1) {
			int[] scd, tmp;
			scd = new int[n];
			
			int s, min;
			for (int p = 1; p < k; p++) {
				for (int i = p; i < n; i++) {
					s = 0;
					scd[i] = Integer.MAX_VALUE;
					for (int j = i; j >= p; --j) {
						s += inp[j];

						if (s > map[j - 1])
							min = s;
						else
							min = map[j -1];
						
						if (min < scd[i])
							scd[i] = min;
					}
				}
				
				tmp = scd;
				scd = map;
				map = tmp;
			}
		}
		
		out.println(map[n - 1]);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
