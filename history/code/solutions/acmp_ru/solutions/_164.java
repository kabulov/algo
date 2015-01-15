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
		int len = str.length();
		v = new int[len];
		
		v[0] = str.charAt(0) - 48;
		for (int i = 1; i < len; i++) {
			v[i] = v[i - 1] + str.charAt(i) - 48;
		}
		
		boolean good = false;
		for (int i = 0; i < len - 1; i++)
			if (root(v[i]) == root(v[len - 1] - v[i])) {
				good = true;
				break;
			}
			
		if (good)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
	
	static int[] v;
	static int root(int n) {
		int tmp;
		while (n > 9) {
			tmp = 0;
			while (n > 0) {
				tmp += n % 10;
				n /= 10;
			}
			n = tmp;
		}
		return n;
	}
}
