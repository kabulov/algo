import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		char[][] map = new char[n + 4][n + 4];
		
		for (int i = 0; i < n + 4; i++) {
			map[i][0] = map[i][1] = map[i][n + 2] = map[i][n + 3] = '#';
			map[0][i] = map[1][i] = map[n + 2][i] = map[n + 3][i] = '#';
		}
		
		int di, dj;
		int tail = 0, head = 0;
		int[] iv = new int[2500];
		int[] jv = new int[2500];
		int[] par = new int[2500];
		
		char c;
		String str;
		for (int i = 2; i < n + 2; i++) {
			str = in.readLine();
			for (int j = 2; j < n + 2; j++) {
				map[i][j] = c = str.charAt(j - 2);
				if (c == '@') 
					if (head == 0) {
						map[i][j] = '$';
						iv[0] = i;
						jv[0] = j;
						par[0] = -1;
						head = 1;
					} else {
						di = i;
						dj = j;
					}						
			}
		}
		
		int[][] knight = {
				{-2, 1},
				{-2, -1},
				{2, 1},
				{2, -1},
				{1, 2},
				{1, -2},
				{-1, 2},
				{-1, -2},
		};
		
		int i, j, ci, cj;
		boolean reach = false;
outer:	while (tail < head) {
			i = iv[tail];
			j = jv[tail];
			
			for (int k = 0; k < 8; k++) { 
				ci = i + knight[k][0];
				cj = j + knight[k][1];
				if (map[ci][cj] == '@') {
					while (par[tail] != -1) {
						map[iv[tail]][jv[tail]] = '@';
						tail = par[tail];
					}
					map[iv[0]][jv[0]] = '@';
					reach = true;
					break outer;
				} else 
				if (map[ci][cj] == '.') {
					map[ci][cj] = '$';
					par[head] = tail;
					iv[head] = ci;
					jv[head] = cj;
					++head;
				}
			}
			
			++tail;
		}
		
		if (!reach)
			out.println("Impossible");
		else {
			for (i = 2; i < n + 2; i++) {
				for (j = 2; j < n + 2; j++)
					out.print(map[i][j] == '$' ? '.' : map[i][j]);
				
				out.println();
			}
		}
		
		out.close();
	}
}
