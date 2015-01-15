import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
//		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		int n = nextInt();		
		int[] vect = new int[n];
		Arrays.fill(vect, Integer.MAX_VALUE);
		
		int len = 1;
		int key, l, r, m;
		vect[0] = nextInt();
		for (int i = 1; i < n; i++) {
			key = nextInt();
			l = 0; r = len;
			while (r - l > 1) {
				m = l + (r - l) / 2;
				if (key < vect[m])
					r = m;
				else
					l = m;
			}
			if (key <= vect[l]) {
				vect[l] = key;
			} else {
				vect[l + 1] = key;
				if (l + 1 == len) {
					++len;
				}
			}
		}
		
		out.println(len);
		out.close();
	}
	
	static PrintWriter out;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}