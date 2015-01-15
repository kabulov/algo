import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int l = nextInt();
		int n = nextInt();
		
		int[] inp = new int[n];
		for (int i = 0; i < n; ++i)  inp[i] = nextInt();
		
		Arrays.sort(inp);
		
		Minimizer m = new Minimizer(n + 1);
		m.push(0);
		
		for (int i = n - 1, r = n - 1; i >= 0; --i) {
			while (inp[i] + l < inp[r] - l) {
				m.pop();
				--r;			
			}
			m.push(1 + m.min());
		}
		
		out.println(m.last());
		out.close();
	}
	
	static StreamTokenizer in;

	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class Minimizer {
	int size, minf;
	int topf, tops;
	int[] fst, scd;
	
	Minimizer(int n) {
		size = n;
		fst = new int[size];
		scd = new int[size];
		topf = tops = 0;
	}
	
	int last() {
		return fst[topf - 1];
	}
	
	void push(int val) {
		fst[topf++] = val;
		if (topf == 1 || val < minf)
			minf = val; 
	}
	
	void pop() {
		if (tops == 0) { 
			scd[tops++] = fst[--topf];
			while (topf > 0) {
				scd[tops] = scd[tops - 1];
				if (fst[--topf] < scd[tops]) scd[tops] = fst[topf];
				++tops;
			}
		}
		--tops;
	}
	
	int min() {
		if (tops == 0) return minf;
		if (topf == 0) return scd[tops - 1];
		return minf < scd[tops - 1] ? minf : scd[tops - 1];
	}
}