import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Main implements type {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String[] buf = in.readLine().split(" ");
		int n = Integer.parseInt(buf[0]);
		int m = Integer.parseInt(buf[1]);
		
		int bi = -1, bj = -1;					//
		int[][] map = new int[n + 2][m + 2];
		
		for (int i = 0; i < n + 2; ++i)
			map[i][0] = map[i][m + 1] = MIRROR;
		for (int j = 0; j < m + 2; ++j)
			map[0][j] = map[n + 1][j] = MIRROR;
		
		String str;
		for (int i = 0; i < n; ++i) {
			str = in.readLine();
			for (int j = 0; j < m; ++j)
				switch(str.charAt(j)) {
				case '.':
					map[i + 1][j + 1] = SPACE;
					break;
				case 'X':
					bi = i + 1;
					bj = j + 1;
					map[i + 1][j + 1] = BOTH;
					break;
				case '*':
					map[i + 1][j + 1] = MIRROR;
					break;
				}
		}
		
		int[] iv, jv, v;
		
		iv = new int[4 * n * m];
		jv = new int[4 * n * m];

		iv[0] = iv[1] = iv[2] = iv[3] = bi;
		jv[0] = jv[1] = jv[2] = jv[3] = bj;
		
		v = new int[4 * n * m];
		v[0] = 0;
		v[1] = 1;
		v[2] = 2;
		v[3] = 3;

		int add = 4, rem = 0;
		
		int i, j, t, ni, nj;
		while (rem < add) {
			i = iv[rem];
			j = jv[rem];
			t = v[rem];
			
			ni = i + way[t][0];
			nj = j + way[t][1];
			
			switch(t) {
			case 0:
				if (map[ni][nj] != MIRROR && map[ni][nj] != BOTH && map[ni][nj] != RIGHT) {
					if ((map[i - 1][j] != MIRROR) || (map[i][j + 1] != MIRROR)) {
						map[ni][nj] += RIGHT;
						iv[add] = ni;
						jv[add] = nj;
						v[add] = t;
						++add;
					}
				} else
				if (map[ni][nj] == MIRROR) {
					if (map[i - 1][j] == MIRROR && (map[i][j + 1] == RIGHT || map[i][j + 1] == SPACE)) {
						map[i][j + 1] += LEFT;
						iv[add] = i;
						jv[add] = j + 1;
						v[add] = 1;
						++add;
					} else
					if (map[i][j + 1] == MIRROR && (map[i - 1][j] == SPACE || map[i - 1][j] == RIGHT)) {
						map[i - 1][j] += LEFT;
						iv[add] = i - 1;
						jv[add] = j;
						v[add] = 3;
						++add;
					}
				}
				break;
			case 1:
				if (map[ni][nj] != MIRROR && map[ni][nj] != BOTH && map[ni][nj] != LEFT) {
					if (map[i + 1][j] != MIRROR || map[i][j + 1] != MIRROR) {
						map[ni][nj] += LEFT;
						iv[add] = ni;
						jv[add] = nj;
						v[add] = t;
						++add;
					}
				} else 
				if (map[ni][nj] == MIRROR) {
					if (map[i][j + 1] == MIRROR && (map[i + 1][j] == SPACE || map[i + 1][j] == LEFT)) {
						map[i + 1][j] += RIGHT;
						iv[add] = i + 1;
						jv[add] = j;
						v[add] = 2;
						++add;
					} else 
					if (map[i + 1][j] == MIRROR && (map[i][j + 1] == SPACE || map[i][j + 1] == LEFT)) {
						map[i][j + 1] += RIGHT;
						iv[add] = i;
						jv[add] = j + 1;
						v[add] = 0;
						++add;
					}
				}
				break;
			case 2:
				if (map[ni][nj] != MIRROR && map[ni][nj] != BOTH && map[ni][nj] != RIGHT) {
					if (map[i][j - 1] != MIRROR || map[i + 1][j] != MIRROR) {
						map[ni][nj] += RIGHT;
						iv[add] = ni;
						jv[add] = nj;
						v[add] = t;
						++add;
					}
				} else
				if (map[ni][nj] == MIRROR) {
					if (map[i + 1][j] == MIRROR && (map[i][j - 1] == SPACE || map[i][j - 1] == RIGHT)) {
						map[i][j - 1] += LEFT;
						iv[add] = i;
						jv[add] = j - 1;
						v[add] = 3;
						++add;
					} else 
					if (map[i][j - 1] == MIRROR && (map[i + 1][j] == SPACE || map[i + 1][j] == RIGHT)) {
						map[i + 1][j] += LEFT;
						iv[add] = i + 1;
						jv[add] = j;
						v[add] = 1;
						++add;
					}
				}
				break;
			case 3:
				if (map[ni][nj] != MIRROR && map[ni][nj] != BOTH && map[ni][nj] != LEFT) {
					if (map[i - 1][j] != MIRROR || map[i][j - 1] != MIRROR) {
						map[ni][nj] += LEFT;
						iv[add] = ni;
						jv[add] = nj;
						v[add] = t;
						++add;
					}
				} else
				if (map[ni][nj] == MIRROR) {
					if (map[i][j - 1] == MIRROR && (map[i - 1][j] == SPACE || map[i - 1][j] == LEFT)) {
						map[i - 1][j] += RIGHT;
						iv[add] = i - 1;
						jv[add] = j;
						v[add] = 0;
						++add;
					} else 
					if (map[i - 1][j] == MIRROR && (map[i][j - 1] == SPACE || map[i][j - 1] == LEFT)) {
						map[i][j - 1] += RIGHT;
						iv[add] = i;
						jv[add] = j - 1;
						v[add] = 2;
						++add;
					}
				}
				break;
			}
			
			++rem;
		}
		
		char c;
		for (i = 1; i <= n; ++i) {
			for (j = 1; j <= m; ++j) {
				switch(map[i][j]) {
				case MIRROR:
					c = '*';
					break;
				case SPACE:
					c = '.';
					break;
				case LEFT:
					c = '\\';
					break;
				case RIGHT:
					c = '/';
					break;
				case BOTH:
					c = 'X';
					break;
				default : 
					c = '\0';
				}
				out.print(c);
			}
			out.println();
		}
		
		out.close();
	}
	
	static int[][] way = {
		{-1, 1},
		{1, 1},
		{1, -1},
		{-1, -1}
	};
}

interface type {
	public final int MIRROR = 1, SPACE = 0, LEFT = 2, RIGHT = 3, BOTH = 5;
}