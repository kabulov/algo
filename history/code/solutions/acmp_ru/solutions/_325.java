import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int[][] knight = {
				{-2, -1},
				{-2, +1},
				{+2, -1},
				{+2, +1},
				{-1, -2},
				{-1, +2},
				{+1, -2},
				{+1, +2}
		};
		
		String str = in.next();
		int fi = str.charAt(1) - 48;
		int fj = str.charAt(0) - 'a' + 1;
		
		str = in.next();
		int si = str.charAt(1) - 48;
		int sj = str.charAt(0) - 'a' + 1;
		
		boolean can = false;
		if (canget(fi, fj, si, sj)) {
			out.println(1);
			can = true;
		} else {
			for (int i = 0; i < 8; i++) {
				if (In(fi + knight[i][0], fj + knight[i][1]) && canget(fi + knight[i][0], fj + knight[i][1], si, sj)) {
					out.println(2);
					can = true;
					break;
				}
			}
		}
		
		if (!can)
			out.println("NO");
		
		out.close();
	}

	static boolean canget(int i1, int j1, int i2, int j2) {
		return (Math.abs((i1 - i2) * (j1 - j2)) == 2);
	}
	
	static boolean In(int i, int j) {
		return (0 < i && i <= 8 && 0 < j && j <= 8);
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}