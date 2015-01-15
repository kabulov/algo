import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		out.println("YES");

		int x, y, xv, yv;
		x = y = 0;
		xv = 0;
		yv = 75;
		
		for (int i = 0; i < n; ++i) {
			out.println(x + " " + y);
		
			if (xv >= 0 && yv > 0) {
				++xv;
				--yv;
			} else
			if (xv > 0 && yv <= 0) {
				--xv;
				--yv;
			} else 
			if (xv <= 0 && yv < 0) {
				--xv;
				++yv;
			} else {//x < 0 && y >= 0
				++xv;
				++yv;
			}
			
			x += xv;
			y += yv;
		}
		
		out.close();
	}
}
