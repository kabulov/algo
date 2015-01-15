import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = nextInt();
		
		int min, tmp;
		for (int i = 0; i < n - 1; i++) {
			min = i;
			for (int j = i + 1; j < n; j++)
				if (v[j] < v[min])
					min = j;
					
			tmp = v[i];
			v[i] = v[min];
			v[min] = tmp;
		}
		
		double inp = nextInt(), mid, max = inp;
		for (int i = 0; i < n; i++) { 
			mid = inp;
			
			for (int j = i; j < n; j++)
				mid = (mid + v[j]) / 2;
			
			if (mid > max)
				max = mid;
		}
		
		out.printf("%.6f", max);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
