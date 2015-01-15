import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		
		n = Integer.parseInt(in.readLine());
		
		solve(0, 1, 1, 1);
		
		out.close();
	}
	
	static void solve(int n1, int d1, int n2, int d2) {
		if (d1 + d2 <= n) {
			solve(n1, d1, n1 + n2, d1 + d2);
			out.println((n1 + n2) + "/" + (d1 + d2));
			solve(n1 + n2, d1 + d2, n2, d2);
		}
	}
	
	static PrintWriter out;
	static int n;
}
