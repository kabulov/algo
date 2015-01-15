import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int s = in.nextInt() - 1;
		int[][] map = new int[n][n];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = in.nextInt();
		
		boolean[] was = new boolean[n];
		Arrays.fill(was, false);
		was[s] = true;
		
		int[] que = new int[n * n];
		int add = 1, rem = 0;
		que[0] = s;
		
		s = 0;
		
		int node;
		while (rem < add) {
			node = que[rem];
			++rem;
			for (int i = 0; i < n; i++)
				if (map[node][i] == 1 && !was[i]) {
					++s;
					was[i] = true;
					que[add] = i;
					++add;
				}
		}
		
		out.println(s);
		out.close();
	}
}