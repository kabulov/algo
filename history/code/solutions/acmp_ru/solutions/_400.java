import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int[][] v = new int[2][6];
		
		int buf;
		for (int i = 0; i < 6; i++) {
			v[0][i] = in.nextInt();
			v[1][i] = in.nextInt();
			//v[0][i] <= v[1][i]
			if (v[0][i] > v[1][i]) {
				buf = v[0][i];
				v[0][i] = v[1][i];
				v[1][i] = buf;
			}
		}
			
		int min;
		for (int i = 0; i < 5; i++) {
			min = i;
			for (int j = i + 1; j < 6; j++)
				if (v[0][min] > v[0][j] || (v[0][min] == v[0][j] && v[1][min] > v[1][j]))
					min = j;
			
			buf = v[0][min];
			v[0][min] = v[0][i];
			v[0][i] = buf;
			
			buf = v[1][min];
			v[1][min] = v[1][i];
			v[1][i] = buf;
		}
		
		boolean possible = false;
		if (v[0][0] == v[1][0] && equal(v[0], 0, 5) && equal(v[1], 0, 5))
			possible = true;
		else
		if (equal(v[0], 0, 3) && equal(v[1], 0, 1) && equal(v[1], 4, 5) && equal(v[0], 4, 5) && equal(v[1], 2, 3) && 
																						v[0][0] == v[1][0] && v[0][0] == v[1][5] && v[0][5] == v[1][2])
			possible = true;
		else
		if (equal(v[0], 0, 3) && equal(v[0], 4, 5) && equal(v[1], 2, 5) && equal(v[1], 0, 1) && v[0][5] == v[1][0])
			possible = true;
			
		if (possible)
			out.println("POSSIBLE");
		else
			out.println("IMPOSSIBLE");
		
		out.close();
	}
	
	static boolean equal(int[] v, int l, int r) {
		for (int i = l + 1; i <= r; i++)
			if (v[i] != v[i - 1])
				return false;
		
		return true;
	}
}
