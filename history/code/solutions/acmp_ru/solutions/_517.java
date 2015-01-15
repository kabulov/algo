import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int n = in.nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = in.nextInt();
		
		int ans = 0;
		for (int t = 0, p = 0; t < 10; ++t) 
			if (v[p] == 10) {
				ans += 10 + v[p+1] + v[p+2];
				p++;
			} else
			if (v[p] + v[p+1] == 10) {
				ans += 10 + v[p+2];
				p += 2;
			} else {
				ans += v[p] + v[p+1];
				p += 2;
			}		
		
		out.println(ans);
		out.close();
	}
}