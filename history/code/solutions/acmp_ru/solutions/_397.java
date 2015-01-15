
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");

		char[] v = in.readLine().toCharArray();
		int mxdif = 0, l = 0, r = 0, minpos = 0; //mxlen = r - l + 1 
		
		for (int i = 1, t; i < v.length; ++i) {
			if (v[i] <= v[minpos]) minpos = i;
			if ((t = v[i] - v[minpos]) > mxdif || (t == mxdif && i - minpos < r - l)) {
				mxdif = t;
				l = minpos;
				r = i;
			}
		}
		
		minpos = v.length - 1;
		for (int i = v.length - 2, t; i >= 0; --i) {
			if (v[i] <= v[minpos]) minpos = i;
			if ((t = v[i] - v[minpos]) > mxdif || (t == mxdif && minpos - i < r - l)) {
				mxdif = t;
				l = i;
				r = minpos;
			}
		}
		
		for (int i = l; i <= r; ++i) out.print(v[i]);
		out.close();
	}	
}