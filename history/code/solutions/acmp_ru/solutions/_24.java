import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = in.nextInt();
		int m = in.nextInt();
		
		if (m > n)
			out.println(0);
		else
		if (n == 0) 
			out.println(1); 	
		else
		if (m == n)
			out.println(1);
		else
		if (m == 0)
			out.println(1);
		else
		if (m == 1)
			out.println(n);
		else {
			long s = 0;
			for (int i = 0; i <= (n - m) / (m - 1); ++i) s += n - m - i * (m - 1) + 1;			
			out.println(s);
		}
		
		out.close();
	}
}