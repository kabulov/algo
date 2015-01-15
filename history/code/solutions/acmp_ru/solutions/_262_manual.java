import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		PQ pq = new PQ(n);
		for (int i = 0; i < n; i++)
			pq.add(nextInt());
		
		int mid;
		long ans = 0;
		while (pq.len > 1) {
			mid = pq.poll() + pq.poll();
			ans += mid;
			pq.add(mid);
		}
		
		out.printf("%.2f", ans / 20.0);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}

class PQ {
	int[] v;
	int len;
	
	PQ(int size) {
		len = 0;
		v = new int[size];
	}
	
	void add(int val) {
		v[len++] = val;
		int i = len - 1;
		while (i > 0) {
			int par = (i - 1) / 2;
			if (v[par] > v[i]) {
				int tmp = v[par];
				v[par] = v[i];
				v[i] = tmp;
				i = par;
			} else
				return;
		}
	}
	
	int poll() {
		int min = v[0];
		v[0] = v[--len];
		siftdown();
		return min;
	}
	
	void siftdown() {
		int i = 0;
		while (2 * i + 1 < len) {
			int min = 2 * i + 1;
			if (2 * i + 2 < len && v[2 * i + 2] < v[min])
				min++;
			
			if (v[min] >= v[i])
				return;
			
			int tmp = v[min];
			v[min] = v[i];
			v[i] = tmp;
			
			i = min;
		}
	}
}
