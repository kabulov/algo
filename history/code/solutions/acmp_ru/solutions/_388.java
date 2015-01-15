//import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.math.BigInteger;
import java.io.StreamTokenizer;
//import java.util.Formatter;
//import java.util.Locale;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
//		in = new BufferedReader(new FileReader("input.txt"), 4096);
		
		short n = nextShort();
		short m = nextShort();
		
		short[][] map = new short[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				in.nextToken();
				map[i][j] = (short)in.nval;
			}
		
		short[] min = new short[n];
		short[] max = new short[m];
		
		short tmp;
		
		for (int i = 0; i < n; i++) {
			min[i] = map[i][0];
			for (int j = 1; j < m; j++) {
				tmp = map[i][j];
				if (tmp < min[i])
					min[i] = tmp;
			}
		}
		
		for (int j = 0; j < m; j++) {
			max[j] = map[0][j];
			for (int i = 1; i < n; i++) {
				tmp = map[i][j];
				if (tmp > max[j])
					max[j] = tmp;
			}
		}
		
		int answer = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				tmp = map[i][j];
				if (tmp == min[i] && tmp == max[j])
					answer++;
			}
		
		out.println(answer);
		out.close();
	} 
	  
//	static BufferedReader in;
//	static Scanner in;
	static PrintWriter out;
	static StreamTokenizer in;

	public static short nextShort() throws IOException {
		in.nextToken();
		return (short)in.nval;
	}
	
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
