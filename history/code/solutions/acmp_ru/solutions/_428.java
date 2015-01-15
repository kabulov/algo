import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] argv) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		char[] inp = in.next().toCharArray();
		
		int len;
		int[] map = new int[inp.length];
		int[] var = new int[inp.length];
		
		len = 1;
		map[0] = ord(inp[0]);
		var[0] = extra(inp[0]);
		for (int i = 1; i < inp.length; i++) 
			if (type(inp[i]) == type(inp[i - 1])) {
				map[len - 1] += ord(inp[i]);
			} else {
				map[len] = ord(inp[i]);
				var[len] = extra(inp[i]);
				++len;
			}
		
		BigInteger[][] cur;
		BigInteger[][] sol = new BigInteger[len][n];
		
		for (int i = 1; i < len; i++)	
			for (int j = 0; j < n; j++)
				sol[i][j] = BigInteger.ZERO;
		
		fill(n, inp.length);
		
		int s = map[0];
		cur = var[0] == 3 ? three : four;

		for (int j = 0; j < n; j++)
			sol[0][j] = cur[j + 1][s]; 				
		
		for (int i = 1; i < len; i++) {
			cur = var[i] == 3 ? three : four;
			
			s = map[i];
			for (int j = 1; j < n; j++)
				for (int k = 0; k < j; k++)
					sol[i][j] = sol[i][j].add(sol[i - 1][k].multiply(cur[j - k][s]));
		}
			
		out.println(sol[len - 1][n - 1]);
		out.close();
	}
	
	static int extra(char c) {
		int var = type(c);
		if (var == 5 || var == 7)
			return 4;
		else
			return 3;
	}
	
	static int type(char c) {
		switch(c) {
		case 'A':case 'B': case 'C':
			return 0;
		case 'D':case 'E':case 'F':
			return 1;
		case 'G':case 'H':case 'I':
			return 2;
		case 'J':case 'K':case 'L':
			return 3;
		case 'M':case 'N':case 'O':
			return 4;
		case 'P':case 'Q':case 'R':case 'S':
			return 5;
		case 'T':case 'U':case 'V':
			return 6;
		case 'W':case 'X':case 'Y':case 'Z':
			return 7;
		default: 
			return -1;
		}
	}
	
	static int ord(char c) {
		switch(c) {
		case 'A':case 'D':case 'G':case 'J':case 'M':case 'P':case 'T':case 'W':
			return 1;
		case 'B':case 'E':case 'H':case 'K':case 'N':case 'Q':case 'U':case 'X':
			return 2;
		case 'C':case 'F':case 'I':case 'L':case 'O':case 'R':case 'V':case 'Y':
			return 3;
		default:
			return 4;
		}
	}
	
	static void fill(int a, int b) {
		int n, s;
		
		n = a; 
		s = 3 * b;
		three = new BigInteger[n + 1][s + 1];
		
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= s; j++)
				three[i][j] = BigInteger.ZERO;
		
		three[0][0] = BigInteger.ONE;
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= s && j <= 3 * i; j++) 
				for (int k = 1; k <= 3 && j >= k; ++k)
					three[i][j] = three[i][j].add(three[i - 1][j - k]);
			
		n = a;
		s = 4 * b;
		four = new BigInteger[n + 1][s + 1];
		
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= s; j++)
				four[i][j] = BigInteger.ZERO;
		
		four[0][0] = BigInteger.ONE;
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= 4 * i && j <= s; j++)
				for (int k = 1; k <= 4 && j >= k; k++)
					four[i][j] = four[i][j].add(four[i - 1][j - k]);
	}
	
	static BigInteger[][] three, four;
}