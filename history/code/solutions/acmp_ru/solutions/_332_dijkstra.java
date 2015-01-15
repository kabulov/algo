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
			for (int j = 0; j < n - i; j++)
				map[i][j] = in.nextInt();
		
		min = new int[n];
		node = new int[n];
			
		int m = 0;
		for (int i = 0; i < n; i++) {
			node[i] = i;
			min[i] = map[0][i];
			if (min[i] < min[m]) m = i;
		}
		
		swap(0, m);
		
		int p = 0;
		while (node[p] != n - 1) {
			m = p + 1;
			for (int i = p + 1; i < n; i++) {
				if (node[p] < node[i]) {
					int tmp = min[p] + map[node[p] + 1][node[i] - node[p] - 1];
					if (tmp < min[i]) min[i] = tmp;
				}
				if (min[i] < min[m]) m = i;
			}
			swap(p + 1, m);
			++p;
		}
		
		out.println(min[p]);
		out.close();
	}
	
	
	static void swap(int i, int j) {
		if (i == j) return;
		int tmp = min[i];
		min[i] = min[j];
		min[j] = tmp;
		tmp = node[i];
		node[i] = node[j];
		node[j] = tmp;
	}
	
	static int[] min, node;
}
