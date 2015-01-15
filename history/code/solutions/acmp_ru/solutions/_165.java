import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
//		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		int n = nextInt();
		int m = nextInt();
		
		int step;
		int[][] map = new int[n][m]; 
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				map[i][j] = 0;
		
		map[0][0] = 1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				step = nextInt();
				if (map[i][j] == 0 || step == 0)
					continue;
				if (i + step < n)
					map[i + step][j] += map[i][j];
				if (j + step < m)
					map[i][j + step] += map[i][j];
			}
		
		out.println(map[n - 1][m - 1]);
		out.close();
	}
	
	static PrintWriter out;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}