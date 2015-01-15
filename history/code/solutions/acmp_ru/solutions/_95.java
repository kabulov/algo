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
		int num = 0, len = v.length;
		
		if (len == 1) {
			out.println(v[0] + " 0");
			out.close();
			return;
		}
		
		for (int i = 0; i < len; i++)
			num += v[i] - 48;
		
		int ctr = 1, tmp;
		while (num > 9) {
			tmp = 0;
			while (num > 0) {
				tmp += num % 10;
				num /= 10;
			}
			++ctr;
			num = tmp;
		}
		
		out.println(num + " " + ctr);
		out.close();
	}
}
