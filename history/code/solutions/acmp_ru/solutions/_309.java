import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int s = 0;
		
		int tmpi, tmps;
		for (int i = 1; i < n; ++i) {
			tmpi = i;
			tmps = 0;
			while (tmpi > 0) {
				tmps = tmps * 10 + tmpi % 10;
				tmpi /= 10;
			}
			if (i + tmps == n) ++s;
		}
		
		out.println(s);
		out.close();
	}	
}
