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
		
		int min = 1, max = 1, sum = 0;
		for (int i = 0; i < n; i++) {
			v[i] = nextInt();
			if (v[i] > 0)
				sum += v[i];
			if (v[i] > v[max])
				max = i;
			if (v[i] < v[min])
				min = i;
		}
		
		out.print(sum + " ");
		sum = 1;
		if (min < max) 
			for (int i = min + 1; i < max; i++)
				sum *= v[i];
		else
			for (int i = max + 1; i < min; i++)
				sum *= v[i];
		
		out.println(sum);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
