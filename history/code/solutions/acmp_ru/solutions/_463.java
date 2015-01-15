import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();
		int k = Integer.parseInt(str.split(" ")[0]);
		int m = Integer.parseInt(str.split(" ")[1]);
		
		str = in.readLine();
		int ans = 0, len = str.length(), d;
		
		char c;
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if ('0' <= c && c <= '9')
				d = c - 48;
			else
				d = c - 55;
			ans = (ans * k + d) % m;
		}

		out.println(ans);
		out.close();
	}
}