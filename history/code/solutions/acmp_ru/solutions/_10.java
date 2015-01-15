import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long a, b, c, d;
		a = in.nextLong();
		b = in.nextLong();
		c = in.nextLong();
		d = in.nextLong();
		
		boolean[] vect = new boolean[201];
		Arrays.fill(vect, false);
		
		for (int i = -100; i < 101; i++)
			if (a * i * i * i + b * i * i + c * i + d == 0) {
				vect[100 + i] = true;
			}
		
		for (int i = -100; i < 101; i++)
			if (vect[100 + i]) 
				out.print(i + " ");
		
		out.close();
	}
}