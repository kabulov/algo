import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		boolean[][][][][] map = new boolean[10][10][10][10][10];  
		for (int i1 = 0; i1 < 10; ++i1)
			for (int i2 = 0; i2 < 10; ++i2)
				for (int i3 = 0; i3 < 10; ++i3)
					for (int i4 = 0; i4 < 10; ++i4)
						for (int i5 = 0; i5 < 10; ++i5)
							map[i1][i2][i3][i4][i5] = false;
		
		char[] vect = in.next().toCharArray();
		boolean ans = false;
		
		int[] ind = new int[5];
		ind[0] = ind[1] = ind[2] = ind[3] = ind[4] = 0;
		
		for (int i = k - 1; i < n; ++i) {
			for (int j = 0; j < k; ++j)
				ind[4 - j] = (vect[i - j] - '0');
			
			if (map[ind[0]][ind[1]][ind[2]][ind[3]][ind[4]]) {
				ans = true;
				break;
			}
			
			map[ind[0]][ind[1]][ind[2]][ind[3]][ind[4]] = true;
		}
		
		out.println(ans ? "YES" : "NO");
		
		in.close();
		out.close();
	}
}