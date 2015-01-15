import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
//		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int k = in.nextInt();
		
		long[][] vect = new long[n][2];
		vect[0][0] = 0;
		vect[0][1] = k - 1;
		for (int i = 1; i < n; i++) {
			vect[i][0] = vect[i - 1][1];
			vect[i][1] = (k - 1) * (vect[i - 1][0] + vect[i - 1][1]);
		}
		
		out.println(vect[n - 1][0] + vect[n - 1][1]);
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}