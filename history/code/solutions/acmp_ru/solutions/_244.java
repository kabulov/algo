import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		int k = nextInt();
		
		int[] v = new int[n];
		for (int i = 0; i < n; i++) 
			v[i] = nextInt();
		
		int amt = 0, min = 0;
		for (int i = 0; i < n - k; ++i)
			if (v[i] == v[i + k]) {
				if (i + 2 * k < n && v[i + 2 * k] != v[i]) {
					if (amt == 1) {
						amt = 2;
						break;
					}
					amt = 1;
					v[i + 2 * k] = v[i];
					min = i + 2 * k + 1;
				}
			} else {//v[i] != v[i + k]
				if (amt == 1) {
					amt = 2;
					break;
				}
				amt = 1;
				if (i + 2 * k >= n) {
					min = i + 1;
				} else {
					if (v[i] == 1 && v[i + k] == 0) {
						if (v[i + 2 * k] == v[i]) {
							v[i + k] = 1;
							min = i + k + 1;
						} else {
							min = i + 1;
						}
					} else {//v[i] == 0 && v[i + k] == 1
						if (v[i + 2 * k] == v[i]) {
							v[i + k] = 0;
							min = i + k + 1;
						} else {
							min = i + 1;
						}
					}
				}
			}
		
		if (amt > 1)
			out.println("FAIL");
		else
			out.println("OK\n" + min);
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
