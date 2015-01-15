import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");
	
		int n = nextInt();
		if (n == 1)
			out.println(1);
		else
		if (n == 2)
			out.println("1 2");
		else {
			int[] v = new int[n];
			
			v[0] = 1;
			v[1] = 2;
			for (int i = 2; i < n; i++) {
				v[i] = v[i / 2];
				v[i / 2] = i + 1;
			}
			
			for (int i = 0; i < n; i++)
				out.print(v[i] + " ");
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}