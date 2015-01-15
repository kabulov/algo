import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		String str;
		int rem, len;
		for (int i = 0; i < n; i++) {
			str = in.next();
			rem = 0;
			len = str.length();
			for (int j = 0; j < len; j++)
				if (str.charAt(len - 1 - j) == '1') 
					switch(j % 3) {
					case 0:
						rem += 2;
						break;
					case 1:
						rem += 4;
						break;
					case 2:
						rem += 1;
						break;
					}
			if (rem % 7 == 0)
				out.println("Yes");
			else
				out.println("No");
		}
		
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}