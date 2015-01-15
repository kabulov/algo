import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		w = in.nextInt();
		h = in.nextInt();
		
		inp = new boolean[h + 2][w + 2];
		for (int i = 0; i < h + 2; i++)
			inp[i][0] = inp[i][w + 1] = false;
		
		for (int j = 0; j < w + 2; j++)
			inp[0][j] = inp[h + 1][j] = false;
		
		String str;
		for (int i = 1; i <= h; i++) {
			str = in.next();
			for (int j = 1; j <= w; j++)
				inp[i][j] = str.charAt(j - 1) == '.' ? false : true;
		}
		
		mholes = 0;
		min = Integer.MAX_VALUE;
		map = new int[h + 5][w + 5];
		for (int i = 0; i < h + 5; ++i)
			for (int j = 0; j < w + 5; ++j)
				map[i][j] = 0;
		
		int push, pop;
		point[] que = new point[105 * 105];
		for (int i = 0; i < 105 * 105; i++)
			que[i] = new point();
		
		cur = 0;
		int ic, jc;

		for (int i = 1; i <= h; i++) 
			for (int j = 1; j <= w; j++)
				if (inp[i][j]) {
					area = 0;
					maxj = maxi = Integer.MIN_VALUE;
					minj = mini = Integer.MAX_VALUE;
					
					pop = 0;
					push = 1;
					inp[i][j] = false;
					que[0].i = i;
					que[0].j = j;
					
					++cur;
					while (pop < push) {
						ic = que[pop].i;
						jc = que[pop].j;
						++pop;
						
						++area;
						map[ic + 1][jc + 1] = cur;
						if (ic + 1 < mini)
							mini = ic + 1;
						if (ic + 1 > maxi)
							maxi = ic + 1;
						if (jc + 1 < minj)
							minj = jc + 1;
						if (jc + 1 > maxj)
							maxj = jc + 1;
						
						if (inp[ic - 1][jc]) {
							inp[ic - 1][jc] = false;
							que[push].i = ic - 1;
							que[push].j = jc;
							++push;
						}
						if (inp[ic + 1][jc]) {
							inp[ic + 1][jc] = false;
							que[push].i = ic + 1;
							que[push].j = jc;
							++push;
						}
						if (inp[ic][jc - 1]) {
							inp[ic][jc - 1] = false;
							que[push].i = ic;
							que[push].j = jc - 1;
							++push;
						}
						if (inp[ic][jc + 1]) {
							inp[ic][jc + 1] = false;
							que[push].i = ic;
							que[push].j = jc + 1;
							++push;
						}
					}
					
					for (int iin = mini - 1; iin <= maxi + 1; ++iin)
						for (int jin = minj - 1; jin <= maxj + 1; ++jin)
							if (map[iin][jin] != cur)
								map[iin][jin] = 0;
					
					for (int iin = mini - 2; iin <= maxi + 2; ++iin)
						map[iin][minj - 2] = map[iin][maxj + 2] = cur;
					
					for (int jin = minj - 2; jin <= maxj + 2; ++jin)
						map[mini - 2][jin] = map[maxi + 2][jin] = cur;
					
					pop = 0;
					push = 1;
					que[0].i = mini - 1;
					que[0].j = minj - 1;
					map[mini - 1][minj - 1] = cur;
					while (pop < push) {
						ic = que[pop].i;
						jc = que[pop].j;
						++pop;
						
						if (map[ic - 1][jc] == 0) {
							map[ic - 1][jc] = cur;
							que[push].i = ic - 1;
							que[push].j = jc;
							++push;
						}
						if (map[ic + 1][jc] == 0) {
							map[ic + 1][jc] = cur;
							que[push].i = ic + 1;
							que[push].j = jc;
							++push;
						}
						if (map[ic][jc - 1] == 0) {
							map[ic][jc - 1] = cur;
							que[push].i = ic;
							que[push].j = jc - 1;
							++push;
						}
						if (map[ic][jc + 1] == 0) {
							map[ic][jc + 1] = cur;
							que[push].i = ic;
							que[push].j = jc + 1;
							++push;
						}
					}
					
					holes = 0;
					for (int iin = mini; iin <= maxi; ++iin)
						for (int jin = minj; jin <= maxj; ++jin)
							if (map[iin][jin] == 0) {
								++holes;
								
								pop = 0;
								push = 1;
								que[0].i = iin;
								que[0].j = jin;
								map[iin][jin] = cur;
								while (pop < push) {
									ic = que[pop].i;
									jc = que[pop].j;
									++pop;
									
									if (map[ic - 1][jc] == 0) {
										map[ic - 1][jc] = cur;
										que[push].i = ic - 1;
										que[push].j = jc;
										++push;
									}
									if (map[ic + 1][jc] == 0) {
										map[ic + 1][jc] = cur;
										que[push].i = ic + 1;
										que[push].j = jc;
										++push;
									}
									if (map[ic][jc - 1] == 0) {
										map[ic][jc - 1] = cur;
										que[push].i = ic;
										que[push].j = jc - 1;
										++push;
									}
									if (map[ic][jc + 1] == 0) {
										map[ic][jc + 1] = cur;
										que[push].i = ic;
										que[push].j = jc + 1;
										++push;
									}
								}
							}
					
					if (holes > 0)
						if (holes > mholes) {
							mholes = holes;
							min = area;
						} else
						if (holes == mholes) {
							if (area < min)
								min = area;
						}
				}
		
		if (min == Integer.MAX_VALUE)
			min = 0;
		
		out.println(min);
		out.close();
	}
	
	static int cur;
	
	static int maxi, mini;
	static int maxj, minj;
	static int area, holes;
	
	static int min, mholes;
	
	static int w, h;
	static boolean[][] inp;
	static int[][] map;
}

class point {
	int i, j;
}
