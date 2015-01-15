import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] map = new int[201];
		for (int i = 0; i < 201; ++i)
			map[i] = 0;
		
		for (int i = 0; i < n; i++)
			++map[100 + nextInt()];
		
		int tmp;
		for (int i = 0; i < 201; ++i) {
			tmp = i - 100;
			for (int j = 0; j < map[i]; ++j)
				out.print(tmp + " ");
		}
			
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
