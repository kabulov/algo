import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new  PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		
		Brd bd = new Brd();
		
		String line;
		String[] token;
		
		for (int i = 0; i < n; ++i) {
			line = in.readLine();
			
/*			if (line.charAt(2) == '-') {
				token = line.split("[-]");
				char c1, p1, c2, p2;
				
				c1 = token[0].charAt(0);
				p1 = token[0].charAt(1);
				
				int val = bd.get(c1, p1);
				
				c2 = token[1].charAt(0);
				p2 = token[1].charAt(1);

				bd.clear(c1, p1, c2, p2);				
				bd.put(c2, p2, val);
			} else {*/
				//token = line.split("[:]");
				token = line.split("[:-]");
				
				int val;
				char c1, p1, c2 = token[0].charAt(0), p2 = token[0].charAt(1);
				for (int j = 1; j < token.length; ++j) {
					c1 = c2;
					p1 = p2;
					c2 = token[j].charAt(0);
					p2 = token[j].charAt(1);
					
					val = bd.get(c1, p1);
					bd.clear(c1, p1, c2, p2);
					bd.put(c2, p2, val);
				}
			//}
		}
		
		int val;
		for (char c = '8'; c >= '1'; --c) {
			for (char p = 'a'; p <= 'h'; ++p) 
				if ((val = bd.get(p, c)) == 0) {
					out.print(bd.col(p, c) == 1 ? '.' : '-');
				} else {
					switch(val) {
					case 1:
						out.print('w');
						break;
					case 2:
						out.print('b');
						break;
					case -1:
						out.print('W');
						break;
					case -2:
						out.print('B');
						break;
					}
				}
			
			out.println();
		}
		
		out.close();
	}	
}

class Brd {
	int[][] map;
	
	Brd() {
		map = new int[8][8];
		
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				map[i][j] = 0;
		
		for (char c = 'a'; c <= 'h'; c += 2) put(c, '1', 1);
		for (char c = 'b'; c <= 'h'; c += 2) put(c, '2', 1);
		for (char c = 'a'; c <= 'h'; c += 2) put(c, '3', 1);
		
		for (char c = 'b'; c <= 'h'; c += 2) put(c, '6', 2);
		for (char c = 'a'; c <= 'h'; c += 2) put(c, '7', 2);
		for (char c = 'b'; c <= 'h'; c += 2) put(c, '8', 2);
	}

	int i, j;
	
	private void coord(char c, char n) {
		i = 8 - (n - '0');
		j = c - 'a';
	}
	
	void put(char c, char n, int val) {
		coord(c, n);
		if ((val == 1 && i == 0) || (val == 2 && i == 7)) 
			val = -val;
		
		map[i][j] = val;
	}
	
	int get(char c, char n) {
		coord(c, n);
		return map[i][j];
	}
	
	void clear(char c1, char n1, char c2, char n2) {
		int i1, j1, i2, j2;
		coord(c1, n1);
		i1 = i;
		j1 = j;
		coord(c2, n2);
		i2 = i;
		j2 = j;
		int iv = i2 - i1 < 0 ? -1 : 1, jv = j2 - j1 < 0 ? -1 : 1;
		while (i1 != i2 || j1 != j2) {	//&&
			map[i1][j1] = 0;	//put
			i1 += iv;
			j1 += jv;
		}
		map[i2][j2] = 0; //
	}
	
	int col(char c, char n) {
		coord(c, n);
		return (i + j) % 2 == 1 ? 2 : 1;	//2-b; 1-w
	}
}