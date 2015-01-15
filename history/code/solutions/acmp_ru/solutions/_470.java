import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		Input in = new Input("input.txt", 1024 * 1024);
		
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		
		int i, j;
		
		int[][] map = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++)
			map[i][0] = 0;
		
		for (j = 1; j <= m; j++)
			map[0][j] = 0;
		
		for (i = 1; i <= n; i++) 
			for (j = 1; j <= m; j++) {
				map[i][j] = map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1] + in.nextInt();
			}
				
		int iup, jup, idn, jdn;
		for (int p = 0; p < k; p++) {
			iup = in.nextInt();
			jup = in.nextInt();
			idn = in.nextInt();
			jdn = in.nextInt();
			
			out.println(map[idn][jdn] - map[idn][jup - 1] - map[iup - 1][jdn] + map[iup - 1][jup - 1]);
		}
		
		out.close();
	}
}

class Input {
	byte[] buffer;
	int bufpos;
	int bufsize;
	
	int number;
	
	byte c;
	
	FileInputStream in;
	
	Input(final String name, int bufsiz) throws IOException {
		in = new FileInputStream(new File(name)); 
		
		buffer = new byte[bufsiz];
		bufsize = in.read(buffer);
		
		bufpos = 0;
		c = buffer[0];
	}
	
	int nextInt() throws IOException {
		number = 0;
		
		while (!('0' <= c && c <= '9')) {
			++bufpos;
			if (bufpos >= bufsize) {
				bufsize = in.read(buffer);
				bufpos = 0;
			}
			c = buffer[bufpos];
		}
		
		do {
			number = number * 10 + c - 48;
			++bufpos;
			if (bufpos >= bufsize) {
				if ((bufsize = in.read(buffer)) <= 0)
					break;
				bufpos = 0;
			}
			c = buffer[bufpos];
		} while ('0' <= c && c <= '9');
		
		return number;
	}
}