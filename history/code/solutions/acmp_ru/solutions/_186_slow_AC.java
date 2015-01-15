import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int[] vect = new int[n + 1];
		vect[0] = 0;
		for (int i = 1; i <= n; i++) {
			vect[i] = nextInt();
		}
	
		rand = new Random();
		sort(vect, 1, n);
		
		int[] ans = new int[n + 1];
		ans[2] = vect[2] - vect[1];
		if (n > 2) {
			ans[3] = vect[3] - vect[1];
		}
		
		for (int i = 4; i <= n; i++) {
			ans[i] = vect[i] - vect[1];
			for (int j = 3; j < i; j++)
				ans[i] = Math.min(ans[i], Math.max(ans[j - 1], vect[i] - vect[j]));
		}
		
		out.println(ans[n]);
		out.close();
	}
	
	static void sort(int[] vect, int l, int r) {
		int i = l, j = r;
		int m = vect[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			while (vect[i] < m) i++;
			while (m < vect[j]) j--;
			if (i <= j) {
				tmp = vect[i];
				vect[i] = vect[j];
				vect[j] = tmp;
				i++;
				j--;
			}
		}
		if (l < j) sort(vect, l, j);
		if (i < r) sort(vect, i, r);
	}
	
	static int tmp;
	static Random rand;
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}