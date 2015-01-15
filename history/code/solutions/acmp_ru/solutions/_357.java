import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.readLine();
		int answer = 0;
		int one = 1;
		for (int i = str.length() - 1; i >= 0; --i) {
			answer += (str.charAt(i) - 48) * one;
			one = -one;
		}
		
		if (answer % 11 ==  0)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
}
