import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextInt();
		int fst = 0, len = 1;
		
		boolean[] v = new boolean[10];

		v[0] = true;
		for (int i = 1; i < 10; ++i)
			v[i] = false;
		
		int m = 0;
		for (int i = 2; i <= n; ++i) { 
			if (fst < 9) {
				m = fst + 1;
				while (m < 10 && v[m]) ++m;

				if (m < 10) {
					fst = m;
					m = v[0] ? min(v) : 0;
				} else {
					++len;
					fst = min(v);
					m = v[0] ? fst : 0;
				}
			} else {
				++len;
				fst = min(v);
				m = v[0] ? fst : 0;
			}

			for (int j = 0; j < 10; ++j) 
				v[j] = j == fst || (len > 1 && j == m) ? true : false;
		}

		out.print(fst);
		for (int i = 2; i <= len; ++i)
			out.print(m);
		
		in.close();
		out.close();
	}
	
	static int min(boolean[] v) {
		int i = 1;
		while (i < 10 && v[i]) ++i;
		return i; //		
	}
}