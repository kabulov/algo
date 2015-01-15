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

		char[] v = in.readLine().toCharArray();
		if (v.length == 1 || Character.isLowerCase(v[0])) {
			out.println("No");
			out.close();
			return;
		}
		
		int len = v.length, i = 1, prev = 0;
		boolean good = true;
		
		while (true) {
			while (i < len && Character.isLowerCase(v[i]))
				++i;
			if (i - prev == 1 || i - prev > 4) {
				good = false;
				break;
			} else {
				prev = i++;
				if (prev == len)
					break;
			}
		}
		
		if (good)
			out.println("Yes");
		else
			out.println("No");
		
		out.close();
	}
}
