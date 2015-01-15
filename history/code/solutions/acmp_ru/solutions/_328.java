import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = nextInt();
		
		long answer = 0;
		for (int i = 1; i <= n; i++)
			answer += i * (n - i + 1);
		
		for (int i = 0; i <= n; i++)
			answer += (n + i) * (n - i + 1) / 2;
		
		out.println(answer);
		out.close();
	}
	
	static PrintWriter out;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}