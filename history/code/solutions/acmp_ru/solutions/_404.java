import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		long n = Long.parseLong(in.readLine());
		long pos = 2, sqrt = 1;
		if (n <= 2) {
			pos = 2;
		}
		
		while (pos < n) {
			pos += sqrt + 1;
			if ((sqrt + 1) * (sqrt + 1) <= pos) {
				++pos;
				++sqrt;
			}
		}
		
		if (pos == n)
			out.println("LOSE");
		else
			out.println("WIN");
		
		out.close();
	}
}
