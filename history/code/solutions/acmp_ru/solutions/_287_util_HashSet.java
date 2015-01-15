import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int m = Integer.parseInt(in.readLine().split(" ")[1]);
		String str = in.readLine();
		int n = str.length();
		
		int ans = 0;
		HashSet<String> hs = new HashSet<String>();
		
		String tmp;
		for (int i = 0; i <= n - m; i++) {
			tmp = str.substring(i, i + m);
			if (!hs.contains(tmp)) {
				hs.add(tmp);
				++ans;
			}
		}
			
		out.println(ans);
		out.close();
	}
}
