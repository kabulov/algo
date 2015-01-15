import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] argv) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		v = new agent[n];
		for (int i = 0; i < n; i++)
			v[i] = new agent(nextInt(), nextInt());
		
		
		buf = new agent[n / 2 + 1];
		sort(0, n - 1);
		
		int f = v[1].risk;
		
		if (n > 2) {
			int s = f;
			f = v[1].risk + v[2].risk;
			
			int tmp;
			for (int i = 3; i < n; i++) {
				tmp = v[i].risk + Math.min(f, s);
				s = f;
				f = tmp;
			}
		}
		
		out.println(f);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static class agent {
		int age, risk;
		agent(int a, int r) {
			age = a;
			risk = r;
		}
	}
	
	static agent[] v, buf;
	static void sort(int l, int r) {
		if (l == r)
			return;
		
		int m = (l + r) / 2;
		sort(l, m);
		sort(m + 1, r);
		
		System.arraycopy(v, l, buf, 0, m - l + 1);
//		for (int i = l; i <= m; i++)
//			buf[i - l] = v[i];
		
		int i = 0, j = m + 1, k = l;
		while (k <= r) 
			if (i > m - l)
				v[k++] = v[j++];
			else
			if (j > r)
				v[k++] = buf[i++];
			else 
			if (buf[i].age < v[j].age)
				v[k++] = buf[i++];
			else
				v[k++] = v[j++];
	}
}
