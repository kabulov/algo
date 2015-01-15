import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
//		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] vect = new int[n];
		
		for (int i = 0; i < n; i++)
			vect[i] = nextInt();
		
		int tmp;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (vect[i] > vect[j]) {
					tmp = vect[i];
					vect[i] = vect[j];
					vect[j] = tmp;
				}
				
		if (n <= 3)
			vect[n - 1] -= vect[0];
		else {
			int buf = vect[2];
			vect[1] -= vect[0];
			vect[2] -= vect[0];
			for (int i = 3; i < n; i++) {
				tmp = buf;
				buf = vect[i];
				vect[i] += Math.min(vect[i - 2], vect[i - 1]) - tmp;
			}
		}
		
		out.println(vect[n - 1]);
		out.close();
	}
	
	static PrintWriter out;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}