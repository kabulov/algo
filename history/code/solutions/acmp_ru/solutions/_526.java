import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String a = in.next();
		int b = in.nextInt();
		
		int tmp;
		boolean done = false;
		for (int i = 2; i <= 36; i++)
			try {
				tmp = Integer.parseInt(a, i);
				if (tmp == b) {
					out.println(i);
					done = true;
					break;
				}
			} catch (NumberFormatException n) {
				//do nothing 
			}				
			
		if (!done)
			out.println(0);
		
		out.close();
	}
}