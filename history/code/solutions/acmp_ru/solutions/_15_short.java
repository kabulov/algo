import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int ans = 0, n = in.nextInt();
		
		for (int i = 0; i < n * n; i++)
			ans += in.nextInt();
		
		out.println(ans >>> 1);
		out.close();
	}
}