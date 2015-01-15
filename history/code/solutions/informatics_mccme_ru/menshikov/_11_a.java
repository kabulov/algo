import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int a = nextInt();
		int n = nextInt();
		
		if (n <= 10000) {
			for (int i = 2; i <= n; i++)
				a = (a * a) % 10000; //1e4
			
			out.println(a);
		} else {
			int[] map = new int[10000]; // 1e4;
			Arrays.fill(map, -1);
			
			int c = 0;
			while (map[a] == -1) {
				map[a] = ++c;
				a = (a * a) % 10000; //1e4
			}
			++c;

			n = (n - map[a] + 1) % (c - map[a]);
			if (n == 0)
				n = c - map[a];
			
			for (int i = 2; i <= n; i++)
				a = (a * a) % 10000; //1e4;
			
			out.println(a);
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}