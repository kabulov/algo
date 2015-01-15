import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		boolean bool[] = new boolean[1000];
		for (int i = 0; i < 1000; i++)
			bool[i] = false;

		int tmp;
		String str = in.next();
		int len = str.length();
		for (int i = 0; i < len - 2; i++)
			for (int j = i + 1; j < len - 1; j++)
				for (int k = j + 1; k < len; k++) {
					tmp = 100 * (str.charAt(i) - 48) + 10 * (str.charAt(j) - 48) + (str.charAt(k) - 48);
					if (tmp < 1000)
						bool[tmp] = true;
				}	
		
		int answer = 0;
		for (int i = 100; i < 1000; i++)
			if (bool[i])
				answer++;
		
		out.println(answer);
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}