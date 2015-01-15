import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Main implements constants {
	public static void main(String[] argv) throws IOException {
		Input in = new Input("input.txt", 2 * 1024 * 1024);
		int n = in.nextInt();
		
		int[] fst = new int[n + 3];
		int[] scd = new int[n + 3];
		int[] thd = new int[n + 3];
		
		fst[0] = fst[n + 1] = fst[n + 2] = SENTINEL;
		scd[0] = scd[n + 1] = scd[n + 2] = SENTINEL;
		thd[0] = thd[n + 1] = thd[n + 2] = SENTINEL;
		
		
		for (int j = 1; j <= n; j++)
			scd[j] = in.nextInt();
		
		for (int j = 1; j <= n; j++)
			thd[j] = in.nextInt();
		
		int tmp;
		int self;
		int[] buf;
		int answer = -1000;
		
		for (int i = 3; i <= n; i++) {
			buf = fst;
			fst = scd;
			scd = thd;
			thd = buf;
			
			for (int j = 1; j <= n; j++)
				thd[j] = in.nextInt();
			
			for (int j = 1; j <= n; j++) {
				self = fst[j];

				tmp = self + fst[j + 1] + fst[j + 2];
				if (tmp > answer)
					answer = tmp;
				
				tmp = self + scd[j] + thd[j];
				if (tmp > answer)
					answer = tmp;
				
				tmp = self + fst[j + 1] + scd[j + 1];
				if (tmp > answer)
					answer = tmp;
				
				tmp = self + scd[j] + scd[j + 1];
				if (tmp > answer)
					answer = tmp;
				
				tmp = self + fst[j - 1] + scd[j - 1];
				if (tmp > answer)
					answer = tmp;
				
				tmp = self + scd[j] + scd[j - 1];
				if (tmp > answer)
					answer = tmp;
			}		
		}
		
		for (int j = 1; j <= n; j++) {
			self = scd[j];
			
			tmp = self + scd[j + 1] + thd[j + 1];
			if (tmp > answer)
				answer = tmp;
			
			tmp = self + thd[j] + thd[j + 1];
			if (tmp > answer)
				answer = tmp;
			
			tmp = self + scd[j - 1] + thd[j - 1];
			if (tmp > answer)
				answer = tmp;
			
			tmp = self + thd[j] + thd[j - 1];
			if (tmp > answer)
				answer = tmp;
		}
		
		for (int j = 1; j < n - 1; j++) {
			tmp = scd[j] + scd[j + 1] + scd[j + 2];
			if (tmp > answer)
				answer = tmp;
			
			tmp = thd[j] + thd[j + 1] + thd[j + 2];
			if (tmp > answer)
				answer = tmp;
		}
				
		PrintWriter out = new PrintWriter("output.txt");
		out.println(answer);
		out.close();
	}
}

class Input {
	byte c;
	int pos;
	int bufsize;
	int filesize;
	byte[] buffer;
	boolean negative;
	
	File fin;
	FileInputStream in;
	
	Input(final String fileName, final int bfsize) throws IOException {
		fin = new File(fileName);		
		in = new FileInputStream(fin);
		
		bufsize = bfsize;
		buffer = new byte[bufsize];		

		pos = 0;
		filesize = (int)fin.length();
		
		in.read(buffer);
		c = buffer[0];
		if (c == '-')
			negative = true;
		else
			negative = false;
	}
	
	int nextInt() throws IOException {
		while (!(48 <= c && c <= 57)) {
			pos++;
			if (pos == bufsize) {
				in.read(buffer);
				pos = 0;
			}
			c = buffer[pos];
			if (c == '-')
				negative = true;
		}
		
		int result = 0;
		do {
			result = result * 10 + c - 48;
			
			pos++;
			if (pos == filesize) {
				in.close();
				break;
			}
			
			if (pos == bufsize) {
				in.read(buffer);
				pos = 0;
			}
			
			c = buffer[pos];
		} while (48 <= c && c <= 57);
		
		if (negative) {
			result = -result;
			negative = false;			
		}		
		
		return result;
	}
}

interface constants {
	int SENTINEL = -1000;
}