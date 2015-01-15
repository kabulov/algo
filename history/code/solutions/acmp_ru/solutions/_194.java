
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		long[] v = new long[56];
		v[1] = v[2] = 1;
		v[3] = 2;
		v[4] = 4;
		
		for (int i = 5; i < 56; ++i)
			v[i] = v[i - 1] + v[i - 3] + 1;
		
		out.println(v[in.nextInt()]);		
		out.close();
	}
}
