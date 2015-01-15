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
		
		hv = new int[n];
		
		for (int i = 0; i < n; ++i)
			hv[i] = in.nextInt();
		
		matr = new int[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				matr[i][j] = in.nextInt();
		
		hash();
		map = new int[h + 1];
		Arrays.fill(map, 0);
		
		int lt = 0, rt = 1;
		int[] que = new int[h + 1];
		que[0] = h;
		map[h] = 2;
		
		int mid;
		while (lt < rt) {
			mid = h = que[lt++];
			unhash();

			for (int i = 0; i < n; ++i) {
				if (hv[i] == 0) continue;
				for (int j = i; j < n; ++j) {
					if (hv[i] > 0 && hv[j] > 0 && (matr[i][j] != 0 || matr[j][i] != 0)) {
						if (i == j && hv[i] < 2) continue;					

						map[mid] = 1;

						if (matr[i][j] != 0)
							--hv[j];
						if (matr[j][i] != 0)
							--hv[i];
						
						hash();
						
						if (map[h] == 0) {
							map[h] = 2;
							que[rt++] = h;
						}
						
						h = mid;
						unhash();
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 1; i < map.length; ++i)
			if (map[i] == 2)
				++ans;
		
		out.println(ans);
		for (int i = 1; i < map.length; ++i) 
			if (map[i] == 2) {
				h = i;
				unhash();
				for (int j = 0; j < n; ++j) {
					out.print(hv[j]);
					out.print(" ");
				}
				out.println();
			}
		
		
		out.close();
	}	
	
	static int[] map;
	static int[][] matr;
	
	static void unhash() {
		int H = h;
		for (int i = n - 1; i >= 0; --i) {
			hv[i] = H % base;
			H /= base;
		}
	}
	
	static void hash() {
		h = hv[0];
		for (int i = 1; i < n; ++i)
			h = h * base + hv[i];
	}
	
	static int base = 3;
	static int n, h;	
	static int[] hv;
}

//to get accepted, one must change main part of code to this : (however it contradicts the tale
/*
						if (matr[i][j] != 0) {
							--hv[j];
							
							hash();
							
							if (map[h] == 0) {
								map[h] = 2;
								que[rt++] = h;
							}

							h = mid;
							unhash();
						}
						
						if (matr[j][i] != 0) {
							--hv[i];						
						
							hash();
							
							if (map[h] == 0) {
								map[h] = 2;
								que[rt++] = h;
							}
							
							h = mid;
							unhash();
						}						

*/