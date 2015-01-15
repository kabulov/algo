import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		
		int[][] inp = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				inp[i][j] = nextInt();
		
		fst = new point[n + 2][n + 2];
		scd = new point[n + 2][n + 2];
		
		for (int i = 0; i < n + 2; i++) {
			fst[i][0] = fst[i][n + 1] = fst[0][i] = fst[n + 1][i] = 
			scd[i][0] = scd[i][n + 1] = scd[0][i] = scd[n + 1][i] = 
				new point(0, 0);
		}
		
		for (int i = 1; i < n + 1; i++)
			for (int j = 1; j < n + 1; j++) {
				fst[i][j] = (inp[i - 1][j - 1] == 0) ? 	new point(0, 0) : new point(i, j);
				scd[i][j] = new point(0, 0);
			}
			
		change = true;
		while (change) {
			change = false;
			
			tmp = fst;
			fst = scd;
			scd = tmp;
			
			for (int i = 1; i <= n; i++) 
				for (int j = 1; j <= n; j++) {
					p = scd[i][j];
					if (p.i == 0 && p.j == 0) {
						p = null;
						check(i, j, i - 1, j);
						check(i, j, i + 1, j);
						check(i, j, i, j - 1);
						check(i, j, i, j + 1);
					} else {
						fst[i][j].i = p.i;
						fst[i][j].j = p.j;
					}
				}
		}
				
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				if (fst[i][j].i == Integer.MAX_VALUE || fst[i][j].j == 0)
					out.print("0 ");
				else
					out.print(inp[fst[i][j].i - 1][fst[i][j].j - 1] + " ");
			out.println();
		}
		out.close();
	}
	
	static point p;
	static boolean change;
	static point[][] fst, scd, tmp;
	static void check(int i, int j, int ic, int jc) {
		if (scd[ic][jc].i == 0 && scd[ic][jc].j == 0)
			return;
		
		if (p == null) {
			p = scd[ic][jc];
			fst[i][j].i = p.i;
			fst[i][j].j = p.j;
			change = true;
			return;
		} 
		
		if (scd[ic][jc].i != p.i || scd[ic][jc].j != p.j) 
			fst[i][j].i = Integer.MAX_VALUE;
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static class point {
		int i, j;
		point(int ic, int jc) {
			i = ic;
			j = jc;
		}
	}
}