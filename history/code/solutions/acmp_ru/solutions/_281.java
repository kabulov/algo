import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int m = Integer.parseInt(str.split(" ")[1]);
		
		int cnm = 1;
		for (int k = 1; k < m; k++)
			cnm = cnm * (n - (k - 1)) / k;
		
		int ans = m == 0 ? 1 : 0;
		for (int k = m == 0 ? 1 : m; k <= n; k++) {
			cnm = cnm * (n - (k - 1)) / k;
			ans += cnm;
		}
		
		out.println(ans);
		out.close();
	}
}
