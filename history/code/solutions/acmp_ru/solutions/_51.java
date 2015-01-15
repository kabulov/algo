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
		int k = str.split(" ")[1].length();
		int stop = n % k == 0 ? k : n % k;
		
		int answer = stop;
		while (n > stop) {
			answer *= n;
			n -= k;
		}
		
		out.println(answer);
		out.close();
	}
}
