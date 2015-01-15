import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int x1 = nextInt();
		int y1 = nextInt();
		int r1 = nextInt();
		
		int x2 = nextInt();
		int y2 = nextInt();
		int r2 = nextInt();
		
		int d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		
		if ((r1 - r2) * (r1 - r2) <= d && d <= (r1 + r2) * (r1 + r2))
			out.println("YES");
		else
			out.println("NO");
			
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
