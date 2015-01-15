import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		size = new int[n];
		list = new int[n][n];

		for (int i = 0; i < n; ++i) {
			size[i] = 0;
			for (int j = 0; j < n; j++)
				if (in.nextInt() == 1) {
					list[i][size[i]] = j;
					++size[i];
				}
		}
		
		was = new boolean[n];
		Arrays.fill(was, false);
		
		cyc = false;
		bfs();
		
		out.println((one() && !cyc ? "YES" : "NO"));		
		out.close();
	}
	
	static boolean one() {
		for (int i = 0; i < n; i++)
			if (!was[i])
				return false;
		return true;
	}
	
	static void bfs() {
		int[] que = new int[n * n];
		int[] par = new int[n * n];
		int add = 1, rem = 0;
		que[0] = 0;
		par[0] = -1;
		was[0] = true;
		
		int node, newnode;
		while (rem < add) {
			node = que[rem];
			for (int i = 0; i < size[node]; ++i) {
				newnode = list[node][i];
				if (newnode != par[rem])
					if (!was[newnode]) {
						que[add] = newnode;
						par[add] = node;
						was[newnode] = true;
						++add;
					} else {
						cyc = true;
					}
			}
			++rem;
		}
	}
	
	static int n;
	static boolean[] was;
	static int[] size;
	static int[][] list;
	static boolean cyc;
}