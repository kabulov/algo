import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		n = in.nextInt();
		m = in.nextInt();
		
		int tmp;
		if (n > m) {
			tmp = n;
			n = m;
			m = tmp;
		}
			
		map = new int[1 << n][1 << n];
		
		initMap(n);		

		int[][] sol = new int[1 << n][m];
		for (int i = 0; i < (1 << n); i++)
			sol[i][0] = 1;
		
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < (1 << n); j++)
				for (int k = 0; k < (1 << n); k++)
					sol[j][i] += map[k][j] * sol[k][i - 1];
		}
		
		int answer = 0;
		for (int i = 0; i < (1 << n); i++)
			answer += sol[i][m - 1];
		
		out.println(answer);
		
		out.close();
	}
	
	static void initMap(int size) {
		size = 1 << size;
		for (int i = 0; i < size; i++)
			for (int j = i; j < size; j++)
				if (cute(i, j)) {
					map[i][j] = map[j][i] = 1;					
				} else
					map[i][j] = map[j][i] = 0;
	}
	
	static boolean cute(int a, int b) {
		int oneone = 3;
		
		int tmp;
		for (int i = 0; i < n - 1; i++) {
			tmp = a & oneone;
			if ((tmp & (tmp - 1)) > 0) {
				tmp = b & oneone;
				if ((tmp & (tmp - 1)) > 0)
					return false;
			}
			
			tmp = a & oneone;
			if (tmp == 0) {
				tmp = b & oneone;
				if (tmp == 0)
					return false;
			}
			oneone <<= 1;
		}
			
		return true;
	}
	
	static int[][] map;
	static int m, n;
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}