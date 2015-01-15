import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();
		int k = Integer.parseInt(in.readLine());
		
		if (k > 0) {
			StringBuilder ans = new StringBuilder();
			while (k-- > 0 && ans.length() < 1023) {
				ans.append(str);
			}
			if (ans.length() < 1023)
				out.println(ans);
			else
				out.println(ans.substring(0, 1023));
		} else {
			k = -k;
			int len = str.length();
			if (len % k != 0) 
				out.println("NO SOLUTION");
			else {
				int dist = len / k, pos = dist, ctr = 1;
				while (ctr < k) {
					for (int j = 0; j < dist; j++)
						if (str.charAt(j) != str.charAt(pos + j)) {
							out.println("NO SOLUTION");
							out.close();
							return;
						}
					ctr++;
					pos += dist;
				}
				out.println(str.substring(0, dist));
			}
		}
		
		out.close();
	}
}
