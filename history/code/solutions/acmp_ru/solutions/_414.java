import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int[] par = new int[n];
		
		int f = in.nextInt() - 1, s = in.nextInt() - 1;
		
		if (f == s) {
			out.println(f + 1);
			out.close();
			return;
		}
		
		for (int i = 1; i < n; ++i)
			par[i] = in.nextInt() - 1;
		
		boolean[] map = new boolean[n];
		Arrays.fill(map, false);
		map[f] = map[s] = true;
		
		while (f > 0 || s > 0) {
			if (f != 0) {
				if (map[par[f]]) {
					out.println(par[f] + 1);
					break;
				}
				f = par[f];
				map[f] = true;
			}
			if (s != 0) {
				if (map[par[s]]) {
					out.println(par[s] + 1);
					break;
				}
				s = par[s];
				map[s] = true;
			}
		}
		
		out.close();
	}
}