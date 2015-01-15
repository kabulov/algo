import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int zs = 0;
		int us = 0;
		
		for (int i = 0; i < n; i++) {
			if (in.nextInt() == 0) 
				zs++;
			else
				us++;
		}
		
		out.println(Math.min(zs, us));
		
		out.close();
	}
}
