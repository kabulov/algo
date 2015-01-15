import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = 2 * in.nextInt();
		
		int[] map = new int[n];
		for (int i = 0; i < n; ++i)
			map[i] = in.nextInt();
		
		Arrays.sort(map);
		
		int pos = 0;
		boolean indic = true;

		int amt = 1;
		int[] v = new int[2000];
		
		v[0] = 0;
		v[1] = map[0];
		
		while (pos < n) {
			indic = !indic;
			if (pos + 1 < n && map[pos] != map[pos + 1] && indic) {
				v[2 * amt] = map[pos];
				v[2 * amt + 1] = map[pos + 1];
				++amt;
			} 
			++pos;
		}
		
		v[2 * amt] = map[n-1];
		v[2 * amt + 1] = 1000000000; //1e9
		++amt;
		
		int maxlen = v[1] - v[0];
		int curlen = v[1] - v[0];
		
		for (int i = 1; i < amt; ++i) {
			if (v[2 * i] == v[2 * i - 1]) {
				curlen += v[2 * i + 1] - v[2 * i];
			} else 
				curlen = v[2 * i + 1] - v[2 * i];

			if (curlen > maxlen) maxlen = curlen;
		}
		
		out.println(maxlen);
		
		in.close();
		out.close();
	}
}