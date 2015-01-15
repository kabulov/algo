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
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = in.nextInt();
		
		int a = in.nextInt() - 1, b = in.nextInt() - 1;
		
		if (a == b) 
			out.println(0);
		else {
			boolean[] was = new boolean[n];
			Arrays.fill(was, false);
			was[a] = true;
			
			int[] len = new int[n * n];
			int[] que = new int[n * n];
			len[0] = 0;
			que[0] = a;
			int add = 1, rem = 0;
outer:		while (rem < add) {
				int node = que[rem];
				for (int i = 0; i < n; i++)
					if (map[node][i] != 0 && !was[i]) {
						que[add] = i;
						len[add] = len[rem] + 1;
						if (i == b) break outer;
						was[i] = true;
						++add;
					}
				++rem;
			}
			if (rem == add)
				out.println(-1);
			else
				out.println(len[add]);
		}
		
		out.close();
	}
}