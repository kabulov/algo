import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("input.txt")
		
		game = 0;
		map = new char[8][8];
		
		wking = new Point();
		bking = new Point();
		while (!read()) {
			analyze();
			prn();
			in.readLine();
		}
	}
	
	static Point wking, bking;
	
	static void analyze() {
		bcheck = wcheck = false;
		find(wking, bking);

		if (bking.x < 7) {
			if (bking.y > 0 && map[bking.x + 1][bking.y - 1] == 'P') 
				bcheck = true;
			
			if (bking.y < 7 && map[bking.x + 1][bking.y + 1] == 'P') 
				bcheck = true;
		}
		if (!bcheck) 
			bcheck = check(bking.x, bking.y, true);

		if (bcheck) return;

		if (wking.x > 0) {
			if (wking.y > 0 && map[wking.x - 1][wking.y - 1] == 'p')
				wcheck = true;
			
			if (wking.y < 7 && map[wking.x - 1][wking.y + 1] == 'p')
				wcheck = true;
		}
		if (!wcheck)
			wcheck = check(wking.x, wking.y, false);
	}
	
	static boolean check(int ic, int jc, boolean type) {
		
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (map[i][j] != '.' && (Character.isLowerCase(map[i][j]) ^ type)) 
					switch(map[i][j]) {
					case 'n': case 'N':	
						if (Math.abs((i - ic) * (j - jc)) == 2) return true;
						break;
					case 'q': case 'Q':
						if (rook(i, j, ic, jc) || bishop(i, j, ic, jc)) return true;
						break;
					case 'r': case 'R':
						if (rook(i, j, ic, jc)) return true;
						break;
					case 'b': case 'B':
						if (bishop(i, j, ic, jc)) return true;
						break;
					}
		return false;		
	}
	
	static boolean rook(int i1, int j1, int i2, int j2) {
		if (i1 == i2) {
			int j;
			if (j1 > j2) {
				j = j1;
				j1 = j2;
				j2 = j;
			}
			for (j = j1 + 1; j < j2; j++)
				if (map[i1][j] != '.')
					return false;
			return true;
		} 
		if (j1 == j2) {
			int i;
			if (i1 > i2) {
				i = i1;
				i1 = i2;
				i2 = i;
			}
			for (i = i1 + 1; i < i2; i++)
				if (map[i][j1] != '.')
					return false;
			return true;
		}
		return false;
	}
	
	static boolean bishop(int i1, int j1, int i2, int j2) {
		if (Math.abs(i1 - i2) != Math.abs(j1 - j2)) return false;
		if (i1 > i2) {
			int t = i1;
			i1 = i2;
			i2 = t;
			t = j1;
			j1 = j2;
			j2 = t;
		}

		int ctr = (j1 < j2) ? 1 : -1;

		int i = i1 + 1, j = j1 + ctr;
		while (i < i2 && j != j2) {
			if (map[i][j] != '.') return false;
			++i;
			j += ctr;
		}
		return true;
	}
	
	static void find(Point w, Point b) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (map[i][j] == 'k') {
					b.x = i;
					b.y = j;
				} else
				if (map[i][j] == 'K') {
					w.x = i;
					w.y = j;
				}
	}

	static boolean read() throws IOException {
		boolean empty = true;
		for (int i = 0; i < 8; i++) { 
			map[i] = in.readLine().toCharArray();
			if (empty)
				for (int j = 0; j < 8; j++)
					if (map[i][j] != '.') {
						empty = false;
						break;
					}
		}
		return empty;
	}
	
	static char[][] map;
	
	static int game;
	static boolean wcheck, bcheck;
	static BufferedReader in;
	
	static void prn() {
		System.out.print("Game #");
		System.out.print(++game);
		System.out.print(": ");
		if (wcheck)
			System.out.print("white");
		else
		if (bcheck)
			System.out.print("black");
		else
			System.out.print("no");
		System.out.println(" king is in check.");
	}
}