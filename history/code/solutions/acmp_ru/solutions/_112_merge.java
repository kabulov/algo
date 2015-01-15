import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int k = nextInt();
		
		int ans = 0;
		
		v = new int[n];
		m = new int[n / 2 + 1];
		
		for (int i = 0; i < k; ++i) {
			for (int j = 0; j < n; ++j)
				v[j] = nextInt();
			
			amt = 0;
			msort(0, n - 1);
			ans += amt;
		}
		
		out.println(ans);
		out.close();
	}
	
	static int[] v, m;
	static int amt, len;
	static void msort(int l, int r) {
		if (l >= r)
			return;

		
		int mid = (l + r) / 2;
		msort(l, mid);
		msort(mid + 1, r);
		
		len = mid - l + 1;
		for (int i = l; i <= mid; ++i)
			m[i - l] = v[i];
		
		int k = l;
		for (int i = 0, j = mid + 1; i < len || j <= r;) {
			if (i == len) {
				v[k++] = v[j++];
			} else
			if (j > r) {
				v[k++] = m[i++];
			}else 
			if (m[i] <= v[j]) {
				v[k++] = m[i++];
			} else {
				amt += len - i;
				v[k++] = v[j++];
			}
		}
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
