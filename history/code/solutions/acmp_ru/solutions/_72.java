import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		char[] v = in.next().toCharArray();
		int len = v.length;
		
		int i;
		for (i = len - 2; v[i] >= v[i + 1]; --i);
		
		int j;
		for (j = len - 1; v[i] >= v[j]; --j);
		
		char c = v[i];
		v[i] = v[j];
		v[j] = c;
		
		j = i + 1 + (len - (i + 1)) / 2;
		int bound = len - 1 + (i + 1);
		for (i = i + 1; i < j; i++) {
			c = v[i];
			v[i] = v[bound - i];
			v[bound - i] = c;
		}

		for (i = 0; i < len; i++)
			out.print(v[i]);
		
		out.close();
	}
}
