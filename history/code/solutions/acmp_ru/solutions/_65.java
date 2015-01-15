import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");

		s = in.readLine().toCharArray();
		lt = s.length;
		int n = Integer.parseInt(in.readLine());
		
		String str;
		int len = 0, tmp;
		int min = Integer.MAX_VALUE;
		int[] v = new int[21000];

		for (int i = 0; i < n; i++) {
			str = in.readLine();
			tmp = haming(str);
			if (tmp < min) {
				len = 1;
				v[0] = i;
				min = tmp;
			} else
			if (tmp == min)
				v[len++] = i;
		}
		
		out.println(len);
		for (int i = 0; i < len; i++)
			out.print(++v[i] + " ");
		
		out.close();
	}
	
	static int haming(String str) {
		int dist = 0;
		for (int i = 0; i < lt; i++)
			if (str.charAt(i) != s[i])
				++dist;
		
		return dist;
	}
	
	static int lt;
	static char[] s;
}
