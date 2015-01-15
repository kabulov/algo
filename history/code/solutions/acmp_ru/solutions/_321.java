import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		for (int i = 2; i <= 36; i++) {
			if (nosame(dectoi(n, i)))
				out.print(i + " ");
		}
		
		out.close();
	}

	static String dectoi(int n, int radix) {
		String result = "";
		int tmp;
		while (n > 0) {
			tmp = n % radix;
			if (0 <= tmp || tmp <= 9) {
				result = result + (char)(tmp + '0');
			} else {
				result = result + (char)('A' + tmp - 10);
			}
			n /= radix;
		}
		return result;
	}
	
	static boolean nosame(final String num) {
		int len = num.length();
		for (int i = 1; i < len; i++)
			for (int j = 0; j < i; j++)
				if (num.charAt(i) == num.charAt(j)) {
					return false;
				}
		
		return true;
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}