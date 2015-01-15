import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
//another solution is sort
public class Main {
	public static void main(String[] args) throws IOException {
		input in = new input("input.txt", 2 * 1024 * 1024);
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int m = in.nextInt();
		
		int[] vn = new int[100001]; // 10 ^ 5 + 1
		int[] vm = new int[100001];
		
		Arrays.fill(vn, 0);
		Arrays.fill(vm, 0);
		
		for (int i = 0; i < n; ++i)
			++vn[in.nextInt()];
			
		for (int i = 0; i < m; ++i)
			++vm[in.nextInt()];
		
		for (int i = 0; i < 100001; ++i)
			if (vn[i] > 0 && vm[i] > 0)
				out.print(i + " ");
		
		out.close();
	}
}

class input {
	FileInputStream fis;

	int pos, 
		buflen, 
		integer;

	byte[] buffer;
	byte c;
	
	input(String fname, int bsize) throws IOException {
		fis = new FileInputStream(fname);
		
		buffer = new byte[bsize];
		buflen = fis.read(buffer);
		
		pos = 0;
		c = buffer[0];
	}
	
	int nextInt() throws IOException {
		while (!('0' <= c && c <= '9')) {
			++pos;
			if (pos == buflen) {
				buflen = fis.read(buffer);
				pos = 0;
			}
			c = buffer[pos];			
		}
		
		integer = 0;
		do {
			integer = integer * 10 + c - '0';
			++pos;
			if (pos == buflen) {
				buflen = fis.read(buffer);
				if (buflen <= 0)
					break;
				pos = 0;
			}
			c = buffer[pos];
		} while ('0' <= c && c <= '9');
		
		return integer;
	}
}