import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		int ans = 0;
		for (int i = 1; i <= n; ++i)
			ans = (ans + k) % i;
		
		out.println(ans + 1);		
		out.close();
	}
}