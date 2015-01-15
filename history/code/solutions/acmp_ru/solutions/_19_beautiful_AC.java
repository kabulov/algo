import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int fi, fj;
		String str = in.next();	
		fj = str.charAt(0) - 'A';
		fi = str.charAt(1) - 49;
		
		int si, sj;
		str = in.next();	
		sj = str.charAt(0) - 'A';
		si = str.charAt(1) - 49;
		
		int ti, tj;
		str = in.next();	
		tj = str.charAt(0) - 'A';
		ti = str.charAt(1) - 49;
		
		int[][] map = new int[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				map[i][j] = 0;
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (!(((i == fi) && (j == fj)) || ((i == si) && (j == sj)) || ((i == ti) && (j == tj)))) {
					if (((i - si) * (j - sj) == 0) ||
						((i - fi) * (j - fj) == 0) ||
						(Math.abs(i - fi) == Math.abs(j - fj)) ||
						(Math.abs((i - ti) * (j - tj)) == 2))
						map[i][j] = 1;
				}
		
		int answer = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				answer += map[i][j];
		
		out.println(answer);
		out.close();
	}
}