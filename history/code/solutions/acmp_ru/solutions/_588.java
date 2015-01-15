import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		switch(n) {
		case 2:case 3:case 6:
			out.println(-1);
			break;
		case 4:
			out.println("2\n1\n0\n1\n");
			break;
		case 5:
			out.println("1\n2\n0\n0\n2\n");
			break;
		default:
			out.println(2);
			out.println(1);
			for (int i = 3; i < n - 4; ++i) out.println(0);
			out.println(1);
			for (int i = n - 3; i < n; ++i) out.println(0);
			out.println(n - 4);
		}
		
		out.close();
	}
}
