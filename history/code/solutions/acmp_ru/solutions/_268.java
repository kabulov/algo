import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int len = in.nextInt();
		int K = in.nextInt();
		
		String str = in.next();
		char[] src = str.toCharArray();
		
		int answer = len;
		
		short[][] map = new short[len + 1][];
		map[0] = new short[len];
		map[1] = new short[len];
		for (int i = 0; i < len; i++) {
			map[0][i] = 0;
			map[1][i] = 0;
		}	
		
		for (int i = 2; i <= len; i++) {
			map[i] = new short[len - i + 1];
			for (int j = 0; j <= len - i ; j++) {
				map[i][j] = map[i - 2][j + 1];
				if (src[j] != src[j + i - 1])
					map[i][j]++;
				if (map[i][j] <= K)
					answer++;
			}
		}
		
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