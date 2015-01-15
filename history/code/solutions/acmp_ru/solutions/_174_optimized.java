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

		int gorg = nextInt(), j = 0;
		for (int i = 0; i < n; i++)
			if (v[i] > gorg) {
				v[j++] = v[i];
			}
		
		n = j;
		int min, tmp;
		for (int i = 0; i < n - 1; i++) {
			min = i;
			for (j = i + 1; j < n; j++)
				if (v[j] < v[min])
					min = j;
					
			tmp = v[i];
			v[i] = v[min];
			v[min] = tmp;
		}
		
		double max = gorg;
		for (int i = 0; i < n; i++)
			max = (max + v[i]) / 2;
		
		out.printf("%.6f", max);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
