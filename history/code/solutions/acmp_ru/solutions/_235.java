import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		String str = in.next();
		
		boolean[][] map = new boolean[201][201];
		for (int i = 0; i < 101; i++)
			for (int j = 0; j < 101; j++)
				map[i][j] = false;
		
		int ipos = 100; 
		int jpos = 100;
		
		int ivect = 1;
		int jvect = 0;
		
		map[100][100] = true;

		int tmp;
		int answer = 0;
		boolean iscycle = false;
		int len = str.length();
outer:	for (int i = 0; i < len; i++)
			switch(str.charAt(i)) {
			case 'S':
				answer++;
				ipos += ivect;
				jpos += jvect;
				if (map[ipos][jpos]) {
					iscycle = true;
					break outer;
				} else
					map[ipos][jpos] = true;
				break;
			case 'L':
				tmp = ivect;
				ivect = jvect;
				jvect = -tmp;
				break;
			case 'R':
				tmp = ivect;
				ivect = -jvect;
				jvect = tmp;
				break;
			}
		
		if (!iscycle)
			answer = -1;
		
		out.println(answer);
		out.close();
	}

	static int[] fill;
	static boolean fit(int n) {
		if (n < 102) {
			return true;
		}
		for (int i = 0; i < 10; i++)
			fill[i] = 0;
		
		while (n > 0) {
			fill[n % 10] = 1;
			n /= 10;
		}
		
		int s = 0;
		for (int i = 0; i < 10; i++)
			s += fill[i];
		
		if (s > 2)
			return false;
		
		return true;
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}