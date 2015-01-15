import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		String min = in.next();
		String max = in.next();
		
		String tmp;
		if (min.length() > max.length()) {
			tmp = min;
			min = max;
			max = tmp;
		}
			
		int answer = Integer.MAX_VALUE;
		int minlen = min.length();
		int maxlen = max.length();
		
		boolean ok;
		for (int i = -minlen; i < maxlen; i++) {
			ok = true;
			for (int j = Math.max(i, 0); j < Math.min(maxlen, i + minlen); j++)
				if (max.charAt(j) == '2' && min.charAt(Math.abs(j - i)) == '2') {
					ok = false;
					break;
				}
				
			if (ok) 
				answer = Math.min(answer, Math.max(maxlen, i + minlen) - Math.min(0, i));
		}
		
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