import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		String str = in.readLine();
		int len = str.length();

		if (len <= 1) {
			out.println(len == 1 ? 1 : 0);
			out.close();
			return;
		}
		
		BigInteger[] map = new BigInteger[len];
		map[0] = BigInteger.ONE;
		map[1] = BigInteger.ONE;
		
		if (str.charAt(0) != '0' && crypt(str.charAt(0), str.charAt(1)))
			map[1] = map[1].add(BigInteger.ONE);
		
		for (int i = 2; i < len; ++i) {
			map[i] = map[i - 1];
			if (str.charAt(i - 1) != '0' && crypt(str.charAt(i - 1), str.charAt(i)))
				map[i] = map[i].add(map[i - 2]);
		}
		
		out.println(map[len - 1]);
		out.close();
	}
	
	static boolean crypt(char a, char b) {
		return ((a - 48) * 10 + b - 48) <= 33;
	}
}
