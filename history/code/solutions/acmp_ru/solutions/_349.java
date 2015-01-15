import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int left = nextInt();
		int right = nextInt();
		
		boolean[] prime = new boolean[1000001];
		Arrays.fill(prime, true);
		
		for (int i = 2; i < 1000000; i++) {
			if (prime[i]) {
				for (int j = i; j + i < 1000001; j += i)
					prime[j + i] = false;
			}
		}
		
		boolean noprime = true;
		for (int i = left; i <= right; i++) {
			if (prime[i]) {
				out.print(i + " ");
				noprime = false;
			}
		}
		
		if (noprime)
			out.println("Absent");
		
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}