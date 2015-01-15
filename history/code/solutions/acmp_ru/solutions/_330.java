import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int bgnj = in.nextInt();
		int bgni = in.nextInt();
		int endj = in.nextInt();
		int endi = in.nextInt();
		
		if ((Math.abs(bgni - endi) % 2 == 0 && Math.abs(bgnj - endj) % 2 == 1) || (Math.abs(bgni - endi) % 2 == 1 && Math.abs(bgnj - endj) % 2 == 0))
			out.println(0);
		else
		if (Math.abs(bgni - endi) == Math.abs(bgnj - endj))
			out.println(1);
		else
			out.println(2);
		
		out.close();
	}
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}