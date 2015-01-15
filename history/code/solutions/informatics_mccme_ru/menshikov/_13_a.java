import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int x1 = nextInt();
		int y1 = nextInt();
		int x2 = nextInt();
		int y2 = nextInt();
		
		int dx = nextInt();
		int dy = nextInt();
		
		rand = new Random();
		boolean[] map = solve(x1, dx, x2);
		
		int[] x = new int[100];
		int xl = 0;
		
		for (int i = 1; i < 101; i++)
			if (map[i])
				x[xl++] = i;
		
		map = solve(y1, dy, y2);
		
		int[] y = new int[100];
		int yl = 0;
		
		for (int i = 1; i < 101; i++)
			if (map[i])
				y[yl++] = i;
		
		int size = xl * yl;
		int[] v = new int[size];
		for (int i = 0; i < xl; i++)
			for (int j = 0; j < yl; j++)
				v[i * yl + j] = x[i] * y[j];
		
		sort(v, 0, size - 1);
		
		int amt = 1;
		for (int i = 1; i < size; i++)
			if (v[i] != v[i - 1])
				amt++;
		
		out.println(amt);
		out.println(v[0]);
		
		for (int i = 1; i < size; i++)
			if (v[i] != v[i - 1])
				out.println(v[i]);
		
		out.close();
	}
	
	static boolean[] solve(int f, int ofs, int s) {
		boolean[] v = new boolean[101];
		for (int i = 1; i < 101; i++)
			v[i] = false;
		
		int lcm = LCM(f, s);
		
		int vlen = 2;
		int[] vect = new int[lcm / f + 1 + lcm / s + 1];

		vect[0] = 0;
		vect[1] = ofs;
		
		int temp = 0;
		while (temp + f <= lcm) {
			vect[vlen++] = temp += f;
		}
		
		temp = ofs;
		while (temp + s <= ofs + lcm) {
			vect[vlen++] = temp += s;
		}
		
		sort(vect, 0, vlen - 1);
		for (int i = 1; i < vlen; i++)
			v[vect[i] - vect[i - 1]] = true;
		
		return v;
	}
	
	static int LCM(int a, int b) {
		return a / gcd(a, b) * b;
	}
	
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static Random rand;
	static int tmp, mid;
	static void sort(int[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j; ) {
			for (; v[i] < mid; i++);
			for (; mid < v[j]; j--);
			if (i <= j) {
				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;
				i++;
				j--;
			}
		}
		if (l < j) sort(v, l, j);
		if (i < r) sort(v, i, r);
	}
	
}