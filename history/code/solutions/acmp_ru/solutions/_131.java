import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int max = 0;
		int pos = -1;
		
		for (int i = 1 ; i <= n; i++) {
			int age = in.nextInt();
			if (in.nextInt() == 1 && age > max) {
				max = age;
				pos = i;
			}
		}
		
		out.println(pos);
		
		out.close();
	}
}
