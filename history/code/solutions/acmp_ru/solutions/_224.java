import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
//import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();

		int tmp;
		int[] v = new int[5];
		
		v[0] = v[1] = Integer.MAX_VALUE;
		v[2] = v[3] = v[4] = Integer.MIN_VALUE;
		
		for (int i = 0; i < n; ++i) {
			tmp = nextInt();
			if (tmp >= v[4]) {
				v[2] = v[3];
				v[3] = v[4];
				v[4] = tmp;
			} else
			if (tmp >= v[3]) {
				v[2] = v[3];
				v[3] = tmp;
			} else 
			if (tmp > v[2]) {
				v[2] = tmp;
			}
			
			if (tmp <= v[0]) {
				v[1] = v[0];
				v[0] = tmp;
			} else
			if (tmp < v[1]) {
				v[1] = tmp;
			}
		}
		
		if (n == 1)
			out.println(v[4]); // v[0]
		else
		if (n == 2)
			out.println(v[3] * v[4]); //v[0] * v[1]
		else
			out.println(Math.max((long)v[0] * v[1] * v[4], (long)v[2] * v[3] * v[4]));
		
/*		Arrays.sort(v);
		
		if (n == 1)
			out.println(v[0]);
		else
		if (n == 2)
			out.println(v[0] * v[1]);
		else 
			out.println(Math.max((long)v[0] * v[1] * v[n-1], (long)v[n-3] * v[n-2] * v[n-1]));
*/
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}