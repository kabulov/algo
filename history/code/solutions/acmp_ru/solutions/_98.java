import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = nextInt();
		
		int fst = 0;
		int scd = 0;
		
		int l = 0, r = n - 1;
		while (l <= r) {
			if (v[l] >= v[r]) {
				fst += v[l];
				l++;
			} else {
				fst += v[r];
				r--;
			}
			
			if (l <= r) {
				if (v[l] >= v[r]) {
					scd += v[l];
					l++;
				} else {
					scd += v[r];
					r--;
				}
			}
		}
		
		out.println(fst + ":" + scd);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}