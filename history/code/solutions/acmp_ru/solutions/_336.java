import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.StreamTokenizer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = "";
		if (in.hasNext())
			str = in.next();

		int left = 150;
		int right = 150;
		int pos = 150;
		
		int len = str.length();
		for (int i = 0; i < len; i++) 
			switch(str.charAt(i)) {
			case '1':
				pos++;
				if (pos > right)
					right = pos;
				break;
			case '2':
				pos--;
				if (pos < left)
					left = pos;
				break;
			}
		
		out.println(right - left + 1);
		out.close();
	}
	
//	static StreamTokenizer in;

//	public static long nextLong() {
//		return (long)in.nval;
//	}
	
//	public static boolean hasNext() throws IOException {
//		in.nextToken();
//		if (in.ttype == StreamTokenizer.TT_EOL || in.ttype == StreamTokenizer.TT_EOF)
//			return false;
//		return true;
//	}
}
