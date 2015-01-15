import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		input in = new input("input.txt", 2 * 1024 * 1024);
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int m = in.nextInt();
		
		if (n == 0 || m == 0) {
			out.close();
			return;
		}
		
		int[] vn = new int[n];
		for (int i = 0; i < n; ++i)
			vn[i] = in.nextInt();
		
		int[] vm = new int[m];
		for (int i = 0; i < m; ++i)
			vm[i] = in.nextInt();
		
		rand = new Random();
		sort(vn, 0, n - 1);
		sort(vm, 0, m - 1);
		
		int nl = 1;
		for (int i = 1; i < n; ++i)
			if (vn[i] != vn[i - 1])
				vn[nl++] = vn[i];
		
		int ml = 1;
		for (int i = 1; i < m; ++i)
			if (vm[i] != vm[i - 1])
				vm[ml++] = vm[i];
		
		int i = 0, j = 0;
		while (i < nl && j < ml) {
			if (vn[i] < vm[j])
				++i;
			else
			if (vn[i] > vm[j])
				++j;
			else {
				out.print(vn[i] + " ");
				++i;
				++j;
			}
		}
		
		out.close();
	}
	
	static Random rand;
	static int mid, tmp;
	
	static void sort(int[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; v[i] < mid; ++i);
			for (; mid < v[j]; --j);
			if (i <= j) {
				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;
				++i;
				--j;
			}
		}
		if (l < j) sort(v, l, j);
		if (i < r) sort(v, i, r);
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