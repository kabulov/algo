import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int[] vect = new int[n];
		
		for (int i = 0; i < n; i++)
			vect[i] = in.nextInt();
		
		for (int i = 0; i < n; i++)
			out.println(vect[n - i - 1]);
		
		out.close();
	}
}
