import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		map = new int[n + 1][m + 1];
		
		int minv = n + 1, maxv = 0;
		int minh = m + 1, maxh = 0;
		
		String str;
		for (int i = 1; i <= n; i++) {
			str = in.readLine();
			for (int j = 1; j <= m; j++) {
				map[i][j] = map[i - 1][j] - map[i - 1][j - 1] + map[i][j - 1];
				if (str.charAt(j - 1) == '*') {
					map[i][j]++;
					
					if (i < minv)
						minv = i;
					if (i > maxv)
						maxv = i;
					if (j < minh)
						minh = j;
					if (j > maxh)
						maxh = j;
				}
			}
		}
		
		answer = false;
		
		if (maxh == 0) {
			end();
			return;
		}
		
		total = map[n][m];
		
		int size;
		int jr, id;
		int imax, jmax;
		int sz = Math.min(maxv - minv + 1, maxh - minh + 1);
outer:	for (int part = 0; part < 5; part++) {
			size = sz - part;
			if (size <= 0)
				break;
			
			imax = maxv - size + 1;
			jmax = maxh - size + 1;
			
			for (int i = minv; i <= imax; i++)
				for (int j = minh; j <= jmax; j++)
					if (full(i, j, i + size - 1, j + size - 1)) {
						id = i + size - 1;
						jr = j + size - 1;
						if (size > 2) {
							if (j > 2 && jr < m - 1 && i > 2 && id < n - 1 && emptyOut(i - 2, j - 2, id + 2, jr + 2)) {
								answer = true;
								break outer;								
							}
							
							if (j > 1 && jr < m && i > 1 && id < n && emptyOut(i - 1, j - 1, id + 1, jr + 1)) {
								answer = true;
								break outer;								
							}
							
							if (emptyOut(i, j, id, jr)) {
								answer = true;
								break outer;
							}
						} else {
							if (i > 1 && j > 1 && id < n && jr < m) {
								if (j > 2 && jr < m - 1 && i > 2 && id < n - 1 && emptyOut(i - 2, j - 2, id + 2, jr + 2)) {
									answer = true;
									break outer;								
								}
									
								if (emptyOut(i - 1, j - 1, id + 1, jr + 1)) {
									answer = true;
									break outer;
								}
							}
						}
					}
		}
		
		end();
	}
	
	static boolean emptyOut(int ilu, int jlu, int ird, int jrd) {
		return total == map[ird][jrd] - map[ird][jlu - 1] - map[ilu - 1][jrd] + map[ilu - 1][jlu - 1];
	}
	
	static boolean full(int ilu, int jlu, int ird, int jrd) {
		return (ird - ilu + 1) * (jrd - jlu + 1) == map[ird][jrd] - map[ird][jlu - 1] - map[ilu - 1][jrd] + map[ilu - 1][jlu - 1];
	}
	
	static void end() {
		if (answer)
			out.println("SQUARE");
		else
			out.println("CIRCLE");
		out.close();
	}
	
	static int[][] map;
	static int total;
	static boolean answer;
	static BufferedReader in;
	static PrintWriter out;
}