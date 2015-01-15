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
		
		boolean[][] map = new boolean[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				map[i][j] = false;
		
		int type, i, j; 
		for (int l = 0; l < k; l++) {
			type = in.nextInt();
			i = in.nextInt() - 1;
			j = in.nextInt() - 1;
			if (!(0 <= i && i < n - 1 && 0 <= j && j < m - 1))
				continue;
			
			switch(type) {
				case 1:
					if (!(map[i + 1][j] || map[i + 1][j + 1] || map[i][j + 1])) {
						map[i + 1][j] = true;
						map[i + 1][j + 1] = true;
						map[i][j + 1] = true;
					}						
					break;
				case 2:
					if (!(map[i][j] || map[i + 1][j] || map[i + 1][j + 1])) {
						map[i][j] = true;
						map[i + 1][j] = true;
						map[i + 1][j + 1] = true;
					}
					break;
				case 3:
					if (!(map[i][j] || map[i][j + 1] || map[i + 1][j + 1])) {
						map[i][j] = true;
						map[i][j + 1] = true;
						map[i + 1][j + 1] = true;
					}
					break;
				case 4:
					if (!(map[i][j] || map[i + 1][j] || map[i][j + 1])) {
						map[i][j] = true;
						map[i + 1][j] = true;
						map[i][j + 1] = true;
					}
					break;
			}
		}
		
		int answer = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < m; j++)
				if (map[i][j])
					answer++;
		
		out.println(answer);			
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
