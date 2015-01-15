import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		boolean[] v = new boolean[6];
		for (int i = 1; i <= 5; i++)
			v[i] = false;
		
		for (int i = 0; i < 5; i++) 
			v[compute(nextInt(), nextInt())] = true;
		
		int ans = 0 ;
		for (int i = 1; i <= 5; i++)
			if (v[i])
				++ans;
		
		out.println(ans);
		out.close();
	}
	
	static int compute(int x, int y) {
		if (Math.abs(y) > 10)
			return 0;
		
		for (int i = 0; i < 5; i++)
			if (Math.abs(x - i * 25) <= 10)
				return i + 1;
		
		return 0;
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
