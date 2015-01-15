import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Locale;


public class Main implements constants {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		
		int n = nextInt();
		int m = nextInt();
		
		int[][] inp = new int[n][m];
		int[][] map = new int[2 * n + 1][2 * m + 1];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				inp[i][j] = nextInt();
		
//		for (int i = 0; i < n; i++)
//			for (int j = 0; j < m; j++)
//				out.print(inp[i][j]);
		
		
		for (int i = 0; i <= 2 * n; i++) {
			map[i][0] = OUTER_WALL;
			map[i][2 * m] = OUTER_WALL;
		}
		
		for (int j = 1; j < 2 * m; j++) {
			map[0][j] = OUTER_WALL;
			map[2 * n][j] = OUTER_WALL;
		}
		
		for (int i = 1; i < 2 * n; i++)
			for (int j = 1; j < 2 * m; j++)
				map[i][j] = NUSED;
		
//		for (int i = 0; i <= 2 * n; i++) {
//			for (int j = 0; j <= 2 * m; j++)
//				out.print(map[i][j] + " ");
//			out.println();
//		}
		
		int num;
		cell tmp;
		int ii = 0, jj = 0;
		LinkedList<cell> list = new LinkedList<cell>();

		for (int i = 0; i < n; i++)	
			for (int j = 0; j < m; j++) {
				ii = i + 1;
				jj = j + 1;
				if (map[2 * ii - 1][2 * jj - 1] == NUSED) {
					num = inp[i][j];
					list.add(new cell(i, j));
					map[2 * ii - 1][2 * jj - 1] = ROOM;
					
					while (!list.isEmpty()) {
						tmp = list.poll();
						ii = tmp.i;
						jj = tmp.j;
						if (ii > 0 && inp[ii - 1][jj] == num) {
							if (map[2 * ii - 1][2 * (jj + 1) - 1] == NUSED) {
								map[2 * ii - 1][2 * (jj + 1) - 1] = ROOM;
								list.add(new cell(ii - 1, jj));
							}
							map[2 * ii][2 * (jj + 1) - 1] = MID_ROOM;							
						}
						if (ii < (n - 1) && inp[ii + 1][jj] == num) {
							if (map[2 * (ii + 2) - 1][2 * (jj + 1) - 1] == NUSED) {
								map[2 * (ii + 2) - 1][2 * (jj + 1) - 1] = ROOM;
								list.add(new cell(ii + 1, jj));
							}
							map[2 * (ii + 1)][2 * (jj + 1) - 1] = MID_ROOM;
						}
						if (jj > 0 && inp[ii][jj - 1] == num) {
							if (map[2 * (ii + 1) - 1][2 * jj - 1] == NUSED) {
								map[2 * (ii + 1) - 1][2 * jj - 1] = ROOM;
								list.add(new cell(ii, jj - 1));
							}
							map[2 * (ii + 1) - 1][2 * jj] = MID_ROOM;
						}
						if (jj < (m - 1) && inp[ii][jj + 1] == num) {
							if (map[2 * (ii + 1) - 1][2 * (jj + 2) - 1] == NUSED) {
								map[2 * (ii + 1) - 1][2 * (jj + 2) - 1] = ROOM;
								list.add(new cell(ii, jj + 1));
							}
							map[2 * (ii + 1) - 1][2 * (jj + 1)] = MID_ROOM;
						}
					}
//					for (int iii = 0; iii <= 2 * n; iii++) {
//						for (int jjj = 0; jjj <= 2 * m; jjj++)
//							out.print(map[iii][jjj] + " ");
//						out.println();
//					}
//					out.println();
				}
			}
	
//		for (int i = 0; i <= 2 * n; i++) {
//			for (int j = 0; j <= 2 * m; j++)
//				out.print(map[i][j] + " ");
//			out.println();
//		}

		for (int i = 2; i < 2 * n - 1; i++)
			for (int j = 2; j < 2 * m - 1; j++)
				if (map[i][j] == NUSED && map[i + 1][j] == MID_ROOM && map[i - 1][j] == MID_ROOM && map[i][j + 1] == MID_ROOM && map[i][j - 1] == MID_ROOM)
					map[i][j] = ROOM;

//		for (int i = 0; i <= 2 * n; i++) {
//			for (int j = 0; j <= 2 * m; j++)
//				out.print(map[i][j] + " ");
//			out.println();
//		}

		int answer = 0;
		ii = 0; jj = 0;
//		out.println(ii + " " + jj);
		
		for (int i = 1; i < 2 * n; i++) {
			for (int j = 1; j < 2 * m; j++)
				if (map[i][j] == NUSED) {
					ii = i;
					jj = j;
				
		list.add(new cell(ii, jj));
		while (!list.isEmpty()) {
			tmp = list.poll();
			ii = tmp.i;
			jj = tmp.j;
			
			if (map[ii][jj] == USED) {
				continue;
			}
			map[ii][jj] = USED;
			
			num = 0;
			if (map[ii][jj + 1] == NUSED || map[ii][jj + 1] == USED)
				num++;
			if (map[ii][jj - 1] == NUSED || map[ii][jj - 1] == USED)
				num++;
			if (map[ii - 1][jj] == NUSED || map[ii - 1][jj] == USED)
				num++;
			if (map[ii + 1][jj] == NUSED || map[ii + 1][jj] == USED)
				num++;

//			System.out.println(ii + " " + jj + " " + num + " " + answer);
			
			if (num == 0) {
				answer += 4;
			} else
			if (num == 1) {
				answer += 4;
			} else
			if (num == 2) {
				if ((map[ii][jj - 1] == NUSED || map[ii][jj - 1] == USED) && (map[ii][jj + 1] == NUSED || map[ii][jj + 1] == USED)) {
					if (map[ii - 1][jj - 1] == ROOM && map[ii - 1][jj + 1] == ROOM && map[ii + 1][jj - 1] == ROOM && map[ii + 1][jj + 1] == ROOM)
						answer += 1;
					else
						answer += 4;
				} else 
				if ((map[ii - 1][jj] == NUSED || map[ii - 1][jj] == USED) && (map[ii + 1][jj] == NUSED || map[ii + 1][jj] == USED)) {
					if (map[ii - 1][jj - 1] == ROOM && map[ii - 1][jj + 1] == ROOM && map[ii + 1][jj - 1] == ROOM && map[ii + 1][jj + 1] == ROOM)
						answer += 1;
					else
						answer += 4;
				} else {
					answer += 1;
				}
			} else {
				answer += 1;
			}
			
			if (map[ii - 1][jj] == NUSED) {
				list.add(new cell(ii - 1, jj));
			}
			if (map[ii + 1][jj] == NUSED) {
				list.add(new cell(ii + 1, jj));
			}
			if (map[ii][jj - 1] == NUSED) {
				list.add(new cell(ii, jj - 1));
			}
			if (map[ii][jj + 1] == NUSED) {
				list.add(new cell(ii, jj + 1));
			}
		}
		}
		}
		out.printf(Locale.US, "%.5f", (double)answer * 3 / 25);
//		out.println((double)answer * 3 / 25);
		out.close();
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
	static PrintWriter out;
}

interface constants {
	int NUSED = -3;
	int OUTER_WALL = -2;
	int ROOM = -1;
	int USED = 1;
	int MID_ROOM = -4;
}

class cell {
	int i, j;
	cell(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
