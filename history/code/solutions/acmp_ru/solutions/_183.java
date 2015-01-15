import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
//		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		int k = in.nextInt();
		int p = in.nextInt();

		if (k < 2) {
			out.println(0);
			out.close();
			return;
		}
		
		int[] map = new int[k + 1];
		map[2] = 1 % p;
		map[0] = map[1] = 0;
		
		for (int i = 3; i <= k; i++) {
			map[i] = map[i - 1];
			if (i % 2 == 0)
				map[i] = (map[i] + map[i / 2]) % p;
		}
		
		out.println(map[k]);
		out.close();
	}
	
	static PrintWriter out;
}