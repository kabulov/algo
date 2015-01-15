import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		int[] rating = new int[n];
		Arrays.fill(rating, 0);
		int[] dur = new int[n];
		
		for (int i = 0; i < n; ++i) {
			hm.put(in.next(), i);
			dur[i] = in.nextInt();
		}
		
		int m = in.nextInt(), k = in.nextInt();
		
		int[] song = new int[m];
		for (int i = 0; i < m; ++i)
			song[i] = hm.get(in.next());
		
		int[] time = new int[k];
		for (int i = 0; i < k; ++i)
			time[i] = in.nextInt();
		
		for (int i = 0, j = 0, t = 0; i < m; ++i) {
			if (j < k && time[j] < t + dur[song[i]]) {
				--rating[song[i]];
				t = time[j++];
			} else {
				++rating[song[i]];
				t += dur[song[i]];
			}
		}
		
		for (int i = 0; i < n; ++i)
			out.print(rating[i] + " ");
		
		out.close();
	}
}