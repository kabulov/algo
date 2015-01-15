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
		int ans = 0, len = v.length;
		
		for (int i = 4; i < len; i++) 
			if ((v[i] == '>' && v[i - 1] == '-' && v[i - 2] == '-' && v[i - 3] == '>' && v[i - 4] == '>') ||
				(v[i] == '<' && v[i - 1] == '<' && v[i - 2] == '-' && v[i - 3] == '-' && v[i - 4] == '<'))
			{
				++ans;
			}
		
		out.println(ans);
		out.close();
	}
}
