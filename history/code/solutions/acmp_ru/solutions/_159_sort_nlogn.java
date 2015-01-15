import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		elem[] v = new elem[n];
		for (int i = 0; i < n; i++)
			v[i] = new elem(i + 1, nextInt());
		
		Arrays.sort(v);

		for (int i = 0; i < n; i++)
			out.print(v[i].ord + " ");
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class elem implements Comparable<elem>{
	int ord, val;
	elem(int a, int b) {
		ord = a;
		val = b;
	}

	public int compareTo(elem arg) {
		return val - arg.val;
	}
}