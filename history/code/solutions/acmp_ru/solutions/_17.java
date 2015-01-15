import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt() - 1;
		int[] vect = new int[n];
		for (int i = 0; i < n; ++i)
			vect[i] = in.nextInt();
		
		int last = n;
		for (int i = 1; i * i <= n; ++i) 
			if (n % i == 0) {
				boolean flag = true;
				
outer1:			for (int a = 0; a < i; ++a)
					for (int b = a + i; b < n; b += i)
						if (vect[b] != vect[b - i]) {
							flag = false;
							break outer1;
						}
				
				if (flag) {
					last = i;
					break;
				}
				
				flag = true;
				
outer2:			for (int a = 0; a < n/i; ++a)
					for (int b = a + n/i; b < n; b += n/i)
						if (vect[b] != vect[b - n/i]) {
							flag = false;
							break outer2;
						}
				
				if (flag)
					last = n/i;
			}
		
		out.print(last);
		
		in.close();
		out.close();
	}
	
	static int len(int i) {
		int ans = i < 0 ? 1 : 0;
		while (i != 0) {
			ans++;
			i /= 10;
		}
		return ans;
	}
}