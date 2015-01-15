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
		
		list = new int[n][n];
		size = new int[n];
		
		for (int i = 0; i < n; ++i) {
			size[i] = 0;
			for (int j = 0; j < n; ++j)
				if (in.nextInt() == 1) {
					list[i][size[i]] = j;
					++size[i];
				}
		}
					
		was = new boolean[n];
		Arrays.fill(was, false);
		was[s] = true;
	
		ans = -1;
		bfs(s);
		
		out.println(ans);
		out.close();
	}
	
	static void bfs(int p) {
		++ans;
		was[p] = true;
		for (int i = 0; i < size[p]; ++i)
			if (!was[list[p][i]])
				bfs(list[p][i]);
	}
	
	static int ans;
	static int[] size;
	static int[][] list;
	static boolean[] was;
}