import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		boolean[] map = new boolean[50001];		
		
		int i, j;
		for (i = 0; i < 50001; ++i)
			map[i] = false;
		
		int num;
		map[0] = true;
		for (i = 0; i < n; ++i) {
			num = nextInt();
			if (num == 0)
				continue;
			for (j = 50000 - num; j >= 0; --j) {
				if (map[j])
					map[j + num] = true;
			}
		}
		
		int answer = 1;
		for (i = 1; i < 50001; ++i)
			if (map[i])
				++answer;
		
		out.println(answer);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
