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
		
		String str = in.next();
		str = str.replace(":", " ");
		String[] v = str.split(" ");
		
		int hh = Integer.parseInt(v[0]);
		int mm = Integer.parseInt(v[1]);
		
		int h = in.nextInt();
		int m = in.nextInt();
		
		final int cst = 24 * 60 * 60;
		
		int sum = hh * 60 * 60 + mm * 60;
		sum += h * 60 * 60 + m * 60;
		sum %= cst;
		
		hh = sum / 3600;
		mm = sum % 3600;
		mm /= 60;
		
		if (hh < 10)
			out.print(0);
		out.print(hh + ":");
		
		if (mm < 10)
			out.print(0);
		out.println(mm);
		
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
