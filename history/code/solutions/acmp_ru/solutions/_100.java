import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		BigInteger[][] map = new BigInteger[n / 2][9 * n / 2];
		for (int i = 0; i < 9; i++)
			map[0][i] = BigInteger.ONE;
		
		for  (int i = 1; i < n / 2; i++) {
			for (int j = 0; j < 9 * i; j++)
				map[i][j] = new BigInteger(map[i - 1][j].toString());
			
			for (int j = 9 * i; j < 9 * (i + 1); j++)
				map[i][j] = BigInteger.ZERO;
			
			for (int j = 1; j < 10; j++)
				for (int k = j; k < 9 * i + j; k++)
					map[i][k] = map[i][k].add(map[i - 1][k - j]);
		}
		
		BigInteger answer = BigInteger.ONE;
		for (int i = 0; i < 9 * n / 2; i++)
			for (int j = i / 9; j < n / 2 - 1; j++)
				map[n / 2 - 1][i] = 
					map[n / 2 - 1][i].add(map[j][i]);
		
		for (int i = 0; i < 9 * n / 2; i++)
			answer = answer.add(map[n / 2 - 1][i].multiply(map[n / 2 - 1][i]));
		
		out.println(answer);
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}