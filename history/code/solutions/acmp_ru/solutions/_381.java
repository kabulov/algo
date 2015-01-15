import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = Integer.parseInt(in.readLine());
		
		map = new char[n + 2][n + 2];
		int bi = 0, bj = 0, ei, ej;
		
		String str;
		for (int i = 1; i <= n; i++) {
			str = in.readLine();
			for (int j = 1; j <= n; j++) {
				map[i][j] = str.charAt(j - 1);
				if (map[i][j] == '@') {
					bi = i;
					bj = j;
				} else
				if (map[i][j] == 'X') {
					ei = i;
					ej = j;
				}
			}
		}		
		
		for (int i = 0; i <  n + 2; i++) 
			map[i][0] = map[i][n + 1] = map[0][i] = map[n + 1][i] = 'O';
		
		iv = new int[n * n];
		jv = new int[n * n];
		par = new int[n * n];
		boolean found;
		
		found = false;
		par[0] = 0;
		iv[0] = bi;
		jv[0] = bj;
		head = 1;
		tail = 0;
		
		int i, j;
		while (tail < head) {
			i = iv[tail];
			j = jv[tail];
			
			check(i + 1, j);
			check(i - 1, j);
			check(i, j + 1);
			check(i, j - 1);
			
			if (map[i - 1][j] == 'X' || map[i + 1][j] == 'X' || map[i][j + 1] == 'X' || map[i][j - 1] == 'X') {
				found = true;

				while (par[tail] != tail) {
					map[iv[tail]][jv[tail]] = '+';
					tail = par[tail];
				}
				
				break;
			}
			
			tail++;
		}
		
		if (!found)
			out.println("No");
		else {
			out.println("Yes");
			char c;
			for (i = 1; i <= n; i++) {
				for (j = 1; j <= n; j++) {
					c = map[i][j];
					switch (map[i][j]) {
					case 'X':
						c = '+';
						break;
					case '-':
						c = '.';
						break;
					}
					out.print(c);
				}
				out.println();
			}
		}
		
		out.close();
	}

	static void check(int i, int j) {
		if (map[i][j] == '.') {
			map[i][j] = '-';
			iv[head] = i;
			jv[head] = j;
			par[head] = tail;
			head++;
		}
	}
	
	static char[][] map;
	static int[] iv, jv, par;
	static int head, tail;
}
