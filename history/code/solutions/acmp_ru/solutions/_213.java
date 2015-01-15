import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new  PrintWriter("output.txt");

		int n = nextInt();
		
		int all = 0;
		int[] test = new int[n];
		for (int i = 0; i < n; ++i) all += test[i] = nextInt();
		int bonus = nextInt();

		int fine = 0, cur = 0;
		int m = nextInt();
		for (int i = 0; i < m; ++i) {
			int tmp = 0;
			for (int j = 0; j < n; ++j)
				if (nextInt() == 1) 
					tmp += test[j];
			
			if (tmp == all) tmp += bonus;
			tmp -= fine;
			
			if (tmp > cur) cur = tmp;
			
			out.println(cur);
			
			fine += 2;
		}
		
		out.close();
	}	
	
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
