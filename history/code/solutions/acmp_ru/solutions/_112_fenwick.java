import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int k = nextInt();
		
		int sum = 0, tmp;
		summator obj = new summator(n);	
		for (int i = 0; i < k; i++) {
			obj.clear();
			obj.add(nextInt(), 1);
			for (int j = 1; j < n; j++) {
				tmp = nextInt();
				sum += obj.sum(tmp + 1, n);
				obj.add(tmp, 1);
			}
		}
		
		out.println(sum);
		out.close();
	}

	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class summator {
	int size;
	int[] map;
	
	summator(int range) {
		size = range;
		map = new int[size + 1];
	}
	
	void add(int pos, int num) {
		for (; pos <= size; pos += pos ^ (pos & (pos - 1))) 
			map[pos] += num;
	}
	
	int sumzero(int pos) {
		int s = 0;
		for (; pos > 0; pos &= pos - 1)
			s += map[pos];
		return s;
	}
	
	int sum(int l, int r) {//l > 0
		return sumzero(r) - sumzero(l - 1);
	}
	
	void clear() {
		Arrays.fill(map, 0);
	}
}