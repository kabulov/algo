import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int fi, fj;
		String str = in.next();	
		fj = str.charAt(0) - 'A';
		fi = str.charAt(1) - 49;
		
		int si, sj;
		str = in.next();	
		sj = str.charAt(0) - 'A';
		si = str.charAt(1) - 49;
		
		int ti, tj;
		str = in.next();	
		tj = str.charAt(0) - 'A';
		ti = str.charAt(1) - 49;
		
		map = new int[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				map[i][j] = 0;
		
		int[][] knight = {
				{2, 1},
				{2, -1},
				{-2, 1},
				{-2, -1},
				{1, 2},
				{1, -2},
				{-1, 2},
				{-1, -2},
		};
		
		
		map[fi][fj] = map[si][sj] = map[ti][tj] = -1;
		for (int i = 0; i < 8; i++) {
			if (map[si][i] == 0)
				map[si][i] = 1;
			if (map[i][sj] == 0)
				map[i][sj] = 1;
			
			if (map[fi][i] == 0)
				map[fi][i] = 1;
			if (map[i][fj] == 0)
				map[i][fj] = 1;
			if (in_free(fi + i, fj + i))
				map[fi + i][fj + i] = 1;
			if (in_free(fi - i, fj - i))
				map[fi - i][fj - i] = 1;
			if (in_free(fi - i, fj + i))
				map[fi - i][fj + i] = 1;
			if (in_free(fi + i, fj - i))
				map[fi + i][fj - i] = 1;
			
			if (in_free(ti + knight[i][0], tj + knight[i][1]))
				map[ti + knight[i][0]][tj + knight[i][1]] = 1;
				
		}
		
				
		
		int answer = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (map[i][j] == 1)
					answer++;
		
		out.println(answer);
		out.close();
	}
	
	static int[][] map;
	static boolean in_free(int i, int j) {
		return (0 <= i && i < 8) && (0 <= j && j < 8) && map[i][j] == 0;
	}
}