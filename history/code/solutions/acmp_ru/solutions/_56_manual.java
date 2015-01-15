import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		String[] fnd = new String[n];
		for (int i = 0; i < n; i++)
			fnd[i] = in.readLine();
		
		int m = Integer.parseInt(in.readLine());
		String[] fof = new String[m];
		for (int i = 0; i < m; i++)
			fof[i] = in.readLine();
	
		rand = new Random();
		if (n > 0)
			sort(fnd, 0, n - 1);
		if (m > 0)
			sort(fof, 0, m - 1);

		boolean[] mutfnd = new boolean[n];
		for (int i = 0; i < n; i++)
			mutfnd[i] = false;
		
		boolean[] mutfof = new boolean[m];
		for (int i = 0; i < m; i++)
			mutfof[i] = false;
		
		int pos;
		if (m > 0)
			for (int i = 0; i < n; i++) {
				pos = search(fof, 0, m, fnd[i]);
				if (fof[pos].equals(fnd[i])) {
					mutfnd[i] = true;
					mutfof[pos] = true;
				}
			}
		
		out.print("Friends: ");
		for (int i = 0; i < n - 1; i++)
			out.print(fnd[i] + ", ");
		if (n > 0)
			out.print(fnd[n - 1]);
		out.println();
		
		int len = 0;
		String[] md = new String[200];
		
		for (int i = 0; i < n; i++)
			if (mutfnd[i])
				md[len++] = fnd[i];
		
		out.print("Mutual Friends: ");
		for (int i = 0; i < len - 1; i++)
			out.print(md[i] + ", ");
		if (len > 0)
			out.print(md[len - 1]);
		out.println();
		
		len = 0;
		for (int i = 0; i < m; i++)
			if (!mutfof[i])
				md[len++] = fof[i];
		
		out.print("Also Friend of: ");
		for (int i = 0; i < len - 1; i++)
			out.print(md[i] + ", ");
		if (len > 0)
			out.print(md[len - 1]);
		out.println();
		
		out.close();
	}
	
	static int m;
	static int search(String[] v, int l, int r, String key) {
		while (r - l > 1) {
			m = l + (r - l) / 2;
			if (key.compareTo(v[m]) < 0)
				r = m;
			else
				l = m;
		}
		return l;
	}

	static Random rand;
	static String mid, tmp;
	static void sort(String[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			while (v[i].compareTo(mid) < 0) ++i;
			while (mid.compareTo(v[j]) < 0) --j;
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
