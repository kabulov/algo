import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt() - 1;
		inp = new int[n];
		
		for (int i = 0; i < n; ++i)
			inp[i] = in.nextInt();
		
		prefix(n);
		
		if (n % (n - pfx[n-1]) == 0)
			n -= pfx[n-1];

		out.print(n);
		
		in.close();
		out.close();
	}
	
	static int[] inp;
	static int[] pfx;
	
	static void prefix(int n) {
		pfx = new int[n];
		
		pfx[0] = 0;
		for (int i = 1; i < n; ++i) {
			int j = pfx[i-1];
			while(j > 0 && inp[i] != inp[j])
				j = pfx[j-1];
			if (inp[i] == inp[j]) ++j;
			pfx[i] = j;
		}
	}
}