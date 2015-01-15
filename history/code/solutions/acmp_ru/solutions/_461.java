import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] v = new int[n];
		
		for (int i = 0; i < n; i++)
			v[i] = nextInt();
		
		rand = new Random();
		sort(v, 0, n - 1);
		
		int ans = 0;
		for (int i = 0; i <= n / 2; i++)
			ans += v[i] / 2 + 1;
			
		out.println(ans);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static Random rand;
	static void sort(int[] v, int l, int r) {
		int i = l, j = r;
		int mid = v[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			while (v[i] < mid) ++i;
			while (mid < v[j]) --j;
			if (i <= j) {
				int tmp = v[i];
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
