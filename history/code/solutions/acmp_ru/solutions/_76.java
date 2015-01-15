import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int[] start, end;
		start = new int[1440];
		end = new int[1440];
		Arrays.fill(start, 0);
		Arrays.fill(end, 0);
		
		int n = Integer.parseInt(in.readLine());

		int h, m;
		String buf;
		
		for (int i = 0; i < n; ++i) {
			buf = in.readLine();

			h = (buf.charAt(0) - '0') * 10 + (buf.charAt(1) - '0');
			m = (buf.charAt(3) - '0') * 10 + (buf.charAt(4) - '0');
			++start[h * 60 + m];

			h = (buf.charAt(6) - '0') * 10 + (buf.charAt(7) - '0');
			m = (buf.charAt(9) - '0') * 10 + (buf.charAt(10) - '0');
			++end[h * 60 + m];
		}
		
		int cur = 0;
		int max = 0;
		
		int s, e;
		s = next(start, 0);
		e = next(end, 0);
		
		while (s != -1) 
			if (e < s) {
				cur -= end[e];
				e = next(end, e + 1);
			} else {
				cur += start[s];
				if (cur > max) max = cur;
				s = next(start, s + 1);
			}
	
		out.println(max);
		
		in.close();
		out.close();
	}
	
	static int next(int[] v, int p) {
		while (p < 1440 && v[p] == 0) ++p;
		if (p == 1440) p = -1;
		return p;
	}
}