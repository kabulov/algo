import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		char[] str = in.next().toCharArray();
		Arrays.sort(str);
		
		int a = Integer.parseInt(new String(str));
		int b = Integer.parseInt(new String(new StringBuilder(new String(str)).reverse()));
			
		if (prime(a) && prime(b))
			out.println("Yes");
		else
			out.println("No");
		
		out.close();
	}
	
	static boolean prime(int n) {
		if (n == 1)
			return false;
		
		for (int i = 2; i * i <= n; i++)
			if (n % i == 0)
				return false;
		
		return true;
	}
}