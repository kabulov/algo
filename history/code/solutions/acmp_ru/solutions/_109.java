import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String[] buf = in.next().split("[/]");
		int num = Integer.parseInt(buf[0]);
		int den = Integer.parseInt(buf[1]);
		
		out.print(num/den);
		num %= den;
		
		if (num != 0) {
			int[] v = new int[den + 1];
			v[0] = num;
			for (int i = 1; i <= den; ++i) v[i] = (v[i - 1] * 10) % den;
			
			int i = den - 1;
			while (v[i] != v[den]) --i;
			
			int len = den - i;
			while (i >= 0 && v[i] == v[i + len]) --i;
			
			out.print(".");
			for (int j = 0; j <= i; ++j) {
				out.print(num * 10 / den);
				num = num * 10 % den;
			}
			
			if (!(len == 1 && v[den] == 0)) {
				out.print("(");
				for (int j = 0; j < len; ++j) {
					out.print(num * 10 / den);
					num = num * 10 % den;
				}
				out.print(")");
			}
		}
		
		out.close();
	}
}
