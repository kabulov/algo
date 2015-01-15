import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int[] vect = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			vect[i] = nextInt();
		}
		
		if (n > 1)
			vect[n - 1] += vect[n];
		
		if (n == 1)
			vect[0] = vect[1];
		else
			vect[0] = 0;
		
		int i;
		for (i = n - 2; i >= 0; --i)
			vect[i] += Math.max(vect[i + 1], vect[i + 2]);
		
		out.println(vect[0]);
		i = 1;
		if (n > 1 && vect[2] > vect[1])
			i = 2;
		
		while (i < n - 1) {
			out.print(i + " ");
			if (vect[i + 1] > vect[i + 2])
				i += 1;
			else
				i += 2;
		}
		
		if (i == n - 1)
			out.print(i + " ");
		
		out.println(n);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}