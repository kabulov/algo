import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int lt = nextInt();
		int rt = nextInt();
		
		int[] vect = new int[rt + 1];
		for (int i = 1; i <= rt; ++i)
			vect[i] = 1;
		
		int bnd = rt / 2;
		for (int i = 2; i <= bnd; ++i) {
			for (int j = i + i; j <= rt; j += i)
				vect[j] += i;
		}
		
		boolean empty = true;
		
		int i;
		for (i = lt; i <= rt; ++i)
			if (i < vect[i] && vect[i] <= rt && vect[vect[i]] == i) {
				out.println(i + " " + vect[i]);
				empty = false;
				break;
			}

		++i;
		for (; i <= rt; ++i) 
			if (i < vect[i] && vect[i] <= rt && vect[vect[i]] == i)
				out.println(i + " " + vect[i]);
			
		if (empty)
			out.println("Absent");
		
		out.close();
	}
	
	static PrintWriter out;
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
