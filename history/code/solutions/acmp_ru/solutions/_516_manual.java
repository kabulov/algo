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

		sort(str);

		int a = parse(str);
		
		reverse(str);
		
		int b = parse(str);
		
		if (prime(a) && prime(b))
			out.println("Yes");
		else
			out.println("No");
		
		out.close();
	}
	
	static int parse(char[] v) {
		int ans = 0, len = v.length;
		for (int i = 0; i < len; i++)
			ans = ans * 10 + v[i] - 48;
		return ans;
	}
	
	static void reverse(char[] v) {
		int len = v.length;
		for (int i = 0; i < len / 2; i++) {
			char c = v[i];
			v[i] = v[len - 1 - i];
			v[len - 1 - i] = c;
		}
	}
	
	static void sort(char[] v) {
		int len = v.length;
		for (int i = 0; i < len - 1; i++) {
			int min = i;
			for (int j = i + 1; j < len; j++)
				if (v[min] > v[j])
					min = j;
			
			char c = v[i];
			v[i] = v[min];
			v[min] = c;
		}
				
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