import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main implements cst {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new BufferedReader(new FileReader("input.txt"));
		
		int n = Integer.parseInt(in.readLine());
		map = new int[n + 2][n + 2]; 
		
		for (int i = 0; i < n + 2; i++) 
			map[0][i] = map[i][0] = map[n + 1][i] = map[i][n + 1] = WALL;
		
		map[0][0] = map[1][0] = map[0][1] = ENGAGED;
		map[n + 1][n + 1] = map[n][n + 1] = map[n + 1][n] = ENGAGED;
		
		String str;
		for (int i = 0; i < n; i++) {
			str = in.readLine();
			for (int j = 0; j < n; j++)
				if (str.charAt(j) == '.')
					map[i + 1][j + 1] = FREE;
				else
					map[i + 1][j + 1] = WALL;
		}
		
		amt = 0;
		map[1][1] = ENGAGED;
		solve(1, 1);
		if (map[n][n] == FREE)
			solve(n, n);
		
		out.println(amt * 25);
		out.close();
	}
	
	static void solve(int i, int j) {
		if (map[i - 1][j] == FREE) {
			map[i - 1][j] = ENGAGED;
			solve(i - 1, j);
		} else
		if (map[i - 1][j] == WALL)
			amt++;
		
		if (map[i + 1][j] == FREE) {
			map[i + 1][j] = ENGAGED;
			solve(i + 1, j);
		} else
		if (map[i + 1][j] == WALL)
			amt++;

		if (map[i][j - 1] == FREE) {
			map[i][j - 1] = ENGAGED;
			solve(i, j - 1);
		} else
		if (map[i][j - 1] == WALL)
			amt++;

		if (map[i][j + 1] == FREE) {
			map[i][j + 1] = ENGAGED;
			solve(i, j + 1);
		} else
		if (map[i][j + 1] == WALL)
			amt++;
	}
	
	static int[][] map;
	static int amt;
	
	static PrintWriter out;
	static BufferedReader in;	
}

interface cst {
	int WALL = 1;
	int FREE = 0;
	int ENGAGED = -1;
}