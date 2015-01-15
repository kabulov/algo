import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String fst = in.readLine();
		String scd = in.readLine();
		
		int ans = 1, len = fst.length();
		for (int i = 0; i < len; i++)
			ans *= count(fst.charAt(i), scd.charAt(i));
		
		out.println(ans);
		out.close();
	}

	static int count(char a, char b) {
		boolean b1, b2, b3;
		b1 = b2 = b3 = false;
		
		if (Character.isDigit(a))
			b1 = true;
		else
		if (Character.isLetter(a))
			b2 = true;
		else
			b3 = true;
		
		if (Character.isDigit(b))
			b1 = true;
		else
		if (Character.isLetter(b))
			b2 = true;
		else
			b3 = true;
		
		if (b1 && !b2 && !b3) {
			if (a == b)
				return 1;
			else
				return 0;
		} else		
		if (!b1 && b2 && !b3) {
			if (a <= b) {
				if ((a - 'a' + 3) - (b - 'a') + 1 > 0)
					return (a - 'a' + 3) - (b - 'a') + 1;
				else
					return 0;
			} else {
				if ((b - 'a' + 3) - (a - 'a') + 1 > 0)
					return (b - 'a' + 3) - (a - 'a') + 1;
				else
					return 0;
			}
		} else
		if (!b1 && !b2 && b3) {
			return 10;
		} else 
		if (b1 && b2) {
			if (Character.isDigit(a)) {
				if (b - 'a' <= a - '0' && a - '0' <= b - 'a' + 3)
					return 1;
				else
					return 0;
			} else {
				if (a - 'a' <= b - '0' && b - '0' <= a - 'a' + 3)
					return 1;
				else
					return 0;
			}
		} else
		if (b1 && b3) {
			return 1;
		} else { // b2 && b3
			return 4;
		}
	}
}
