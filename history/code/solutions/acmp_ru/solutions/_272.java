import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		
		while (hasNext()) {
			min = Math.min(nextLong(), min);
			if (hasNext())
				max = Math.max(nextLong(), max);
			else
				break;
		}
		
		out.println(min + max);
		out.close();
	}
	
	static StreamTokenizer in;

	public static long nextLong() {
		return (long)in.nval;
	}
	
	public static boolean hasNext() throws IOException {
		in.nextToken();
		if (in.ttype == StreamTokenizer.TT_EOL || in.ttype == StreamTokenizer.TT_EOF)
			return false;
		return true;
	}
}
