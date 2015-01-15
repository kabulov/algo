import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		double cst = 1 / Math.sqrt(2);
		double[][] vector = {
				{0, 1},
				{cst, cst},
				{1, 0},
				{cst, -cst},
				{0, -1},
				{-cst, -cst},
				{-1, 0},
				{-cst, cst}
		};
		
		int n = in.nextInt();
		double x, y;
		x = y = 0;
		
		int p, step;
		for (int i = 0; i < n; i++) {
			p = in.nextInt();
			step = in.nextInt();
			
			x += step * vector[p - 1][0];
			y += step * vector[p - 1][1];
		}
		
		out.printf("%.3f %.3f", x, y);
		out.close();
	}
}