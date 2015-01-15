import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] map = new int[101];
		for (int i = 1; i < 101; i++)
			map[i] = Integer.MAX_VALUE;
		
		int tmp, buf;
		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			tmp = nextInt();
			if ((buf = i - map[tmp]) > ans)
				ans = buf;
				
			map[tmp] = i;
		}
		
		out.println(ans);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
//another solution is stable sort;