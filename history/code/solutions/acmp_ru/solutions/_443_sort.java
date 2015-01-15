import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.readLine();
		String[] spt = str.split(" ");
		int n = Integer.parseInt(spt[0]);
		m = Integer.parseInt(spt[1]);
		x = Integer.parseInt(spt[2]);
		
		int[] map = new int[m];
		Arrays.fill(map, 0);
		
		int[] v = new int[n + 1];
		v[n] = m;
		for (int i = 0; i < n; i++)
			v[i] = hash(in.readLine());
		
		rand = new Random();
		sort(v, 0, n - 1);
		
		int answer = 0, len = 1;
		for (int i = 1; i <= n; i++) 
			if (v[i] == v[i - 1])
				len++;
			else {
				answer += len * (len - 1) / 2;
				len = 1;
			}
		 
		out.println(answer);
		out.close();
	}
	
	static int m, x;
	
	static int hash(final String str) {
		int xi = 1;
		int ans = 0;
		int len = str.length();
		
		for (int i = 0; i < len; ++i) {
			ans = (ans + (str.charAt(i) - 48) * xi) % m;
			xi = (xi * x) % m;
		}
		
		return ans;
	}
	
	static int tmp;
	static Random rand;
	static void sort(int[] v, int l, int r) {
		int i = l, j = r;
		int mid = v[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			while (v[i] < mid) i++;
			while (mid < v[j]) j--;
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
