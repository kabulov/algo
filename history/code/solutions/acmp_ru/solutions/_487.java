import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt(), k = nextInt(), p = nextInt();		
		
		int[] map = new int[n + 1];
		map[0] = 0;
		for (int i = 1, badi = 0; i <= n; ++i)
			if (i - badi <= k)
				map[i] = 1;
			else {
				map[i] = 0;
				badi = i;
			}
			
		for (int i = 0, s = n, t; i < p; ++i) {
			t = s;
			s -= nextInt();
			out.println(map[t] == 1 && map[s] == 1 ? "F" : "T");
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}