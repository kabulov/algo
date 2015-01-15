import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.io.StreamTokenizer;
//import java.util.Formatter;
//import java.util.Locale;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		
		int[][] map = new int[n + 2][m + 2];
		for (int i = 0; i < n + 2; i++)
			Arrays.fill(map[i], 0);
		
		for (int l = 0; l < k; l++)
			map[in.nextInt()][in.nextInt()] = -1;
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				if (map[i][j] != -1) {
					if (map[i - 1][j] == -1)
						map[i][j]++;
					if (map[i + 1][j] == -1)
						map[i][j]++;
					if (map[i][j - 1] == -1)
						map[i][j]++;
					if (map[i][j + 1] == -1)
						map[i][j]++;
					if (map[i + 1][j + 1] == -1)
						map[i][j]++;
					if (map[i - 1][j - 1] == -1)
						map[i][j]++;
					if (map[i + 1][j - 1] == -1)
						map[i][j]++;
					if (map[i - 1][j + 1] == -1)
						map[i][j]++;
				}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i][j] == -1)
					out.print('*');
				else
				if (map[i][j] == 0)
					out.print('.');
				else
					out.print(map[i][j]);
			}
			out.println();
		}	
		
		out.close();
	} 
	  
	static Scanner in;
	static PrintWriter out;
//	static StreamTokenizer in;

//	public static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	public static long nextLong() throws IOException {
//		in.nextToken();
//		return (long)in.nval;
//	}
	
//	public static boolean hasNext() throws IOException {
//		in.nextToken();
//		if (in.ttype == StreamTokenizer.TT_EOL || in.ttype == StreamTokenizer.TT_EOF)
//			return false;
//		return true;
//	}
}
