import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));

		size = nextInt();
		left = new int[size];
		right = new int[size];
		
		for (int i = 0; i < size; ++i) {
			left[i] = nextInt();
			right[i] = nextInt();
		}
		
		rand = new Random();
		sort(0, size - 1);
		
		int answer = 0;
		int min, max;
		min = left[0];
		max = right[0];
		
		for (int i = 1; i < size; ++i) {
			if (left[i] > max) {
				answer += max - min;
				min = left[i];
				max = right[i];
			} else {
				if (right[i] > max)
					max = right[i];
			}
		}
		
		out.println(answer + (max - min));
		out.close();
	}
	
	static int size;
	static int[] left, right;
	
	static int tmp;
	static Random rand;

	static void sort(int l, int r) {
		int i = l, j = r;
		int mid = left[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			while (left[i] < mid) ++i;
			while (mid < left[j]) --j;
			if (i <= j) {
				tmp = left[i];
				left[i] = left[j];
				left[j] = tmp;
				
				tmp = right[i];
				right[i] = right[j];
				right[j] = tmp;
				
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
	
	static PrintWriter out;
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}