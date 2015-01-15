import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String n = in.next();
		int k = in.nextInt();
		
		String sol = new String(solve(k));
		
		if (sol.length() > n.length()) 
			out.println("NO");
		else {
			long a = Long.parseLong(n);
			long b = Long.parseLong(sol);
			if (b <= a)
				out.println("YES");
			else
				out.println("NO");
		}
		
		out.close();
	}
	
	static StringBuilder solve(int max) {
		if (max == 1)
			return new StringBuilder("1");
		
		StringBuilder result = new StringBuilder();
		int[] vect = new int[10];
		Arrays.fill(vect, 0);
		
		while (max % 2 == 0) {
			vect[2]++;
			max /= 2;
		}
		
		for (int i = 3; i <= 7; i += 2)
			while (max % i == 0) {
				vect[i]++;
				max /= i;
			}
		
		if (max > 1) 
			return new StringBuilder("10000000000"); // 1e10
		
		vect[8] = vect[2] / 3;
		vect[2] %= 3;
		
		vect[9] = vect[3] / 2;
		vect[3] %= 2;
		
		if (vect[3] == 0) {
			if (vect[2] == 2) {
				vect[2] = 0;
				vect[4] = 1;
			}
		} else {
			if (vect[2] > 0) {
				vect[3] = 0;
				vect[6] = 1;
				vect[2]--;
			}
		}
		
		for (int i = 2; i < 10; i++)
			for (int j = 0; j < vect[i]; j++)
				result.append((char)(i + 48));
		
		return result;
	}
}
