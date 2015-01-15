import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		int tmp;
		for (int i = 0; i < n; i++) {
			tmp = in.nextInt();
			if (tmp > max)
				max = tmp;
			if (tmp < min)
				min = tmp;			
		}
		
		out.println(min + " " + max);
		
		out.close();
	}
}
