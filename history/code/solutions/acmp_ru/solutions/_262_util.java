import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < n; i++)
			pq.add(nextInt());
		
		long ans = 0;
		int mid;
		do {
			mid = pq.poll();
			if (pq.isEmpty())
				break;
			mid += pq.poll();
			ans += mid;
			pq.add(mid);
		} while (true);
		
		out.printf("%.2f", ans / 20.0);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
