import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String[] buf = in.next().split("[:]");
		init(Integer.parseInt(buf[0]), Integer.parseInt(buf[1]));		
		
		while (s > 0) {
			++ans;
			modify();
		}
		
		out.println(ans);
		out.close();
	}	
	
	static void modify() {
		++m;
		if (m == 60) {
			m = 0;
			++h;
			if (h == 24) 
				h = 0;			
		}
		
		for (int i = h < 10 ? 10 : h / 10, j = 0; j < 7; ++j) {
			if (state[0][j] != map[i][j] && flag[0][j] == 0) { 
				--s;
				flag[0][j] = 1;
			}
			state[0][j] = map[i][j];
		}
		
		
		for (int i = h % 10, j = 0; j < 7; ++j) {
			if (state[1][j] != map[i][j] && flag[1][j] == 0) { 
				--s;
				flag[1][j] = 1;
			}
			state[1][j] = map[i][j];
		}

		for (int i = m / 10, j = 0; j < 7; ++j) {
			if (state[2][j] != map[i][j] && flag[2][j] == 0) { 
				--s;
				flag[2][j] = 1;
			}
			state[2][j] = map[i][j];
		}

		for (int i = m % 10, j = 0; j < 7; ++j) {
			if (state[3][j] != map[i][j] && flag[3][j] == 0) { 
				--s;
				flag[3][j] = 1;
			}
			state[3][j] = map[i][j];
		}

	}
	
	static void init(int hour, int min) {
		h = hour;
		m = min;
		s = 27;
		ans = 0;
		
		state = new int[4][7];
		
		for (int i = hour < 10 ? 10 : hour / 10, j = 0; j < 7; ++j)
			state[0][j] = map[i][j];

		for (int i = hour % 10, j = 0; j < 7; ++j)
			state[1][j] = map[i][j];
		
		for (int i = min / 10, j = 0; j < 7; ++j)
			state[2][j] = map[i][j];
		
		for (int i = min % 10, j = 0; j < 7; ++j)
			state[3][j] = map[i][j];		
		
		flag = new int[4][7];
		for (int i = 0; i < 4; ++i)
			Arrays.fill(flag[i], 0);
		flag[0][5] = 1;
	}
	
	static int m, h, s, ans;
	
	static int[][] state, flag;
	
	static int[][] map = {
		{1, 1, 1, 1, 1, 1, 0},
		{0, 1, 1, 0, 0, 0, 0},
		{1, 1, 0, 1, 1, 0, 1},
		{1, 1, 1, 1, 0, 0, 1},
		{0, 1, 1, 0, 0, 1, 1},
		{1, 0, 1, 1, 0, 1, 1},
		{1, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 0, 1, 1},
		{0, 0, 0, 0, 0, 0, 0}
	};
}